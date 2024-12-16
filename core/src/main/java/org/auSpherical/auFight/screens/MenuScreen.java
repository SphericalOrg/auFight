package org.auSpherical.auFight.screens;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.auSpherical.auFight.AuFight;


public class MenuScreen extends AbstractScreen {

    private final Skin skin;
    public Table rootTable;

    public MenuScreen(AuFight main) {
        super(main);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        setRootTable();

        setTitle();

        setButtons();

    }

    public void setButtons(){
        TextButton button1 = createButton("button1","Partida Rapida");
        TextButton button2 = createButton("button2", "Marcadores");
        TextButton button3 = createButton("button3","Salir");

        setElementOnTable(button1);
        setElementOnTable(button2);
        setElementOnTable(button3);

        button1.addListener(createDialogListener());
        button2.addListener(createLeaderListener());
        button3.addListener(createExitListener());
    }

    public TextButton createButton(String buttonName, String buttonText){
        TextButton button = new TextButton(buttonText, skin);
        button.setTransform(true);  // Permitir transformación
        button.setScale(2f);  // Escalar el botón a 7.5 veces su tamaño normal
        button.setName(buttonName);
        button.setProgrammaticChangeEvents(true);
        return button;
    }

    public void setRootTable(){
        rootTable = new Table(skin);
        rootTable.setBackground("window");
        rootTable.setFillParent(true);
        rootTable.defaults().align(Align.left);
        rootTable.left().top();
        rootTable.pad(150f);
        rootTable.defaults().space(50f);
        addActor(rootTable);
    }

    public void setTitle(){
        Label label = new Label("auFight", skin);
        label.setName("gameTitle");
        label.setFontScale(7.5f);  // Escalar el texto para que sea grande
        label.setAlignment(Align.center);
        rootTable.add(label).colspan(3).padBottom(20f);  // Añadir el texto en el centro
        rootTable.row();
    }

    public void setElementOnTable(Actor actor){
        rootTable.add(actor).pad(10f).row();
    }

    private Dialog createDialog(TextField usernameField, TextField username2Field) {
        Dialog inputDialog = new Dialog("Ventana de Inputs", skin) {
            @Override
            protected void result(Object object) {
                try {
                    main.changeScreen("GAME");
                } catch (XInputNotLoadedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        addDialogLogic(inputDialog,usernameField,username2Field);
        return inputDialog;
    }

    private void addDialogLogic(Dialog inputDialog, TextField usernameField, TextField username2Field ){
        inputDialog.setName("inputDialog");

        inputDialog.row();
        inputDialog.add(new Label("Jugador 1: ", skin));
        inputDialog.add(usernameField).width(200);

        inputDialog.add(new Label("Jugador 2: ", skin));
        inputDialog.add(username2Field).width(200);

        inputDialog.key(Keys.ENTER, true).key(Keys.ESCAPE, false).text("Ingrese Jugador 1 y Jugador 2:");
    }


    private ChangeListener createDialogListener() {
        return new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                TextField usernameField = new TextField("", skin);
                TextField username2Field = new TextField("", skin);

                Dialog inputDialog = createDialog(usernameField, username2Field);

                inputDialog.show(MenuScreen.this);
            }
        };
    }

    private ChangeListener createLeaderListener() {
        return new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                try {
                    main.changeScreen("TOP");
                } catch (XInputNotLoadedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private ChangeListener createExitListener() {
        return new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}