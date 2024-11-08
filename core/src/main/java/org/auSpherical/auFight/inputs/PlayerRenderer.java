package org.auSpherical.auFight.inputs;

import com.badlogic.gdx.graphics.g2d.*;
import org.auSpherical.auFight.ResourceManager;

public class PlayerRenderer {
    private Sprite sprite;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> doubleJumpAnimation;
    private float stateTime;

    public PlayerRenderer(int num) {
        idleAnimation = ResourceManager.createAnimation("cat_idle", 4, 0.1f);
        walkAnimation = ResourceManager.createAnimation("cat_walk", 8, 0.1f);
        jumpAnimation = ResourceManager.createAnimation("cat_jump", 8, 0.11f);
        doubleJumpAnimation = ResourceManager.createAnimation("cat_doublejump", 6, 0.11f);

        sprite = new Sprite(idleAnimation.getKeyFrame(0));
        sprite.setScale(4f);
        sprite.setX(num == 1 ? 200 : 1200);
        sprite.setY(300);
    }

    public void updateAnimation(boolean grounded, boolean doubleJump, float speedX, float deltaTime) {
        stateTime += deltaTime;
        TextureRegion frame;
        if (!grounded) {
            if (!doubleJump) {
                frame = doubleJumpAnimation.getKeyFrame(stateTime, false);
            } else {
                frame = jumpAnimation.getKeyFrame(stateTime, false);
            }
        } else if (Math.abs(speedX) > 0) {
            frame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }
        sprite.setRegion(frame);
        sprite.setSize(frame.getRegionWidth(), frame.getRegionHeight());
        sprite.setOriginCenter();
    }

    public void render(SpriteBatch batch, float x, float y, boolean lookingRight) {
        sprite.setPosition(x, y);
        sprite.setFlip(lookingRight, false);
        sprite.draw(batch);
    }
}