package org.auSpherical.auFight.rendered;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import org.auSpherical.auFight.ResourceManager;

public class WeaponsRenderer {
    private final Sprite sprite;
    private final Animation<TextureRegion> animation;
    private TextureRegion frame;
    private float stateTime;

    public WeaponsRenderer(Boolean contact) {
        animation = ResourceManager.createAnimation("cat_jump", 8, 0.2f);
        sprite = new Sprite(animation.getKeyFrame(0));
        sprite.setScale(3.5f);
    }

    public void render(SpriteBatch batch, float x, float y, boolean lookingRight) {
        stateTime += Gdx.graphics.getDeltaTime();
        System.out.println(stateTime);
        frame = animation.getKeyFrame(stateTime, true);
        sprite.setRegion(frame);
        sprite.setSize(frame.getRegionWidth(), frame.getRegionHeight());
        sprite.setPosition(x, y);
        sprite.setFlip(lookingRight, false);
        sprite.draw(batch);
    }
}