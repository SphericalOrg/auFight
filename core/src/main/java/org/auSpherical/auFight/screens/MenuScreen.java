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

    public MenuScreen(AuFight main) {
        super(main);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Crear tabla raíz
        Table rootTable = new Table(skin);
        rootTable.setBackground("window");
        rootTable.setFillParent(true);
        rootTable.defaults().align(Align.left);
        rootTable.left().top();
        rootTable.pad(150f);
        rootTable.defaults().space(50f);
        addActor(rootTable);

        //Texture backgoround = new Texture(Gdx.files.internal("patrick.png"));  // Asegúrate de tener esta imagen

        //Sprite sprite = new Sprite(backgoround);


        Label label = new Label("auFight", skin);
        label.setFontScale(7.5f);  // Escalar el texto para que sea grande
        label.setAlignment(Align.center);
        rootTable.add(label).colspan(3).padBottom(20f);  // Añadir el texto en el centro
        rootTable.row();

        TextButton button1 = new TextButton("Partida Rapida", skin);
        TextButton button2 = new TextButton("Marcadores", skin);
        TextButton button3 = new TextButton("Salir", skin);

        button1.setTransform(true);  // Permitir transformación
        button1.setScale(2f);  // Escalar el botón a 7.5 veces su tamaño normal

        button2.setTransform(true);
        button2.setScale(2f);

        button3.setTransform(true);
        button3.setScale(2f);

        // Añadir los botones a la tabla
        rootTable.add(button1).pad(10f);
        rootTable.row();
        rootTable.add(button2).pad(10f);
        rootTable.row();
        rootTable.add(button3).pad(10f);
        rootTable.row();

        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor)
            {
                TextField usernameField = new TextField("", skin);
                TextField username2Field = new TextField("", skin);

                Dialog inputDialog = ventanDialogo(usernameField, username2Field);
                inputDialog.row();
                inputDialog.add(new Label("Jugador 1: ", skin));
                inputDialog.add(usernameField).width(200);

                inputDialog.add(new Label("Jugador 2: ", skin));
                //passwordField.setPasswordMode(false);  // Modo de contraseña (oculta texto)
                //passwordField.setPasswordCharacter('*');
                inputDialog.add(username2Field).width(200);

                inputDialog.key(Keys.ENTER, true);  // Asignar Enter para confirmar
                inputDialog.key(Keys.ESCAPE, false);  // Asignar Escape para cancelar

                System.out.println(username2Field.getText());
                // Mostrar el diálogo
                inputDialog.show(MenuScreen.this);
            }
        });

        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Botón 2 presionado");
                try {
                    main.changeScreen("TOP");
                } catch (XInputNotLoadedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                System.out.println("Botón 3 presionado");
                Gdx.app.exit();
            }
        });

        System.out.println(rootTable.setZIndex(1));
    }

    private Dialog ventanDialogo(TextField usernameField, TextField passwordField) {
        Dialog inputDialog = new Dialog("Ventana de Inputs", skin) {
            @Override
            protected void result(Object object) {
                String user = usernameField.getText();
                String user2 = passwordField.getText();

                System.out.println("Botón presionado: " + object);
                System.out.println("\n"+ user2);
                System.out.println("\n"+ user);

                try {
                    main.changeScreen("GAME");
                } catch (XInputNotLoadedException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        inputDialog.text("Ingrese Jugador 1 y Jugador 2:");
        return inputDialog;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //main.batch.begin();
        //sprite.setPosition(0, 0);  // Posicionar el fondo en la esquina inferior izquierda
        //sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //sprite.draw(main.batch, 0.5f);
        //main.batch.end();
        //super.render(delta);  // Llama a la función render del padre, que dibuja la UI
    }
}
