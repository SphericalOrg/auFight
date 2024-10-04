package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity {
    public int health;
    public Sprite sprite;
    public CollitionBox collitionBox;

    public void move(){}
}
