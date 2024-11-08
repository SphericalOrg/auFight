package org.auSpherical.auFight.inputs;

import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.Entity;

public class Player extends Entity {
    private final float MAX_SPEED = 4.5f;
    private final float GROUND = 0.5f;
    private final float AIR = 0.05f;
    private final float GRAVITY = 0.5f;
    private final float ACCELERATION = 0.83f;
    final float JUMP = 16f;
    private final float FLOOR = 300;
    public boolean doubleJump = true;
    private PlayerInput controller;
    public boolean lookingRight = false;
    boolean actionable;
    private boolean grounded = true;
    private Vector2 speed = new Vector2(0,0);
    private Vector2 position;
    int actionTimer = 60;
    public int score;

    public Player(PlayerInput controller,int num){
        this.controller = controller;
        this.position = new Vector2(num == 1 ? 200 : 1200, FLOOR);
    }

    @Override
    public void move(){
        controller.update();
        ground();
        setSpeed();
        action();
        updateDirection();
        updatePosition();
    }

    public boolean isGrounded(){
        return grounded;
    }

    public Vector2 getSpeed(){
        return speed;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setGrounded(boolean bool){
        this.grounded = bool;
    }

    private void updateDirection() {
        if ((controller.RIGHT > 0 && lookingRight) && !(controller.LEFT>0)) {
            lookingRight = false;
        } else if ((controller.LEFT >0 && !lookingRight) && !(controller.RIGHT>0)) {
            lookingRight = true;
        }
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

    float fallingDelta(){
        return -GRAVITY*(1+controller.DOWN)+(ACCELERATION/10)*controller.UP;
    }

    private void jump(){
        speed.y = JUMP;
        speed.x = Math.signum((controller.RIGHT-controller.LEFT)/speed.x) == -1f?-0.5f*speed.x:speed.x;
        grounded = false;
        actionable = false;
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

    private void ground() {
        if (!grounded) {
            grounded = position.y <= FLOOR;
            if (grounded) {
                position.y = FLOOR;
                speed.y = 0;
                doubleJump = true;
            }
        }
    }

    private void updatePosition() {
        position.x += speed.x;
        position.y += speed.y;
    }
}