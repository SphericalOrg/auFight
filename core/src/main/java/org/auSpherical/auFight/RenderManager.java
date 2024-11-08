package org.auSpherical.auFight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.auSpherical.auFight.inputs.Player;
import org.auSpherical.auFight.inputs.PlayerRenderer;

public class RenderManager {

    SpriteBatch batch;
    BitmapFont font;
    GameLogicManager gameLogicManager;
    Sprite background;
    PlayerRenderer player1Renderer;
    PlayerRenderer player2Renderer;

    public RenderManager(GameLogicManager gameLogicManager, Batch batch) {
        this.gameLogicManager = gameLogicManager;
        this.batch = (SpriteBatch) batch;
        font = new BitmapFont();
        background = new Sprite(new Texture("stage.png"));
        background.setSize(1600, 900);
        player1Renderer = new PlayerRenderer(1);
        player2Renderer = new PlayerRenderer(2);
    }

    public void draw() {
        batch.begin();
        drawHud();
        background.draw(batch);
        renderPlayer(gameLogicManager.player1, player1Renderer);
        renderPlayer(gameLogicManager.player2, player2Renderer);
        batch.end();
    }

    private void drawHud() {
        font.draw(batch, "Score: " + gameLogicManager.player1.score, 20, 20);
    }

    private void renderPlayer(Player player, PlayerRenderer renderer) {
        renderer.updateAnimation(player.isGrounded(), player.doubleJump, player.getSpeed().x, Gdx.graphics.getDeltaTime());
        renderer.render(batch, player.getPosition().x, player.getPosition().y, player.lookingRight);
    }
}