package org.auSpherical.auFight;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
    private final float MAX_SPEED = 8.5f;
    private final float GROUND = 0.5f;
    private final float AIR = 0.05f;
    private final float GRAVITY = 0.5f;
    private final float ACCELERATION = 0.83f;
    private final float JUMP = 15f;
    private final float FLOOR = -850;
    private boolean doubleJump= true;
    private PlayerInput controller;
    private boolean lookingRight = false;
    private boolean actionable;
    private boolean grounded = true;
    private Vector2 speed = new Vector2(0,0);
    private int actionTimer = 60;
    public int score;
    Texture texture;

    public Player(PlayerInput controller,int num){
        this.controller = controller;
        texture = new Texture("soki.png");
        region = new TextureRegion(texture);
        sprite = new Sprite(region);
        sprite.setScale(0.12f);
        sprite.setX(num==1?-800:200);
        sprite.setY(FLOOR);
        sprite.setColor(num==2? Color.BLUE:Color.RED);
    }
    public void summonShield(){
    }

    public void summonWeapon(){

    }

    @Override
    public void move(){
        controller.update();
        ground();
        setSpeed();
        action();
        sprite.setPosition(sprite.getX()+speed.x,sprite.getY()+speed.y);
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

    private void setSpeed(){
        if (grounded){
            speed.x = clampSpeed(groundFriction(speed.x+(ACCELERATION*(controller.RIGHT-controller.LEFT)),GROUND),1);
        } else {
            speed.x = clampSpeed(airFriction(speed.x+(ACCELERATION/2)*(controller.RIGHT-controller.LEFT), AIR),2);
            speed.y += fallingDelta();
        }

        if (actionable && (doubleJump || grounded) && controller.UP==1 && speed.y<=0.1f){
            doubleJump = grounded;
            jump();
        }
    }

    private float fallingDelta(){
        return -GRAVITY*(1+controller.DOWN)+(ACCELERATION/10)*controller.UP;
    }

    private void jump(){
        speed.y = JUMP;
        speed.x = Math.signum((controller.RIGHT-controller.LEFT)/speed.x) == -1.3f?-0.5f*speed.x:speed.x;
        grounded = false;
        actionable = false;
    }

    public void lookLeft(){
        if (lookingRight){
            sprite.flip(true,false);
            lookingRight = false;
        }
    }

    public void lookRight(){
        if (!lookingRight){
            sprite.flip(true,false);
            lookingRight = true;
        }
    }
    private float airFriction(float value, float absoluteReduction){
        return Math.signum(value)*(Math.max(0,Math.abs(value)-absoluteReduction));
    }

    private float groundFriction(float value, float absoluteReduction){
        return Math.signum(value)*(Math.max(0,Math.abs(value)-absoluteReduction*movementFrictionReduction(value)));
    }

    private float movementFrictionReduction(float value){
        return (1-(Math.max(Math.abs(controller.RIGHT-controller.LEFT),0.5f)*(Math.abs(value)/MAX_SPEED)));
    }
    private float clampSpeed(float value,float multiplier){
        return Math.max(-MAX_SPEED*multiplier,Math.min(MAX_SPEED*multiplier,value));
    }

    private void ground(){
        if (!grounded){
            grounded = sprite.getY()<= FLOOR;
            if (grounded){
                sprite.setY(FLOOR);
                speed.x*=2;
                doubleJump = true;
                speed.y=0;
            }
        }
    }

    // este metodo se encarga de dibujar el sprite
    public void render(Batch batch){
        sprite.draw(batch);
    }
}
