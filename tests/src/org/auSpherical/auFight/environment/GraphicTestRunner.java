package org.auSpherical.auFight.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.auSpherical.auFight.AuFight;
import org.auSpherical.auFight.ResourceManager;
import org.auSpherical.auFight.screens.AbstractScreen;
import org.auSpherical.auFight.screens.AuFightScreen;
import org.auSpherical.auFight.screens.LeaderboardScreen;
import org.auSpherical.auFight.screens.MenuScreen;

public class GraphicTestRunner {

    public static Lwjgl3Application application;
    public static AuFight game;
    public static float elapsedTime = 0f;
    public float TIME_LIMIT =0.1f;

    public GraphicTestRunner() {
        // Constructor vacío
    }

    public void setTimeLimit(float timeLimit) {
        TIME_LIMIT = timeLimit;
    }

    public void setUp() {
        elapsedTime = 0f; // Reiniciar tiempo cada vez que se llama a setUp
        setGame();
        createLwjgl3Application();
    }

    public void setDown() {
        if (application != null) {
            Gdx.app.exit(); // Llamada controlada para salir
        }
        if (game != null) {
            game.dispose();
        }
    }

    private Lwjgl3ApplicationConfiguration getConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1000, 1000); // Tamaño mínimo de ventana
        config.setResizable(false);       // Ventana no redimensionable
        config.setDecorated(false);       // Sin bordes ni decoración
        config.disableAudio(true);        // Deshabilitar audio para pruebas
        config.setForegroundFPS(1);       // Minimizar uso de recursos
        return config;
    }

    private void createLwjgl3Application() {
        application = new Lwjgl3Application(game, getConfig());
    }

    private void setGame() {
        game = new AuFight() {
            @Override
            public void create() {
                batch = new SpriteBatch();
                font = new BitmapFont();
                camera = new OrthographicCamera();
                ResourceManager.loadAllResources();
                camera.setToOrtho(false, 1600, 900);
                camera.update();
                setScreen(typeOfScreen("MENU"));
            }

            @Override
            public void render() {
                super.render();
                // Incrementar tiempo transcurrido
                elapsedTime += Gdx.graphics.getDeltaTime();
                // Salir si el tiempo transcurrido excede el límite
                if (elapsedTime > TIME_LIMIT) {
                    Gdx.app.exit();
                }
            }


            public AbstractScreen typeOfScreen(String screen) {
                switch (screen) {
                    case "GAME" -> {
                        try {
                            return new AuFightScreen(this);
                        } catch (XInputNotLoadedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case "TOP" -> {
                        return new LeaderboardScreen(this);
                    }
                    default -> {
                        return new MenuScreen(this);
                    }
                }
            }
        };
    }


}
