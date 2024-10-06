//esta clase maneja los renderizados para hacerlo aparte
package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderManager {

    SpriteBatch batch;
    BitmapFont font;
    GameLogicManager gameLogicManager;

    public RenderManager(GameLogicManager gameLogicManager, Batch batch) {
        this.gameLogicManager = gameLogicManager;
        this.batch = (SpriteBatch) batch;
        font = new BitmapFont();
    }

    public void draw() {
        batch.begin();
        drawHud();

        gameLogicManager.player1.render(batch);
        gameLogicManager.player2.render(batch);
        batch.end();
    }

    private void drawHud() {
        font.draw(batch, "Score: " + gameLogicManager.player1.score, 20, 20);
    }
}
