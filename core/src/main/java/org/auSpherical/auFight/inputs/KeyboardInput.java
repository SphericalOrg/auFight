package org.auSpherical.auFight.inputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * Handles keyboard input for a player.
 */
public class KeyboardInput extends PlayerInput {
    private final boolean hasConfigOne;
    private int KEY_LEFT;
    private int KEY_RIGHT;
    private int KEY_UP;
    private int KEY_DOWN;
    private int KEY_A;
    private int KEY_B;

    /**
     * Constructs a KeyboardInput with the specified configuration.
     *
     * @param configOne Determines which key configuration to use.
     */
    public KeyboardInput(boolean configOne){
        this.hasConfigOne = configOne;
        mapping();

    }

    /**
     * Updates the input state.
     * This method is called every frame to update the input values.
     */
    @Override
    public void update(){
        setInputValues();
    }

    /**
     * Checks if the player has interacted.
     *
     * @return true if the player has interacted, false otherwise.
     */
    @Override
    public boolean interacted(){
        return !hasPlayer && (key(KEY_A) || key(KEY_B) || key(KEY_UP) || key(KEY_DOWN) || key(KEY_LEFT) || key(KEY_RIGHT));
    }

    /**
     * Sets the input values based on the current state of the keys.
     */
    private void setInputValues(){
        LEFT = key(KEY_LEFT)?1:0;
        RIGHT = key(KEY_RIGHT)?1:0;
        UP = key(KEY_UP)?1:0;
        DOWN = key(KEY_DOWN)?1:0;
        A = key(KEY_A);
        B = key(KEY_B);
    }

    /**
     * Checks if the specified key is pressed.
     *
     * @param keyValue The key value to check.
     * @return true if the key is pressed, false otherwise.
     */
    private boolean key(int keyValue){
        return Gdx.input.isKeyPressed(keyValue);
    }

    /**
     * Maps the keys based on the configuration.
     */
    private void mapping(){
        if (hasConfigOne){
            KEY_LEFT = Keys.A;
            KEY_RIGHT = Keys.D;
            KEY_UP = Keys.W;
            KEY_DOWN = Keys.S;
            KEY_A = Keys.C;
            KEY_B = Keys.V;
        } else {
            KEY_LEFT = Keys.J;
            KEY_RIGHT = Keys.L;
            KEY_UP = Keys.I;
            KEY_DOWN = Keys.K;
            KEY_A = Keys.O;
            KEY_B = Keys.P;
        }
    }
}
