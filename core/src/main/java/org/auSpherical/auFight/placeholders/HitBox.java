package org.auSpherical.auFight.placeholders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.inputs.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HitBox extends Rectangle implements CollitionBox {
    private final Vector2 vector2;
    private final float damage;
    private final Player player;
    private final Vector2 speed;
    public final boolean contact;
    public int duration;

    //agrega en que posicion quiero que se cree la hitbox
    public HitBox(Vector2 vector2, float damage, Player player, Vector2 speed, float width, float height, int duration, boolean contact) {
        super(vector2.x, vector2.y, width, height);
        this.vector2 = vector2;
        this.speed = speed;
        this.damage = damage;
        this.duration = duration;
        this.player = player;
        this.contact = contact;
    }


    public void update() {
        if (contact) {
            setPosition(player.getPosition().cpy().add(player.lookingLeft ? -120 : 30, -20));
        } else {
            setPosition(x+speed.x, y+speed.y);
        }

        duration--;
    }

    public void checkCollision(HurtBox hurtBox) {
        if (overlaps(hurtBox)) {
            hurtBox.receiveDamage(damage);
            duration = 0;
        }
    }

    public Player getPlayer() {
        return player;
    }

    //si quieres ver la hitbox en pantalla
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
}