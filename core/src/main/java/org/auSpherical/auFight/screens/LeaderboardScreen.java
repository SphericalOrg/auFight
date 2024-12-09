package org.auSpherical.auFight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import org.auSpherical.auFight.AuFight;
import org.auSpherical.auFight.data.Punctuation;

import java.util.ArrayList;
import java.util.Collection;

public class LeaderboardScreen extends AbstractScreen {

    private final Skin skin;

    private final Table rootTable;


    public LeaderboardScreen(AuFight main) {
        super(main);
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        rootTable = new Table(skin);
        setRootTable();

        Label label = new Label("Leaderboard", skin);
        label.setFontScale(7.5f);
        label.setAlignment(Align.center);
        rootTable.add(label).colspan(3).padBottom(20f);
        rootTable.row();



        setLeaderboard();

        addActor(rootTable);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }


        Table rootTable = new Table(skin);
    private TextButton backButton() {
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main));
            }
        });
        return backButton;
    }


    private Table mapLeaderboardTable(Collection<Punctuation> leaderboardList) {
        Table leaderboardTable = new Table(skin);
        leaderboardTable.defaults().align(Align.center);
        leaderboardTable.add(new Label("Name", skin)).padRight(50f);
        //leaderboardTable.add(new Label("Wins", skin)).padRight(50f);
        leaderboardTable.add(new Label("Punctuation", skin));
        leaderboardTable.row();

        if (leaderboardList.isEmpty()) {
            leaderboardTable.add(new Label("No data available", skin)).colspan(3);
        }

        leaderboardList.forEach(row -> {
            String name = row.getName();
            int wins = row.getWins();
            int punctuation = row.getPunctuation();

            Label nameLabel = new Label(name, skin);
            //Label winsLabel = new Label(String.valueOf(wins), skin);
            Label punctuationLabel = new Label(String.valueOf(punctuation), skin);

            leaderboardTable.add(nameLabel);
            //leaderboardTable.add(winsLabel);
            leaderboardTable.add(punctuationLabel);
            leaderboardTable.row();
        });
        return leaderboardTable;
    }


    private void setRootTable(){
        rootTable.setBackground("window");
        rootTable.setFillParent(true);
        rootTable.defaults().align(Align.left);
        rootTable.left().top();
        rootTable.pad(150f);
        rootTable.defaults().space(50f);
    }

    private void setLeaderboard(){

        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://localhost:8080/leaderboard/");
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                ArrayList<Punctuation> leaderboardList = new ArrayList<>();

                String responseJson = httpResponse.getResultAsString();
                if (responseJson.isEmpty()) {
                    Gdx.app.error("HTTP Request", "Empty response");
                    Table leaderboardTable = mapLeaderboardTable(leaderboardList);
                    rootTable.add(leaderboardTable).colspan(3);
                    rootTable.row();
                    return;
                }

                JsonValue root = new JsonReader().parse(responseJson);

                root.forEach(entry -> {
                    String name = entry.getString("name");
                    int wins = entry.getInt("wins");
                    int punctuation = entry.getInt("punctuation");
                    leaderboardList.add(new Punctuation(name, wins, punctuation));
                });

                leaderboardList.sort((a, b) -> b.getWins() - a.getWins());

                Table leaderboardTable = mapLeaderboardTable(leaderboardList);
                rootTable.add(leaderboardTable).colspan(3);
                rootTable.row();

                TextButton backButton = backButton();
                rootTable.add(backButton).colspan(3).padTop(50f);
                rootTable.row();

            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.error("HTTP Request", "Failed to get leaderboard data", t);
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HTTP Request", "Request cancelled");
            }
        });
    }
}