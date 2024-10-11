package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionBox {
    Rectangle box;

    public CollisionBox(Vector2 position, float width, float height) {
        this.box = new Rectangle(position.x, position.y, width, height);
    }
}
