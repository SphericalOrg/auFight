//esta clase sirve para hacer la logica de los sprites
package org.auSpherical.auFight;

import org.auSpherical.auFight.inputs.KeyboardInput;
import org.auSpherical.auFight.inputs.Player;

public class GameLogicManager {

    AuFight main;
    Cat player1;
    Cat player2;

    public GameLogicManager(AuFight main) {
        this.main = main;
        player1 = new Cat(new Player(new KeyboardInput(true),1));
        player2 = new Cat(new Player(new KeyboardInput(true),1));
    }
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
