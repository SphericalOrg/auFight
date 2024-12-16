package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.inputs.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HitBox extends Rectangle {
    private final Vector2 vector2;
    private final float damage;
    private final Player player;

    //agrega en que posicion quiero que se cree la hitbox
    public HitBox(Vector2 vector2, float damage, Player player) {
        super(vector2.x, vector2.y, 30, 30);
        this.vector2 = vector2;
        this.damage = damage;
        this.player = player;
    }

    public void update() {
        setPosition(vector2.x, vector2.y);
    }

    public void checkCollision(HurtBox hurtBox) {
        if (overlaps(hurtBox)) {
            hurtBox.receiveDamage(damage);
        }
    }

    public Player getPlayer() {
        return player;
    }

    //si quieres ver la hitbox en pantalla
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}