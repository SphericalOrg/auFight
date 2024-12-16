package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;


public class CollisionBoxManager {
    public final Array<HurtBox> hurtBoxes = new Array<>();
    public final Array<HitBox> hitBoxes = new Array<>();

    public void addHurtBox(HurtBox hurtBox) {
        hurtBoxes.add(hurtBox);
    }

    public void addHitBox(HitBox hitBox) {
        hitBoxes.add(hitBox);
    }

    public void update() {
        updatePositions();
        checkCollisions();
    }

    private void updatePositions() {
        Array.ArrayIterator<HurtBox> hurtBoxIterator = new Array.ArrayIterator<>(hurtBoxes);
        while (hurtBoxIterator.hasNext()) {
            hurtBoxIterator.next().update();
        }

        Array.ArrayIterator<HitBox> hitBoxIterator = new Array.ArrayIterator<>(hitBoxes);
        while (hitBoxIterator.hasNext()) {
            HitBox hitBox = hitBoxIterator.next();
            hitBox.update();
            hitBox.render(new ShapeRenderer());
        }
    }

    //esto ya detecta si es que el usuario se golpea
    private void checkCollisions() {
        Array.ArrayIterator<HitBox> hitBoxIterator = new Array.ArrayIterator<>(hitBoxes);
        while (hitBoxIterator.hasNext()) {
            HitBox hitBox = hitBoxIterator.next();
            Array.ArrayIterator<HurtBox> hurtBoxIterator = new Array.ArrayIterator<>(hurtBoxes);
            while (hurtBoxIterator.hasNext()) {
                HurtBox hurtBox = hurtBoxIterator.next();
                if (hitBox.getPlayer() != hurtBox.getPlayer()) {
                    hitBox.checkCollision(hurtBox);
                }
            }
        }
    }

    public void removeHitBox(HitBox hitBox) {
        hitBoxes.removeValue(hitBox, false);
    }
}