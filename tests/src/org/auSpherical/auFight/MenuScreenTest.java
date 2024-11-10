package org.auSpherical.auFight;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.auSpherical.auFight.environment.GraphicTestRunner;
import org.auSpherical.auFight.screens.MenuScreen;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuScreenTest {

    private GraphicTestRunner graphicTestRunner;
    private MenuScreen menuScreen;

    @BeforeEach
    public void setUp() {
        graphicTestRunner = new GraphicTestRunner();
        graphicTestRunner.setUp();
        menuScreen = (MenuScreen) GraphicTestRunner.game.getScreen();
    }


    @Test
    public void testMenuScreenNotNull() {
        assertNotNull(menuScreen, "MenuScreen should not be null");
    }

    @Test
    public void titleLabelExists(){
        Label gameTitle = menuScreen.rootTable.findActor("gameTitle");
        assertNotNull(gameTitle,"Label 'gameTitle' should exists in the MenuScreen");
        assertEquals(Label.class, gameTitle.getClass(),"Label 'gameTitle' should be of type Label");
    }

    @Test
    public void testButton1Exists() {
        Button button1 = menuScreen.rootTable.findActor("button1");
        assertNotNull(button1, "Button 'button1' should exist in the MenuScreen");
        assertEquals(TextButton.class, button1.getClass(), "Button 'button1' should be of type TextButton");
    }

    @Test
    public void testButton2Exists() {
        Button button2 = menuScreen.rootTable.findActor("button2");
        assertNotNull(button2, "Button 'button2' should exist in the MenuScreen");
        assertEquals(TextButton.class, button2.getClass(), "Button 'button2' should be of type TextButton");
    }

    @Test
    public void testButton3Exists() {
        Button button3 = menuScreen.rootTable.findActor("button3");
        assertNotNull(button3, "Button 'button3' should exist in the MenuScreen");
        assertEquals(TextButton.class, button3.getClass(), "Button 'button3' should be of type TextButton");
    }

    @Test
    public void testButton1Dialog() {
        TextButton textButton = menuScreen.rootTable.findActor("button1");
        assertNotNull(textButton, "Button 'button1' should exist in the MenuScreen");

        textButton.toggle();
        textButton.fire(new ChangeListener.ChangeEvent());

        Dialog dialog = menuScreen.getRoot().findActor("inputDialog");
        assertNotNull(dialog, "Dialog should not be null");
    }

   // @Test public void testButton2Listener() {}
   // @Test public void testButton3Listener() {}
}