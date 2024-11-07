package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.auSpherical.auFight.screens.AuFightScreen;
import org.auSpherical.auFight.screens.LeaderboardScreen;
import org.auSpherical.auFight.screens.MenuScreen;

/**
 * Main class for the AuFight game.
 * This class handles the game lifecycle and screen navigation.
 */
public class AuFight extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;

    /**
     * Initializes the game, loading necessary resources and setting up the initial screen.
     */
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

    /**
     * Disposes of the game's resources when they are no longer needed.
     * This method is called when the game is closed.
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Changes the current screen of the game based on the given screen name.
     *
     * @param screen The name of the screen to switch to. Valid values are "GAME", "MENU", "TOP".
     *               Defaults to the menu screen if an invalid name is provided.
     */
    public void changeScreen(String screen) {
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
