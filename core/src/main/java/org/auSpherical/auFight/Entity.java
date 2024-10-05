package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Entity {
    public int health;
    //Use textureRegion por que para despues nos puede servir para animaciones
    public TextureRegion texture;
    public CollitionBox collitionBox;

    public void move(){}
}
