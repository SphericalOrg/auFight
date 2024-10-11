package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.auSpherical.auFight.screens.AuFightScreen;
import org.auSpherical.auFight.screens.LeaderboardScreen;
import org.auSpherical.auFight.screens.MenuScreen;

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
        setScreen(new MenuScreen(this));
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
    public void changeScreen(String screen){
        switch (screen) {
            case "GAME":
                setScreen(new AuFightScreen(this));
                break;
            case "MENU":
                setScreen(new MenuScreen(this));
                break;
            case "TOP":
                setScreen(new LeaderboardScreen(this));
                break;
            /*case "NAME":
                setScreen(new MenuScreen(this));
                break;*/
            default:
                setScreen(new MenuScreen(this));
                break;
        }
    }
}
