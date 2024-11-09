package org.auSpherical.auFight.inputs;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerInputTest {
    private ControllerInput controllerInput;
    private MockXInputDevice mockDevice;
    private MockXInputAxes mockAxes;
    private MockXInputButtons mockButtons;
    private MockXInputButtonsDelta mockButtonsDelta;
    private MockXInputComponents lastComps;


    @BeforeEach
    void setUp() throws XInputNotLoadedException {
        mockDevice = new MockXInputDevice();
        mockAxes = new MockXInputAxes();
        mockButtons = new MockXInputButtons();
        mockButtonsDelta = new MockXInputButtonsDelta();
        lastComps = new MockXInputComponents(mockAxes, mockButtons);

        mockDevice.setComponents(lastComps);
        mockDevice.setDelta(new MockXInputComponentsDelta(mockButtonsDelta, lastComps));

        controllerInput = new ControllerInput(mockDevice);

    }

    @Test
void interactedReturnsTrueWhenMultipleButtonsPressed() {
    mockButtons.a = true;
    mockButtons.b = true;
    controllerInput.update();
    System.out.println(controllerInput.DEVICE.getComponents().getButtons());
    assertTrue(controllerInput.interacted());
}

@Test
void interactedReturnsFalseWhenNoButtonsPressed() {
    mockButtons.a = false;
    mockButtons.b = false;
    controllerInput.update();
    assertFalse(controllerInput.interacted());
}

@Test
void leftTriggerSetsCorrectValueWhenPressed() {
    mockButtonsDelta.setPressed(XInputButton.LEFT_SHOULDER, true);
    mockAxes.lt = 0.4f;
    controllerInput.update();
    assertTrue(controllerInput.LT);
}

@Test
void rightTriggerSetsCorrectValueWhenPressed() {
    mockButtonsDelta.setPressed(XInputButton.RIGHT_SHOULDER, true);
    mockAxes.rt = 0.4f;
    controllerInput.update();
    assertTrue(controllerInput.RT);
}

@Test
void leftShoulderSetsCorrectValueWhenPressed() {
    mockButtonsDelta.setPressed(XInputButton.LEFT_SHOULDER, true);
    controllerInput.update();
    assertTrue(controllerInput.LB);
}

@Test
void rightShoulderSetsCorrectValueWhenPressed() {
    mockButtonsDelta.setPressed(XInputButton.RIGHT_SHOULDER, true);
    controllerInput.update();
    assertTrue(controllerInput.RB);
}

@Test
void rightDirectionalValueSetCorrectlyWhenAxisMoved() {
    mockAxes.lx = 0.5f;
    controllerInput.update();
    assertEquals(0.5f, controllerInput.RIGHT);
}

@Test
void downDirectionalValueSetCorrectlyWhenAxisMoved() {
    mockAxes.ly = -0.5f;
    controllerInput.update();
    assertEquals(0.5f, controllerInput.DOWN);
}

@Test
void leftDirectionalValueSetCorrectlyWhenAxisMoved() {
    mockAxes.lx = -0.5f;
    controllerInput.update();
    assertEquals(0.5f, controllerInput.LEFT);
}

@Test
void upDirectionalValueSetCorrectlyWhenAxisMoved() {
    mockAxes.ly = 0.5f;
    controllerInput.update();
    assertEquals(0.5f, controllerInput.UP);
}

@Test
void rightDirectionalValueSetCorrectlyWhenAxisInDeadZone() {
    mockAxes.lx = 0.1f;
    controllerInput.update();
    assertEquals(0, controllerInput.RIGHT);
}

@Test
void downDirectionalValueSetCorrectlyWhenAxisInDeadZone() {
    mockAxes.ly = -0.1f;
    controllerInput.update();
    assertEquals(0, controllerInput.DOWN);
}

@Test
void leftDirectionalValueSetCorrectlyWhenAxisInDeadZone() {
    mockAxes.lx = -0.1f;
    controllerInput.update();
    assertEquals(0, controllerInput.LEFT);
}

@Test
void upDirectionalValueSetCorrectlyWhenAxisInDeadZone() {
    mockAxes.ly = 0.1f;
    controllerInput.update();
    assertEquals(0, controllerInput.UP);
}
}

class MockXInputDevice extends XInputDevice {
    private XInputComponents components;
    private XInputComponentsDelta delta;

    public MockXInputDevice() {
        super(1); // Assuming 1 is a valid player number
    }

    public void setComponents(XInputComponents components) {
        this.components = components;
    }

    public void setDelta(XInputComponentsDelta delta) {
        this.delta = delta;
    }

    @Override
    public XInputComponents getComponents() {
        return components;
    }

    @Override
    public XInputComponentsDelta getDelta() {
        return delta;
    }
}

class MockXInputComponents extends XInputComponents {
    private final XInputAxes axes;
    private final XInputButtons buttons;

    public MockXInputComponents(XInputAxes axes, XInputButtons buttons) {
        this.axes = axes;
        this.buttons = buttons;
    }

    @Override
    public XInputAxes getAxes() {
        return axes;
    }

    @Override
    public XInputButtons getButtons() {
        return buttons;
    }
}

class MockXInputComponentsDelta extends XInputComponentsDelta {
    private final XInputButtonsDelta buttonsDelta;

    public MockXInputComponentsDelta(XInputButtonsDelta buttonsDelta, XInputComponents lastComps) {
        super(lastComps, lastComps);
        this.buttonsDelta = buttonsDelta;
    }

    @Override
    public XInputButtonsDelta getButtons() {
        return buttonsDelta;
    }
}

class MockXInputAxes extends XInputAxes {
    public float lx, ly, lt, rt;
}

class MockXInputButtons extends XInputButtons {
    public boolean a, b, x, y, lShoulder, rShoulder, up, down, left, right;
}

class MockXInputButtonsDelta extends XInputButtonsDelta {
    private final boolean[] pressed = new boolean[XInputButton.values().length];

    protected MockXInputButtonsDelta(XInputButtons lastButtons, XInputButtons buttons) {
        super(lastButtons, buttons);
    }

    public MockXInputButtonsDelta() {
        super(null, null);
    }

    public void setPressed(XInputButton button, boolean isPressed) {
        pressed[button.ordinal()] = isPressed;
    }

    @Override
    public boolean isPressed(XInputButton button) {
        return pressed[button.ordinal()];
    }
}