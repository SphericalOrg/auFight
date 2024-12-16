package org.auSpherical.auFight.inputs;

import static org.junit.jupiter.api.Assertions.*;
import com.badlogic.gdx.math.Vector2;
import org.auSpherical.auFight.Physics;
import org.auSpherical.auFight.placeholders.CollisionBoxManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private  Player player;
    private  PlayerInput mockInput;
    private CollisionBoxManager collisionBoxManager;

    @BeforeEach
    public void setUp() {
        mockInput = new PlayerInput();
        Physics physics = new Physics();
        player = new Player(mockInput, 1, physics, collisionBoxManager);
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
        player.actionable = true;
        player.move();
        assertTrue(player.getSpeed().y > 0);
    }

    @Test
    public void testDoubleJump() {
        mockInput.UP = 1;
        player.actionable = true;
        for (int i = 0; i < 60; i++) {
            player.move();
        }
        mockInput.UP = 1;
        player.move(); // Simulate second jump
        assertFalse(player.doubleJump);
    }

    @Test
    public void testGrounded() {
        player.move();
        assertTrue(player.isGrounded());
    }
}
