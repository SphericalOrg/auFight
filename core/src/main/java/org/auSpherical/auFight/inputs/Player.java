package org.auSpherical.auFight.inputs;

import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.AuConstants;
import org.auSpherical.auFight.Entity;
import org.auSpherical.auFight.Physics;

public class Player extends Entity {
    public boolean doubleJump = true;
    private final PlayerInput controller;
    public boolean lookingRight = false;
    boolean actionable;
    private boolean grounded = true;
    private final Vector2 speed = new Vector2(0, 0);
    private final Vector2 position;
    int actionTimer = 60;
    public int score;
    private final Physics physics;

    public Player(PlayerInput controller, int num, Physics physics) {
        this.controller = controller;
        this.physics = physics;
        this.position = new Vector2(num == 1 ? 200 : 1200, AuConstants.FLOOR);
    }

    @Override
    public void move() {
        controller.update();
        ground();
        setSpeed();
        action();
        updateDirection();
        updatePosition();
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
        if ((controller.RIGHT > 0 && lookingRight) && !(controller.LEFT > 0)) {
            lookingRight = false;
        } else if ((controller.LEFT > 0 && !lookingRight) && !(controller.RIGHT > 0)) {
            lookingRight = true;
        }
    }

    private void action() {
        if (!actionable) {
            actionTimer--;
        }
        if (actionTimer <= 0) {
            actionable = true;
            actionTimer = 60;
        }
    }

    private void setSpeed() {
        if (grounded) {
            speed.x = physics.clampSpeed(physics.groundFriction(speed.x + (AuConstants.ACCELERATION * (controller.RIGHT - controller.LEFT)), AuConstants.GROUND, controller.RIGHT, controller.LEFT), 1);
        } else {
            speed.x = physics.clampSpeed(physics.airFriction(speed.x + (AuConstants.ACCELERATION / 2) * (controller.RIGHT - controller.LEFT)), 2);
            speed.y += physics.fallingDelta(controller.DOWN, controller.UP);
        }

        if (actionable && (doubleJump || grounded) && controller.UP == 1 && speed.y <= 0.1f) {
            doubleJump = grounded;
            jump();
        }
    }

    private void jump() {
        speed.y = AuConstants.JUMP;
        speed.x = Math.signum((controller.RIGHT - controller.LEFT) / speed.x) == -1f ? -0.5f * speed.x : speed.x;
        grounded = false;
        actionable = false;
    }

    private void ground() {
        if (!grounded) {
            grounded = position.y <= AuConstants.FLOOR;
            if (grounded) {
                position.y = AuConstants.FLOOR;
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