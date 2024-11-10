package org.auSpherical.auFight;

public class Physics {

    public float fallingDelta(float controllerDown, float controllerUp) {
        return -AuConstants.GRAVITY * (1 + controllerDown) + (AuConstants.ACCELERATION / 10) * controllerUp;
    }

    public float airFriction(float value) {
        return Math.signum(value) * (Math.max(0, Math.abs(value) - AuConstants.AIR));
    }

    public float groundFriction(float value, float absoluteReduction, float controllerRight, float controllerLeft) {
        return Math.signum(value) * (Math.max(0, Math.abs(value) - absoluteReduction * movementFrictionReduction(value, controllerRight, controllerLeft)));
    }

    private float movementFrictionReduction(float value, float controllerRight, float controllerLeft) {
        return (1 - (Math.max(Math.abs(controllerRight - controllerLeft), 0.5f) * (Math.abs(value) / AuConstants.MAX_SPEED)));
    }

    public float clampSpeed(float value, float multiplier) {
        return Math.max(-AuConstants.MAX_SPEED * multiplier, Math.min(AuConstants.MAX_SPEED * multiplier, value));
    }
}