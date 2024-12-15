package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.inputs.Player;

public class HurtBox extends Rectangle implements Damageable {
    private final Vector2 vector2;
    private final Player player;

    public HurtBox(Vector2 vector2, Player player) {
        super(vector2.x, vector2.y, 68, 113);
        this.vector2 = vector2;
        this.player = player;
    }

    public void update() {
        setPosition(vector2.x-22, vector2.y-43);
    }

    public Player getPlayer() {
        return player;
    }

    public void receiveDamage(float damage) {
        player.receiveDamage(damage);
    }

    //si quieres ver la hitbox en pantalla
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}