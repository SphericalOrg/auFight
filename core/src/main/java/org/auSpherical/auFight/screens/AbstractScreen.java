package org.auSpherical.auFight.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.auSpherical.auFight.AuFight;

public abstract class AbstractScreen extends Stage implements Screen {
    protected final AuFight main;
    public AbstractScreen(AuFight main) {
        this.main = main;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        super.act(delta);
        super.draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}