package org.auSpherical.auFight.placeholders;
import com.badlogic.gdx.math.Rectangle;

public class Hitbox {
    int damage;
    Rectangle hitbox;
    public Hitbox(int damage) {
        this.damage = damage;
    }
    public void hurt(Damageable victim) {
        victim.receiveDamage(damage);
    }
}
