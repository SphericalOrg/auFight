package org.auSpherical.auFight.inputs;


import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.badlogic.gdx.math.Vector2;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
class PlayerTest {

    @Test
    void testPlayerMoveWithGroundedTrue() {
        KeyboardInput controller = new KeyboardInput(true);
        Player player = new Player(controller, 1);

        player.move();

        assertTrue(player.isGrounded());
        assertEquals(new Vector2(0, 0), player.getSpeed());
        assertFalse(player.lookingRight);
    }

    @Test
    void testPlayerMoveWithGroundedFalse() {
        KeyboardInput controller = new KeyboardInput(true);
        Player player = new Player(controller, 1);

        player.setGrounded(false);
        player.move();

        assertFalse(player.isGrounded());
        assertEquals(0, player.getSpeed().x);
        assertEquals(player.fallingDelta(), player.getSpeed().y);
    }

    @Test
    void testPlayerMoveWithJump() {
        KeyboardInput controller = new KeyboardInput(true);
        Player player = new Player(controller, 1);

        // Simulate jump
        controller.UP = 1;
        player.move();

        assertFalse(player.isGrounded());
        assertEquals(player.JUMP, player.getSpeed().y);
    }

    @Test
    void testPlayerMoveWithLookingLeft() {
        KeyboardInput controller = new KeyboardInput(true);
        Player player = new Player(controller, 1);

        // Simulate pressing left key
        controller.LEFT = 1;
        player.move();

        assertTrue(player.lookingRight);
    }

    @Test
    void testPlayerMoveWithLookingRight() {
        KeyboardInput controller = new KeyboardInput(true);
        Player player = new Player(controller, 1);

        // Simulate pressing right key
        controller.RIGHT = 1;
        player.move();

        assertFalse(player.lookingRight);
    }
}