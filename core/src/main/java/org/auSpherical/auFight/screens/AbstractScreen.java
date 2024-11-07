package org.auSpherical.auFight.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.auSpherical.auFight.AuFight;

/**
 * This class provides basic screen management functionality and lifecycle methods.
 */
public abstract class AbstractScreen extends Stage implements Screen {
    protected final AuFight main;

    /**
     * Constructs an AbstractScreen with the given main game instance.
     *
     * @param main The main game instance.
     */
    public AbstractScreen(AuFight main) {
        this.main = main;
    }

    /**
     * Called when this screen becomes the current screen of the Game.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Renders the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        super.act(delta);
        super.draw();
    }

    /**
     * Resizes the screen. This method is called when the screen size changes.
     *
     * @param width The new width of the screen.
     * @param height The new height of the screen.
     */
    @Override
    public void resize(int width, int height) {
        getViewport().update(width,height);
    }

    /**
     * Called when the game is paused.
     */
    @Override
    public void pause() {
    }

    /**
     * Called when the game is resumed from a paused state.
     */
    @Override
    public void resume() {
    }

    /**
     * Called when this screen is no longer the current screen of the Game.
     */
    @Override
    public void hide() {
    }

    /**
     * Disposes of the screen's resources.
     */
    @Override
    public void dispose() {
    }
}