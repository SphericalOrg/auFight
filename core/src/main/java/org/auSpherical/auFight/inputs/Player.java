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
    public Shield shield;
    private int generalCD = 0;
    private int jumpCD = 0;
    private int actionCD = 0;
    private int rangedAttackTimer = 0;
    private int meleeAttackTimer = 0;
    public int score;

    private boolean meleeAttackState = false;
    private boolean rangedAttackState = false;

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
            blockStun(shield.receiveDamage(amm));
        } else {
            health -= amm;
        }
        if (health <= 0){
            score--;
        }
        return health > 0 ? 0 : 1;
    }

    private void blockStun(int stun) {
        generalCD = stun;
    }

    public HurtBox getHurtBox(){
        return (HurtBox) collitionBox;
    }

    @Override
    public void move() {
        controller.update();
        ground();
        reduceCooldowns();
        shield.regenerar();

        performAttack();
        queueAttack();
        defend();

        setSpeed();
        updateDirection();
        updatePosition();
        updateRangedAttackState();
        updateMeleeAttackState();
    }

    private void defend(){
        shield.deactivate();
        if (grounded && controller.DOWN == 1 && !queuedAttack() && isActionable()){
            shield.activate();
            generalCD += 1;
        }
    }

    private void reduceCooldowns(){
        jumpCD -= jumpCD > 0 ? 1 : 0;
        actionCD -= actionCD > 0 ? 1 : 0;
        generalCD -= generalCD > 0 ? 1 : 0;
    }

    private void performAttack(){
        if (queuedAttack() && isActionable()){
            collitionManager.addHitBox(meleeAttackState ? performMeleeAttack() : performRangedAttack());
        }
    }


    private void updateMeleeAttackState() {
        if (meleeAttackState && meleeAttackTimer > 0) {
            meleeAttackTimer--;
            meleeAttackState = meleeAttackTimer != 0;
        }
    }

    public void setMeleeAttackState(boolean state) {
        meleeAttackState = state;
        if (state) {
            meleeAttackTimer = 50;
        }
    }

    public boolean getMeleeAttackState() {
        return meleeAttackState;
    }
  
    private void queueAttack(){
        if (validateAttack() && isActionable()){
            if (controller.A){
                queueMeleeAttack();
            } else {
                queueRangedAttack();
            }
        }
    }

    private boolean validateAttack(){
        return !queuedAttack() && (controller.A || controller.B);
    }

    private boolean queuedAttack(){
        return meleeAttackState || rangedAttackState;
    }

    private void queueMeleeAttack(){
        actionCD = 17;
        generalCD = 17;
        setMeleeAttackState(true);
    }

    private void queueRangedAttack(){
        actionCD = 85;
        generalCD = 85;
        setRangedAttackState(true);
    }

    private HitBox performMeleeAttack() {
        actionCD += 33;
        return new HitBox(new Vector2(position.cpy()).add(lookingLeft ? -120 : 30, -20), 5, this , new Vector2(0,0), 150, 70, 20, true);
    }

    private HitBox performRangedAttack() {
        actionCD += 20;
        return new HitBox(new Vector2(position.cpy()).add(lookingLeft ? -90 : 45, 5), 10, this , new Vector2(lookingLeft ? -20 : 20,0), 10, 5, 100, false);
    }

    //TODO: sync with actual attacks. RN performAttack() takes priority over setting the state to false
    private void updateRangedAttackState() {
        if (rangedAttackState && rangedAttackTimer > 0) {
            rangedAttackTimer--;
            rangedAttackState = rangedAttackTimer != 0;
        }
    }

    public void setRangedAttackState(boolean state) {
        rangedAttackState = state;
        if (state) {
            rangedAttackTimer = 104;
        }
    }

    public boolean getRangedAttackState() {
        return rangedAttackState;
    }

    private boolean isActionable(){
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
        lookingLeft = controller.RIGHT == controller.LEFT ? lookingLeft : controller.LEFT == 1;
    }

    private void setSpeed() {
        if (grounded) {
            speed.x = physics.clampSpeed(physics.groundFriction(speed.x + (AuConstants.ACCELERATION * movementCommand()), AuConstants.GROUND, movementCommand()), 1);
        } else {
            speed.x = physics.clampSpeed(physics.airFriction(speed.x + AuConstants.ACCELERATION * movementCommand()/2), 2);
            speed.y += physics.fallingDelta(controller.DOWN, controller.UP);
        }

        if (validateJump()) {
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
        canDoubleJump = grounded;
        speed.y = AuConstants.JUMP * (grounded ? 1 : (float) 2/3);
        speed.x = Math.signum((controller.RIGHT - controller.LEFT) / speed.x) == -1f ? -0.5f * speed.x : speed.x;
        grounded = false;
        jumpCD += 40;
        actionCD = Math.max(actionCD, 10);
    }

    private void ground() {
        if (position.y <= AuConstants.FLOOR && !grounded) {
            grounded = canDoubleJump = true;
            position.y = AuConstants.FLOOR;
            speed.y = 0;
        }
    }

    private void updatePosition() {
        position.x = Math.min(Math.max(position.x + speed.x, 20), 1560);
        position.y += speed.y;
    }
}