package org.auSpherical.auFight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.auSpherical.auFight.inputs.Player;
import org.auSpherical.auFight.rendered.PlayerRenderer;

public class RenderManager {

    SpriteBatch batch;
    BitmapFont font;
    GameLogicManager gameLogicManager;
    Sprite background;
    PlayerRenderer player1Renderer;
    PlayerRenderer player2Renderer;
    private final TextureRegion healthBarRegion;

    public RenderManager(GameLogicManager gameLogicManager, SpriteBatch batch) {
        this.gameLogicManager = gameLogicManager;
        this.batch = batch;
        font = new BitmapFont();
        background = new Sprite(new Texture("stage.png"));
        background.setSize(1600, 900);
        player1Renderer = new PlayerRenderer(1);
        player2Renderer = new PlayerRenderer(2);
        Texture healthBarTexture = new Texture("RedBar.png");
        healthBarRegion = new TextureRegion(healthBarTexture);
    }

    public void draw() {
        batch.begin();
        background.draw(batch);
        renderPlayer(gameLogicManager.player1, player1Renderer);
        renderPlayer(gameLogicManager.player2, player2Renderer);
        drawHud();
        batch.end();
    }

    private void drawHud() {
        font.draw(batch, "Score: " + gameLogicManager.player1.score, 20, 20);
        drawHealthBar(gameLogicManager.player1.health, 500);
        drawHealthBar(gameLogicManager.player2.health, 900);
    }

    private void drawHealthBar(float health, float x) {
        batch.draw(healthBarRegion, x, (float) 850, health, 20);
    }

    private void renderPlayer(Player player, PlayerRenderer renderer) {
        renderer.updateAnimation(player.isGrounded(), player.canDoubleJump, player.getSpeed().x, Gdx.graphics.getDeltaTime());
        renderer.render(batch, player.getPosition().x, player.getPosition().y, player.lookingRight);
    }
}