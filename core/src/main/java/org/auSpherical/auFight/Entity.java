package org.auSpherical.auFight;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.auSpherical.auFight.placeholders.CollitionBox;

/**
 * Abstract class representing an entity in the game.
 * Provides basic properties and methods for game entities.
 */
public abstract class Entity {
    public int health;
    // Use textureRegion because it may be useful for animations later
    public TextureRegion region;
    public CollitionBox collitionBox;
    public Sprite sprite;

    /**
     * Method to move the entity.
     * This method can be overridden by subclasses to implement specific movement logic.
     */
    public void move(){}
}
