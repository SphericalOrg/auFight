//screen principal donde se va a ejecutar el juego
package org.auSpherical.auFight;

public class AuFightScreen extends AbstractScreen{

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
        gameLogicManager.update(delta);
        renderManager.draw();
    }
    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void show() {

    }

}
