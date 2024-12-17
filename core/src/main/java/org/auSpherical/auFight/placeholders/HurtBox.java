package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.inputs.Player;

public class HurtBox extends Rectangle implements CollitionBox {
    private final Player player;

    public HurtBox(Vector2 vector2, Player player) {
        super(vector2.x, vector2.y, 68, 113);
        this.player = player;
    }

    public void update() {
        setPosition(player.getPosition().cpy().add(-22,-43));
    }

    public Player getPlayer() {
        return player;
    }

    public int receiveDamage(float damage, boolean lookingLeft, float knockback, int hitStun) {
        return player.receiveDamage(damage, lookingLeft, knockback, hitStun);
    }

    //si quieres ver la hitbox en pantalla
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(player.shield.isActive() ? Color.BLUE : Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}