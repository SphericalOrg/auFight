package org.auSpherical.auFight.inputs;

import com.github.strikerx3.jxinput.XInputAxes;
import com.github.strikerx3.jxinput.XInputButtons;
import com.github.strikerx3.jxinput.XInputButtonsDelta;
import com.github.strikerx3.jxinput.XInputComponents;
import com.github.strikerx3.jxinput.XInputDelta;
import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerInputTest {

    private XInputDevice mockedDevice;
    private XInputButtons mockedButtons;
    private XInputAxes mockedAxes;
    private XInputButtonsDelta mockedButtonsDelta;
    private XInputComponents mockedComponents;
    private XInputDelta mockedDelta;

    @BeforeEach
    void setUp() throws XInputNotLoadedException {
        mockedDevice = mock(XInputDevice.class);
        mockedButtons = mock(XInputButtons.class);
        mockedAxes = mock(XInputAxes.class);
        mockedButtonsDelta = mock(XInputButtonsDelta.class);
        mockedComponents = mock(XInputComponents.class);
        mockedDelta = mock(XInputDelta.class);

        when(mockedDevice.getComponents()).thenReturn(mockedComponents);
        when(mockedComponents.getButtons()).thenReturn(mockedButtons);
        when(mockedDevice.getDelta()).thenReturn(mockedDelta);
        when(mockedDelta.getButtons()).thenReturn(mockedButtonsDelta);
    }

    @Test
    void interacted_withButtonPressed_returnTrue() throws XInputNotLoadedException {
        mockedButtons.a = true;

        ControllerInput controllerInput = new ControllerInput(1) {
            protected XInputDevice getDeviceFor(int playerNum) {
                return mockedDevice;
            }
        };
        boolean result = controllerInput.interacted();

        assertTrue(result, "Expected result was true, as the controller was interacted with.");
    }

    @Test
    void interacted_noButtonPressed_returnFalse() throws XInputNotLoadedException {
        mockedButtons.a = false;

        ControllerInput controllerInput = new ControllerInput(1) {
            protected XInputDevice getDeviceFor(int playerNum) {
                return mockedDevice;
            }
        };
        boolean result = controllerInput.interacted();

        assertFalse(result, "Expected result was false, as the controller have not been interacted with.");
    }
}