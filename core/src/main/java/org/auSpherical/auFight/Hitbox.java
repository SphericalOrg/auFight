package org.auSpherical.auFight;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
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
