package org.auSpherical.auFight.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import org.auSpherical.auFight.Entity;
import org.auSpherical.auFight.ResourceManager;

/**
 * Represents a player in the game.
 * Handles player movement, animations, and input controls.
 */
public class Player extends Entity {
    private final float MAX_SPEED = 4.5f;
    private final float GROUND = 0.5f;
    private final float AIR = 0.05f;
    private final float GRAVITY = 0.5f;
    private final float ACCELERATION = 0.83f;
    private final float JUMP = 16f;
    private final float FLOOR = 300;
    private boolean doubleJump = true;
    private PlayerInput controller;
    private boolean lookingRight = false;
    private boolean actionable;
    private boolean grounded = true;
    private Vector2 speed = new Vector2(0,0);
    private int actionTimer = 60;
    public int score;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> doubleJumpAnimation;
    private float stateTime;

    /**
     * Constructs a Player with the given input controller and player number.
     *
     * @param controller The input controller for the player.
     * @param num The player number.
     */
    public Player(PlayerInput controller,int num){
        this.controller = controller;

        idleAnimation = ResourceManager.createAnimation("cat_idle", 4, 0.1f);
        walkAnimation = ResourceManager.createAnimation("cat_walk", 8, 0.1f);
        jumpAnimation = ResourceManager.createAnimation("cat_jump", 8, 0.11f);
        doubleJumpAnimation = ResourceManager.createAnimation("cat_doublejump", 6, 0.11f);

        sprite = new Sprite(idleAnimation.getKeyFrame(0));
        sprite.setScale(4f);
        sprite.setX(num == 1 ? 200 : 1200);
        sprite.setY(FLOOR);
    }

    /**
     * Updates the player's state.
     * This method is called every frame to handle movement, animations, and input.
     */
    @Override
    public void move(){
        controller.update();
        stateTime += Gdx.graphics.getDeltaTime();
        updateAnimation();
        ground();
        setSpeed();
        action();
        sprite.setPosition(sprite.getX()+speed.x,sprite.getY()+speed.y);
        sprite.setFlip(lookingRight, false);
        updateDirection();
    }

    /**
     * Updates the player's direction based on input.
     */
    private void updateDirection() {
        if ((controller.RIGHT > 0 && lookingRight) && !(controller.LEFT>0)) {
            lookingRight = false;
        } else if ((controller.LEFT >0 && !lookingRight) && !(controller.RIGHT>0)) {
            lookingRight = true;
        }
    }

    /**
     * Updates the player's animation based on state.
     */
    private void updateAnimation() {
        TextureRegion frame;
        if (!grounded) {
            if (!doubleJump) {
                frame = doubleJumpAnimation.getKeyFrame(stateTime, false);
            } else {
                frame = jumpAnimation.getKeyFrame(stateTime, false);
            }
        } else if (Math.abs(speed.x) > 0) {
            frame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }
        sprite.setRegion(frame);
        sprite.setSize(frame.getRegionWidth(), frame.getRegionHeight());
        sprite.setOriginCenter();
    }

    private void action(){
        if (!actionable){
            actionTimer--;
            System.out.println(actionTimer);
        }
        if (actionTimer<=0){
            actionable = true;
            actionTimer = 60;
        }
    }

    /**
     * Sets the player's speed based on input and state.
     */
    private void setSpeed(){
        if (grounded){
            speed.x = clampSpeed(groundFriction(speed.x+(ACCELERATION*(controller.RIGHT-controller.LEFT)),GROUND),1);
        } else {
            speed.x = clampSpeed(airFriction(speed.x+(ACCELERATION/2)*(controller.RIGHT-controller.LEFT), AIR),1.5f);
            speed.y += fallingDelta();
        }

        if (actionable && (doubleJump || grounded) && controller.UP==1 && speed.y<=0.1f){
            doubleJump = grounded;
            jump();
        }
    }

    /**
     * Calculates the falling delta based on gravity and input.
     *
     * @return The calculated falling delta.
     */
    private float fallingDelta(){
        return -GRAVITY*(1+controller.DOWN)+(ACCELERATION/10)*controller.UP;
    }

    /**
     * Handles the player's jump logic.
     */
    private void jump(){
        speed.y = JUMP;
        speed.x = Math.signum((controller.RIGHT-controller.LEFT)/speed.x) == -1f?-0.5f*speed.x:speed.x;
        grounded = false;
        actionable = false;
        stateTime = 0;
    }

    /**
     * Applies air friction to the player's speed.
     *
     * @param value The current speed value.
     * @param absoluteReduction The amount of friction to apply.
     * @return The adjusted speed value.
     */
    private float airFriction(float value, float absoluteReduction){
        return Math.signum(value)*(Math.max(0,Math.abs(value)-absoluteReduction));
    }

    /**
     * Applies ground friction to the player's speed.
     *
     * @param value The current speed value.
     * @param absoluteReduction The amount of friction to apply.
     * @return The adjusted speed value.
     */
    private float groundFriction(float value, float absoluteReduction){
        return Math.signum(value)*(Math.max(0,Math.abs(value)-absoluteReduction*movementFrictionReduction(value)));
    }

    /**
     * Calculates the movement friction reduction based on speed.
     *
     * @param value The current speed value.
     * @return The calculated friction reduction.
     */
    private float movementFrictionReduction(float value){
        return (1-(Math.max(Math.abs(controller.RIGHT-controller.LEFT),0.5f)*(Math.abs(value)/MAX_SPEED)));
    }

    /**
     * Clamps the player's speed to a maximum value.
     *
     * @param value The current speed value.
     * @param multiplier The maximum speed multiplier.
     * @return The clamped speed value.
     */
    private float clampSpeed(float value,float multiplier){
        return Math.max(-MAX_SPEED*multiplier,Math.min(MAX_SPEED*multiplier,value));
    }

    /**
     * Handles the player's ground logic.
     * Checks if the player is grounded and updates the state accordingly.
     */
    private void ground(){
        if (!grounded){
            grounded = sprite.getY()<= FLOOR;
            if (grounded){
                sprite.setY(FLOOR);
                speed.x*=2;
                doubleJump = true;
                speed.y=0;
                stateTime = 0;
            }
        }
    }

    /**
     * Renders the player sprite.
     *
     * @param batch The batch used for rendering the sprite.
     */
    // este metodo se encarga de dibujar el sprite
    public void render(Batch batch){
        sprite.draw(batch);
    }
}
