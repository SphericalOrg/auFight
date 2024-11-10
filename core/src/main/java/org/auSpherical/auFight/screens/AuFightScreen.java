package org.auSpherical.auFight.screens;

import org.auSpherical.auFight.AuFight;
import org.auSpherical.auFight.GameLogicManager;
import org.auSpherical.auFight.RenderManager;
import org.auSpherical.auFight.ResourceManager;

public class AuFightScreen extends AbstractScreen {

    GameLogicManager gameLogicManager;
    RenderManager renderManager;

    public AuFightScreen(AuFight main) {
        super(main);
        gameLogicManager = new GameLogicManager(main);
        renderManager = new RenderManager(gameLogicManager, main.batch);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ResourceManager.update();
        gameLogicManager.update();
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
