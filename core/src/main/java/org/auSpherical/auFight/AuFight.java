package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.auSpherical.auFight.screens.AuFightScreen;

public class AuFight extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        ResourceManager.loadAllResources();
        camera.setToOrtho(false, 1600, 900);
        camera.update();
        setScreen(new AuFightScreen(this));
    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
