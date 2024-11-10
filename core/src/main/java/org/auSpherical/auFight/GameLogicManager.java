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
    Physics physics;

    public GameLogicManager(AuFight main) throws XInputNotLoadedException {
        this.main = main;
        this.physics = new Physics();
        player1 = new Player(new KeyboardInput(true), 1, physics);
        player2 = new Player(new KeyboardInput(false), 2, physics);
    }

    public void update() {

        handleInput();
    }

    private void handleInput() {
        player1.move();
        player2.move();
    }
}
