package org.auSpherical.auFight.inputs;

import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static Player player;
    private static PlayerInput mockInput;

    @BeforeAll
    public static void setUp() {
        mockInput = new PlayerInput();
        player = new Player(mockInput, 1);
    }

    @BeforeEach
    public void reset() {
        mockInput.LEFT = 0;
        mockInput.RIGHT = 0;
        mockInput.UP = 0;
        mockInput.DOWN = 0;
        mockInput.A = false;
        mockInput.B = false;
    }

    @Test
    public void testInitialPosition() {
        Vector2 expectedPosition = new Vector2(200, 300);
        assertEquals(expectedPosition, player.getPosition());
    }

    @Test
    public void testMoveRight() {
        mockInput.RIGHT = 1;
        player.move();
        assertTrue(player.getSpeed().x > 0);
    }

    @Test
    public void testMoveLeft() {
        mockInput.LEFT = 1;
        player.move();
        assertTrue(player.getSpeed().x < 0);
    }

    @Test
    public void testJump() {
        mockInput.UP = 1;
        player.move();
        assertTrue(player.getSpeed().y > 0);
    }

    @Test
    public void testDoubleJump() {
        mockInput.UP = 1;
        player.move();
        player.move(); // Simulate second jump
        assertFalse(player.doubleJump);
    }

    @Test
    public void testGrounded() {
        player.move();
        assertTrue(player.isGrounded());
    }
}
