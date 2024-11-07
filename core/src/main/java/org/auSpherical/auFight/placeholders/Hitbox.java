package org.auSpherical.auFight.placeholders;
import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a hitbox in the game.
 */
public class Hitbox {
    int damage;
    Rectangle hitbox;

    /**
     * Constructs a Hitbox with the specified damage.
     *
     * @param damage The amount of damage the hitbox deals.
     */
    public Hitbox(int damage) {
        this.damage = damage;
    }

    /**
     * Inflicts damage on a given victim.
     *
     * @param victim The target that receives the damage.
     */
    public void hurt(Damageable victim) {
        victim.receiveDamage(damage);
    }
}

