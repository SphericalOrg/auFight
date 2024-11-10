package org.auSpherical.auFight;

import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.auSpherical.auFight.inputs.ControllerInput;
import org.auSpherical.auFight.inputs.KeyboardInput;
import org.auSpherical.auFight.inputs.Player;

public class GameLogicManager {

    AuFight main;
    Player player1;
    Player player2;

    public GameLogicManager(AuFight main) throws XInputNotLoadedException {
        this.main = main;

        player1 = new Player(new ControllerInput(XInputDevice.getDeviceFor(0)),1);
        player2 = new Player(new KeyboardInput(false),2);
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
