package org.auSpherical.auFight.inputs;

/**
 * Abstract class representing player input.
 * This class handles the input state for player controls.
 */
public abstract class PlayerInput {
    public boolean hasPlayer = false;
    public float LEFT;
    public float RIGHT;
    public float UP;
    public float DOWN;
    public boolean A;
    public boolean B;

    /**
     * Updates the input state.
     * This method should be overridden by subclasses to implement specific input logic.
     */
    public void update(){}

    /**
     * Checks if the player has interacted.
     *
     * @return true if the player has interacted, false otherwise.
     */
    public boolean interacted(){return false;}
}
