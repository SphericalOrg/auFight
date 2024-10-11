// LeaderManager.java
package org.auSpherical.auFight.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

public class LeaderManager {

    private Leader leader;

    public LeaderManager(){
        FileHandle fileHandle = Gdx.files.local("data/leaderboard.json");
        Json json = new Json();
        if (!fileHandle.exists()) {
            Leader defaultLeader = new Leader("Default Leader");
            fileHandle.writeString(json.toJson(defaultLeader, Leader.class), false);
        }
        leader = json.fromJson(Leader.class, fileHandle.readString());
    }

    public HashMap<Object, Object> getLeaderboard(){
        return leader.puntuation;
    }
}
