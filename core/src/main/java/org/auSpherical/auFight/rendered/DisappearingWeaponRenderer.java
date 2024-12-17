package org.auSpherical.auFight.rendered;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.auSpherical.auFight.ResourceManager;

public class DisappearingWeaponRenderer {
    private final Sprite sprite;
    private final Animation<TextureRegion> animation;
    private float stateTime;
    private boolean destroyed;
    private float x;
    private float y;
    private boolean lookingRight;

    public DisappearingWeaponRenderer(boolean contact, float x, float y, boolean lookingRight) {
        animation = ResourceManager.createAnimation(contact ? "impact" : "bulletimpact" ,contact ? 3 : 2, 0.1f);
        sprite = new Sprite(animation.getKeyFrame(0));
        sprite.setScale(contact ? 4f : 2.5f);
        destroyed = false;
        this.x = x;
        this.y = y;
        this.lookingRight = lookingRight;
    }

    public void render(SpriteBatch batch) {
        if (destroyed) return;
        System.out.println("Rendering disappearing weapon");
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        sprite.setRegion(frame);
        sprite.setSize(frame.getRegionWidth(), frame.getRegionHeight());
        sprite.setPosition(x, y);
        sprite.setFlip(lookingRight, false);
        sprite.draw(batch);

        if (isAnimationFinished()) {
            destroy();
        }
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }

    private void destroy() {
        destroyed = true;
    }
}