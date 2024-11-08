package org.auSpherical.auFight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.auSpherical.auFight.inputs.Player;


public class Cat extends Entity {
    private float stateTime;
    public Player player;
    public int score;

    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> doubleJumpAnimation;

    public Cat (Player player){
        this.player = player;
        idleAnimation = ResourceManager.createAnimation("cat_idle", 4, 0.1f);
        walkAnimation = ResourceManager.createAnimation("cat_walk", 8, 0.1f);
        jumpAnimation = ResourceManager.createAnimation("cat_jump", 8, 0.11f);
        doubleJumpAnimation = ResourceManager.createAnimation("cat_doublejump", 6, 0.11f);

        sprite = new Sprite(idleAnimation.getKeyFrame(0));
        sprite.setScale(4f);
        sprite.setPosition(player.getX(), player.getY());
    }

    @Override
    public void move(){

        stateTime += Gdx.graphics.getDeltaTime();
        player.move();
        sprite.setPosition(player.getX(), player.getY());
        sprite.setFlip(player.shouldFlip(), false);
        updateAnimation();
    }
    private void updateAnimation() {
        TextureRegion frame;
        if (!player.isGrounded()) {
            if (!player.canDoubleJump()) {
                frame = doubleJumpAnimation.getKeyFrame(stateTime, false);
            } else {
                frame = jumpAnimation.getKeyFrame(stateTime, false);
            }
        } else if (Math.abs(player.getSpeed().x) > 0) {
            frame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            frame = idleAnimation.getKeyFrame(stateTime, true);
        }
        sprite.setRegion(frame);
        sprite.setSize(frame.getRegionWidth(), frame.getRegionHeight());
        sprite.setOriginCenter();
    }


    public void render(Batch batch){
        sprite.draw(batch);
    }
}
