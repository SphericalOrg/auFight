package org.auSpherical.auFight.inputs;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;

/**
 * Handles controller input for a player.
 */
public class ControllerInput extends PlayerInput {
    private final double DEAD_ZONE = 0.133;
    private final double TRIGGER_DEAD_ZONE = 0.3;
    private final XInputDevice DEVICE;
    private final XInputAxes AXES;
    private final XInputButtons BUTTONS;
    private final XInputButtonsDelta BUTTONS_DELTA;
    private boolean leftTriggerFuse = true;
    private boolean rightTriggerFuse = true;
    public boolean LT;
    public boolean LB;
    public boolean RT;
    public boolean RB;

    /**
     * Constructs a ControllerInput for the specified player number.
     *
     * @param playerNum The player number.
     * @throws XInputNotLoadedException If the XInput library is not loaded.
     */
    public ControllerInput(int playerNum) throws XInputNotLoadedException {
        DEVICE = XInputDevice.getDeviceFor(playerNum);
        AXES = DEVICE.getComponents().getAxes();
        BUTTONS = DEVICE.getComponents().getButtons();
        BUTTONS_DELTA = DEVICE.getDelta().getButtons();
    }

    /**
     * Updates the input state.
     * This method is called every frame to update the input values.
     */
    @Override
    public void update(){
        poll();
        setInputValues();
    }

    /**
     * Checks if the player has interacted.
     *
     * @return true if the player has interacted, false otherwise.
     */
    @Override
    public boolean interacted(){
        update();
        return !hasPlayer && anyPressed();
    }

    /**
     * Checks if any buttons or axes are pressed.
     *
     * @return true if any buttons or axes are pressed, false otherwise.
     */
    private boolean anyPressed(){
        return
                BUTTONS.b||BUTTONS.a||BUTTONS.x||BUTTONS.y|| BUTTONS.rShoulder || BUTTONS.lShoulder ||
                        valueDeltaY()!=0 || valueDeltaX()!=0 || AXES.lt>= TRIGGER_DEAD_ZONE || AXES.rt>= TRIGGER_DEAD_ZONE;
    }

    /**
     * Sets the input values based on the current state of the controller.
     */
    private void setInputValues() {
        A = BUTTONS_DELTA.isPressed(XInputButton.A);
        B = BUTTONS_DELTA.isPressed(XInputButton.B);
        leftRight();
        upDown();
        triggers();
    }

    /**
     * Polls the device to update the input state.
     */
    private void poll(){
        DEVICE.poll();}

    /**
     * Updates the left and right input values based on the controller's state.
     */
    private void leftRight(){
        if (valueDeltaX()>0){
            RIGHT = valueDeltaX();
            LEFT = 0;
        } else if (valueDeltaX()<0) {
            LEFT = -valueDeltaX();
            RIGHT = 0;
        } else {
            LEFT = 0;
            RIGHT = 0;
        }
    }

    /**
     * Updates the up and down input values based on the controller's state.
     */
    private void upDown(){
        if (valueDeltaY()>0){
            UP = valueDeltaY();
            DOWN = 0;
        } else if (valueDeltaY()<0) {
            DOWN = -valueDeltaY();
            UP = 0;
        } else {
            UP = 0;
            DOWN = 0;
        }
    }

    /**
     * Updates the trigger input values based on the controller's state.
     */
    private void triggers(){
        LB = BUTTONS_DELTA.isPressed(XInputButton.LEFT_SHOULDER);

        LT = leftTriggerFuse && AXES.lt>= TRIGGER_DEAD_ZONE;
        leftTriggerFuse = !LT && AXES.lt< TRIGGER_DEAD_ZONE;

        RB = BUTTONS_DELTA.isPressed(XInputButton.RIGHT_SHOULDER);

        RT = rightTriggerFuse && AXES.rt >= TRIGGER_DEAD_ZONE;
        rightTriggerFuse = !RT && AXES.rt < TRIGGER_DEAD_ZONE;
    }

    /**
     * Calculates the delta X value based on the D-pad and left stick.
     *
     * @return The delta X value.
     */
    private float valueDeltaX(){return dPadX()!=0? dPadX():leftStickX();}

    /**
     * Calculates the delta Y value based on the D-pad and left stick.
     *
     * @return The delta Y value.
     */
    private float valueDeltaY(){return dPadY()!=0? dPadY():leftStickY();}

    /**
     * Returns the D-pad Y value.
     *
     * @return The D-pad Y value.
     */
    private float dPadY(){return Boolean.compare(BUTTONS.up, BUTTONS.down);}

    /**
     * Returns the D-pad X value.
     *
     * @return The D-pad X value.
     */
    private float dPadX(){return Boolean.compare(BUTTONS.right, BUTTONS.left);}

    /**
     * Returns the left stick X value.
     *
     * @return The left stick X value.
     */
    private float leftStickX() {return Math.abs(AXES.lx) > DEAD_ZONE ? AXES.lx : 0;}

    /**
     * Returns the left stick Y value.
     *
     * @return The left stick Y value.
     */
    private float leftStickY() {return Math.abs(AXES.ly) > DEAD_ZONE ? AXES.ly : 0;}
}

