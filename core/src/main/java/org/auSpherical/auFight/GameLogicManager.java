//esta clase sirve para hacer la logica de los sprites
package org.auSpherical.auFight;

public class GameLogicManager {

    AuFight main;
    Player player1;
    Player player2;

    public GameLogicManager(AuFight main) {
        this.main = main;
        player1 = new Player(new KeyboardInput(true),1);
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
        if (player1.sprite.getX()>player2.sprite.getX()){
            player1.lookLeft();
            player2.lookRight();
        } else {
            player1.lookRight();
            player2.lookLeft();
        }
    }
}
