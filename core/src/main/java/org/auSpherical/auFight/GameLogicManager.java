package org.auSpherical.auFight;

import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.auSpherical.auFight.inputs.KeyboardInput;
import org.auSpherical.auFight.inputs.Player;
import org.auSpherical.auFight.placeholders.CollisionBoxManager;

public class GameLogicManager {

    AuFight main;
    Player player1;
    Player player2;
    Physics physics;
    CollisionBoxManager collisionBoxManager;


    public GameLogicManager(AuFight main) throws XInputNotLoadedException {
        this.main = main;
        this.physics = new Physics();
        this.collisionBoxManager = new CollisionBoxManager();
        player1 = new Player(new KeyboardInput(true), 1, physics, collisionBoxManager);
        player2 = new Player(new KeyboardInput(false), 2, physics, collisionBoxManager);
        //las hurtboxes las puse aqui para agregar por que son 2 siempre
        collisionBoxManager.addHurtBox(player1.getHurtBox());
        collisionBoxManager.addHurtBox(player2.getHurtBox());
    }

    public void update() {
        collisionBoxManager.update();
        handleInput();
    }

    private void handleInput() {
        player1.move();
        player2.move();
    }
}
