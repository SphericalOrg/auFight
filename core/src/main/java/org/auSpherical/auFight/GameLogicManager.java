//esta clase sirve para hacer la logica de los sprites
package org.auSpherical.auFight;

import org.auSpherical.auFight.inputs.KeyboardInput;
import org.auSpherical.auFight.inputs.Player;

/**
 * This class handles the entity logic
 */
public class GameLogicManager {

    AuFight main;
    Player player1;
    Player player2;

    /**
     * Constructs a GameLogicManager with the given main game instance.
     * Initializes players with keyboard inputs.
     *
     * @param main The main game instance.
     */
    public GameLogicManager(AuFight main) {
        this.main = main;
        player1 = new Player(new KeyboardInput(true),1);
        player2 = new Player(new KeyboardInput(false),2);
    }

    /**
     * Updates the game logic.
     * This method should be called every frame.
     *
     * @param delta The time in seconds since the last update.
     */
    //aqui va donde deberias actualizar la logica del juego
    public void update(float delta) {

        handleInput();
    }
    //no me acuerdo como xuxa cmanejabas los inputs jun asi que dejo esto por aca si lo usas bien si no borralo
    private void handleInput() {
        player1.move();
        player2.move();
    }
}
