package org.auSpherical.auFight;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
    private PlayerInput controller;
    private boolean performingAction;
    public int score;
    public Vector2 vector2;

    public Player(){
        texture = new TextureRegion(new Texture("soki.png"));
        vector2 = new Vector2(300,100);
    }
    public void summonShield(){
    }

    public void summonWeapon(){

    }
    // este metodo se encarga de dibujar el sprite
    public void render(Batch batch){
        batch.draw(texture, vector2.x, vector2.y);
    }
}
