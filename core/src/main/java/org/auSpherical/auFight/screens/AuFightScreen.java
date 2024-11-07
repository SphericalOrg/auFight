//screen principal donde se va a ejecutar el juego
package org.auSpherical.auFight.screens;

import com.badlogic.gdx.Gdx;
import org.auSpherical.auFight.AuFight;
import org.auSpherical.auFight.GameLogicManager;
import org.auSpherical.auFight.RenderManager;
import org.auSpherical.auFight.ResourceManager;

/**
 * Main screen where the game will be executed.
 */
public class AuFightScreen extends AbstractScreen {

    GameLogicManager gameLogicManager;
    RenderManager renderManager;

    /**
     * Constructs an AuFightScreen with the given main game instance.
     *
     * @param main The main game instance.
     */
    public AuFightScreen(AuFight main) {
        super(main);
        gameLogicManager = new GameLogicManager(main);
        renderManager = new RenderManager(gameLogicManager, main.batch);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ResourceManager.update();
        gameLogicManager.update(delta);
        renderManager.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
        ResourceManager.dispose();
    }
}
