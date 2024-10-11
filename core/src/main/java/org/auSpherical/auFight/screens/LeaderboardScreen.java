package org.auSpherical.auFight.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.auSpherical.auFight.AuFight;
import org.auSpherical.auFight.data.LeaderManager;

public class LeaderboardScreen extends AbstractScreen {

    public LeaderboardScreen(AuFight main) {
        super(main);
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Table rootTable = new Table(skin);
        rootTable.setBackground("window");
        rootTable.setFillParent(true);
        rootTable.defaults().align(Align.center);
        rootTable.top().pad(100f);
        rootTable.defaults().space(50f);
        addActor(rootTable);


        Label titleLabel = new Label("Leaderboard", skin);
        titleLabel.setFontScale(5f);
        rootTable.add(titleLabel).colspan(2).padBottom(20f);
        rootTable.row();

        Table scoreTable = new Table(skin);
        scoreTable.defaults().space(20f).pad(10f);

        scoreTable.add(new Label("Jugador", skin)).padBottom(10f);
        scoreTable.add(new Label("Puntaje", skin)).padBottom(10f);
        scoreTable.row();

        LeaderManager leaderManager = new LeaderManager();
        leaderManager.getLeaderboard().forEach( (x,y) -> {
            scoreTable.add(new Label(x+" ", skin));
            scoreTable.add(new Label(y+"", skin));
            scoreTable.row();
        });

        rootTable.add(scoreTable).colspan(2).padBottom(30f);
        rootTable.row();

        TextButton backButton = new TextButton("Volver al Menu", skin);
        backButton.setTransform(true);
        backButton.setScale(2f);
        rootTable.add(backButton);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen("menu");
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
