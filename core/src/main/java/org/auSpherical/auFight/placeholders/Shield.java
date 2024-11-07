package org.auSpherical.auFight.placeholders;

import org.auSpherical.auFight.Entity;

/**
 * Represents a shield in the game.
 */
public class Shield extends Entity implements Damageable {

    private boolean isActive;

    /**
     * Regenerates the shield.
     * This method is a placeholder and needs to be implemented.
     */
    public void regenerar() {
        //TODO: Waiting
    }

    /**
     * Receives damage.
     * This method is a placeholder and needs to be implemented.
     */
    public void recieveDamage() {
        //TODO: Waiting
    }

    /**
     * Receives damage with a specified amount.
     * This method overrides the receiveDamage method from the Damageable interface.
     *
     * @param damage The amount of damage to be received.
     */
    @Override
    public void receiveDamage(int damage) {

    }
}
