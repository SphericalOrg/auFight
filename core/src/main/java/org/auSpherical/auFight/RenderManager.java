//esta clase maneja los renderizados para hacerlo aparte
package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class handles the rendering of the game
 */
public class RenderManager {

    SpriteBatch batch;
    BitmapFont font;
    GameLogicManager gameLogicManager;
    Sprite background;

    /**
     * Constructs a RenderManager with the given GameLogicManager and Batch.
     *
     * @param gameLogicManager The game logic manager instance.
     * @param batch The batch used for rendering.
     */
    public RenderManager(GameLogicManager gameLogicManager, Batch batch) {
        this.gameLogicManager = gameLogicManager;
        this.batch = (SpriteBatch) batch;
        font = new BitmapFont();
        background = new Sprite(new Texture("stage.png"));
        background.setSize(1600, 900);
    }

    /**
     * Draws the game elements.
     * This method should be called every frame.
     */
    public void draw() {
        batch.begin();
        drawHud();
        background.draw(batch);
        gameLogicManager.player1.render(batch);
        gameLogicManager.player2.render(batch);
        batch.end();
    }

    /**
     * Draws the Heads-Up Display (HUD) elements.
     */
    private void drawHud() {
        font.draw(batch, "Score: " + gameLogicManager.player1.score, 20, 20);
    }
}
