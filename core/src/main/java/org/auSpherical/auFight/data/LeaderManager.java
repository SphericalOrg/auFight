package org.auSpherical.auFight.data;import com.badlogic.gdx.Gdx;import com.badlogic.gdx.files.FileHandle;import com.badlogic.gdx.utils.Json;import java.util.HashMap;

/**
 * Manages the leaderboard for the game.
 * This class handles loading, saving, and retrieving leaderboard data.
 */
public class LeaderManager {

    private Leader leader;

    /**
     * Constructs a LeaderManager.
     * Loads the leaderboard data from a local JSON file. If the file does not exist, creates a default leader.
     */
    public LeaderManager(){
        FileHandle fileHandle = Gdx.files.local("data/leaderboard.json");
        Json json = new Json();
        if (!fileHandle.exists()) {
            Leader defaultLeader = new Leader("Default Leader");
            fileHandle.writeString(json.toJson(defaultLeader, Leader.class), false);
        }
        leader = json.fromJson(Leader.class, fileHandle.readString());
    }

    /**
     * Retrieves the leaderboard data.
     *
     * @return A HashMap containing the leaderboard data.
     */
    public HashMap<Object, Object> getLeaderboard(){
        return leader.puntuation;
    }
}
