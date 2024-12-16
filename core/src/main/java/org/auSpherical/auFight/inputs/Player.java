package org.auSpherical.auFight.inputs;

import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.AuConstants;
import org.auSpherical.auFight.Entity;
import org.auSpherical.auFight.Physics;
import org.auSpherical.auFight.placeholders.CollisionBoxManager;
import org.auSpherical.auFight.placeholders.HitBox;
import org.auSpherical.auFight.placeholders.HurtBox;
import org.auSpherical.auFight.placeholders.Shield;

public class Player extends Entity {
    public boolean canDoubleJump = true;
    private final PlayerInput controller;
    public boolean lookingLeft = false;
    private boolean grounded = true;
    private final Vector2 speed = new Vector2(0, 0);
    private final Vector2 position;
    public boolean shieldActive = false;
    private Shield shield;
    private int generalCD = 0;
    private int jumpCD = 0;
    private int actionCD = 0;
    public int score;
    private final Physics physics;
    private final CollisionBoxManager collitionManager;

    public Player(PlayerInput controller, int num, Physics physics, CollisionBoxManager collitionManager) {
        this.controller = controller;
        this.physics = physics;
        this.collitionManager = collitionManager;
        this.position = new Vector2(num == 1 ? 200 : 1200, AuConstants.FLOOR);
        this.collitionBox = new HurtBox(position, this);
        this.health = 100;
        this.shield = new Shield();
    }



    public int receiveDamage(float amm){
        if (shield.isActive()){
            if (shield.receiveDamage(amm) == 1){
                shieldBreak();
            }
        } else {
            health -= amm;
        }
        if (health <= 0){
            score--;
        }
        return health > 0 ? 0 : 1;
    }

    private void shieldBreak() {
        shieldActive = false;
        generalCD = 100;
    }

    public HurtBox getHurtBox(){
        return (HurtBox) collitionBox;
    }
    @Override
    public void move() {
        controller.update();
        ground();
        refreshCooldowns();
        performAction();
        setSpeed();
        updateDirection();
        updatePosition();
    }

    private void refreshCooldowns(){
        jumpCD = jumpCD > 0 ? jumpCD - 1 : 0;
        actionCD = actionCD > 0 ? actionCD - 1 : 0;
        generalCD = generalCD > 0 ? generalCD - 1 : 0;
        shield.regenerar(shieldActive && shield.health<100 ? 0 : 10);
    }

    private void performAction(){
        if (validateAction()){
            if (controller.A || controller.B){
                if (controller.A){
                    meleeAttack();
                } else {
                    rangedAttack();
                }
            } else if (grounded && controller.DOWN == 1){
                shieldActive = true;
            }
        }
    }

    private void meleeAttack(){
        collitionManager.addHitBox(new HitBox(new Vector2(position).add(lookingLeft ? -50 : 50, -5), 10, this , new Vector2(0,0), 20, 200));
        actionCD = 30;
        generalCD = 20;
    }

    private void rangedAttack(){
        collitionManager.addHitBox(new HitBox(new Vector2(position).add(lookingLeft ? -50 : 50, 5), 10, this , new Vector2(50,0), 10, 5));
        actionCD = 60;
        generalCD = 10;
    }

    private boolean validateAction(){
        return actionCD == 0 && generalCD == 0;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public Vector2 getPosition() {
        return position;
    }

    private void updateDirection() {
        if ((controller.RIGHT > 0 && lookingLeft) && !(controller.LEFT > 0)) {
            lookingLeft = false;
        } else if ((controller.LEFT > 0 && !lookingLeft) && !(controller.RIGHT > 0)) {
            lookingLeft = true;
        }
    }

    private void setSpeed() {
        if (grounded) {
            speed.x = physics.clampSpeed(physics.groundFriction(speed.x + (AuConstants.ACCELERATION * movementCommand()), AuConstants.GROUND, movementCommand()), 1);
        } else {
            speed.x = physics.clampSpeed(physics.airFriction(speed.x + AuConstants.ACCELERATION * movementCommand()/2), 2);
            speed.y += physics.fallingDelta(controller.DOWN, controller.UP);
        }

        if (validateJump()) {
            canDoubleJump = grounded;
            jump();
        }

    }

    private float movementCommand(){
        return generalCD == 0 ? (controller.RIGHT - controller.LEFT) : 0;
    }
    private boolean validateJump() {
        return controller.UP == 1 && (grounded || canDoubleJump) && jumpCD == 0 && generalCD == 0;
    }

    private void jump() {
        speed.y = AuConstants.JUMP;
        speed.x = Math.signum((controller.RIGHT - controller.LEFT) / speed.x) == -1f ? -0.5f * speed.x : speed.x;
        grounded = false;
        jumpCD += 60;
        actionCD = actionCD > 0 ? actionCD : 10;
    }

    private void ground() {
        if (!grounded) {
            grounded = position.y <= AuConstants.FLOOR;
            if (grounded) {
                position.y = AuConstants.FLOOR;
                speed.y = 0;
                canDoubleJump = true;
            }
        }
    }

    private void updatePosition() {
        position.x += speed.x;
        position.y += speed.y;
    }
}