package org.auSpherical.auFight.rendered;

import com.badlogic.gdx.graphics.g2d.*;
import org.auSpherical.auFight.ResourceManager;

public class PlayerRenderer {
    private final Sprite sprite;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> jumpAnimation;
    private final Animation<TextureRegion> doubleJumpAnimation;
    private final Animation<TextureRegion> meleeAttackAnimation;
    private final Animation<TextureRegion> deathAnimation;
    private final Animation<TextureRegion> rangedAttackAnimation;
    private Animation<TextureRegion> currentAnimation;
    private TextureRegion frame;
    private float stateTime;

    public PlayerRenderer(int num) {
        idleAnimation = ResourceManager.createAnimation(num == 1 ? "cat_idle": "dog_idle", 4, 0.1f);
        walkAnimation = ResourceManager.createAnimation(num == 1 ? "cat_walk": "dog_walk", 8, 0.1f);
        jumpAnimation = ResourceManager.createAnimation(num == 1 ? "cat_jump": "dog_jump", 8, 0.11f);
        doubleJumpAnimation = ResourceManager.createAnimation(num == 1 ? "cat_doublejump": "dog_doublejump", 6, 0.11f);
        meleeAttackAnimation = ResourceManager.createAnimation(num == 1 ? "cat_melee" : "dog_melee", 10, 0.05f);
        rangedAttackAnimation = ResourceManager.createAnimation(num == 1 ? "cat_ranged" : "dog_ranged", 6, 0.1f);
        deathAnimation = ResourceManager.createAnimation(num == 1 ? "cat_doublejump" : "dog_doublejump", 6, 0.1f);

        sprite = new Sprite(idleAnimation.getKeyFrame(0));
        sprite.setScale(4f);
        sprite.setX(num == 1 ? 200 : 1200);
        sprite.setY(300);
    }


    public void updateAnimation(boolean grounded, boolean doubleJump, float speedX, float deltaTime, boolean rangedAttack, boolean meleeAttack) {
        Animation<TextureRegion> newAnimation = idleAnimation;

        if (!grounded) {
            newAnimation = doubleJump ? jumpAnimation : doubleJumpAnimation;
        } else if (Math.abs(speedX) > 0) {
            newAnimation = walkAnimation;
        } else if (rangedAttack) {
            newAnimation = rangedAttackAnimation;
        } else if (meleeAttack) {
            newAnimation = meleeAttackAnimation;
        }

        if (newAnimation != currentAnimation) {
            currentAnimation = newAnimation;
            stateTime = 0;
        }

        stateTime += deltaTime;
        frame = currentAnimation.getKeyFrame(stateTime, true);

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