package org.auSpherical.auFight.inputs;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.XInputAxis;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;

public class ControllerInput extends PlayerInput {
    private static final double DEAD_ZONE = 0.133;
    private static final double TRIGGER_DEAD_ZONE = 0.3;
    public final XInputDevice DEVICE;
    private final XInputAxes AXES;
    private XInputButtons BUTTONS;
    private final XInputButtonsDelta BUTTONS_DELTA;
    private boolean leftTriggerFuse = true;
    private boolean rightTriggerFuse = true;
    public boolean LT;
    public boolean LB;
    public boolean RT;
    public boolean RB;
    public ControllerInput(XInputDevice device) throws XInputNotLoadedException {
        DEVICE = device;
        AXES = DEVICE.getComponents().getAxes();
        BUTTONS = DEVICE.getComponents().getButtons();
        BUTTONS_DELTA = DEVICE.getDelta().getButtons();
    }
    @Override
    public void update(){
        DEVICE.poll();
        setInputValues();
    }
    @Override
    public boolean interacted(){
        update();
        return !hasPlayer && anyPressed();
    }
    private boolean anyPressed() {return anyButtonsPressed() || anyDirectionsPressed() || anyTriggersPressed();}

    private boolean anyTriggersPressed(){
        return BUTTONS_DELTA.isPressed(XInputButton.LEFT_SHOULDER)||BUTTONS_DELTA.isPressed(XInputButton.RIGHT_SHOULDER)
                || getTrigger(XInputAxis.LEFT_TRIGGER) || getTrigger(XInputAxis.RIGHT_TRIGGER);
    }

    private boolean anyButtonsPressed(){
        return BUTTONS.a || BUTTONS.b || BUTTONS.x || BUTTONS.y || BUTTONS.start || BUTTONS.back ||
                BUTTONS_DELTA.isPressed(XInputButton.A) || BUTTONS_DELTA.isPressed(XInputButton.B) ||
                BUTTONS_DELTA.isPressed(XInputButton.X) || BUTTONS_DELTA.isPressed(XInputButton.Y) ||
                BUTTONS_DELTA.isPressed(XInputButton.START) || BUTTONS_DELTA.isPressed(XInputButton.BACK);
    }

    private boolean anyDirectionsPressed(){
        return BUTTONS.up || BUTTONS.down || BUTTONS.left || BUTTONS.right ||
                BUTTONS_DELTA.isPressed(XInputButton.DPAD_UP) || BUTTONS_DELTA.isPressed(XInputButton.DPAD_DOWN) ||
                BUTTONS_DELTA.isPressed(XInputButton.DPAD_LEFT) || BUTTONS_DELTA.isPressed(XInputButton.DPAD_RIGHT);
    }

    private void setInputValues() {
        updateButtonsValues();
        updateDirectionalValues();
        updateTriggersValues();
    }

    private void updateButtonsValues(){
        A = BUTTONS_DELTA.isPressed(XInputButton.A);
        B = BUTTONS_DELTA.isPressed(XInputButton.B);
    }

    private void updateDirectionalValues(){
        updateHorizontalValues();
        updateVerticalValues();
    }

    private void updateVerticalValues(){
        UP = Math.max(valueDeltaY(), 0);
        DOWN = Math.max(-valueDeltaY(), 0);
    }

    private void updateHorizontalValues(){
        RIGHT = Math.max(valueDeltaX(), 0);
        LEFT = Math.max(-valueDeltaX(), 0);
    }

    private void updateTriggersValues(){
        LB = BUTTONS_DELTA.isPressed(XInputButton.LEFT_SHOULDER);
        RB = BUTTONS_DELTA.isPressed(XInputButton.RIGHT_SHOULDER);

        LT = leftTriggerFuse && getTrigger(XInputAxis.LEFT_TRIGGER);
        leftTriggerFuse = !LT && !getTrigger(XInputAxis.LEFT_TRIGGER);

        RT = rightTriggerFuse && getTrigger(XInputAxis.RIGHT_TRIGGER);
        rightTriggerFuse = !RT && !getTrigger(XInputAxis.RIGHT_TRIGGER);;
    }
    private boolean getTrigger(XInputAxis axis){return AXES.get(axis) >= TRIGGER_DEAD_ZONE;}
    private float valueDeltaX(){return dPadX()!=0? dPadX():leftStickX();}
    private float valueDeltaY(){return dPadY()!=0? dPadY():leftStickY();}
    private float dPadY(){return Boolean.compare(BUTTONS.up, BUTTONS.down);}
    private float dPadX(){return Boolean.compare(BUTTONS.right, BUTTONS.left);}
    private float leftStickX() {return Math.abs(AXES.get(XInputAxis.LEFT_THUMBSTICK_X)) > DEAD_ZONE ? AXES.get(XInputAxis.LEFT_THUMBSTICK_X) : 0;}
    private float leftStickY() {return Math.abs(AXES.get(XInputAxis.LEFT_THUMBSTICK_Y)) > DEAD_ZONE ? AXES.get(XInputAxis.LEFT_THUMBSTICK_Y) : 0;}
}
