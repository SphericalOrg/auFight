package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.auSpherical.auFight.placeholders.CollitionBox;

public abstract class Entity {
    public int health;
    //Use textureRegion por que para despues nos puede servir para animaciones
    public TextureRegion region;
    public CollitionBox collitionBox;
    public Sprite sprite;

    public void move(){}
}
