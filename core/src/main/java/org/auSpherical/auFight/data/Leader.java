// Leader.java
package org.auSpherical.auFight.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.HashMap;

/**
 * Represents a Leader in the game.
 * This class handles the serialization and deserialization of leader data.
 */
public class Leader implements Json.Serializable {

    public String name;
    public HashMap<Object, Object> puntuation = new HashMap<>();

    /**
     * Default constructor.
     * Sets a default name for the leader.
     */
    public Leader() {
        this.name = "Default Name"; // Set a default name
    }

    /**
     * Constructs a Leader with the given name.
     *
     * @param name The name of the leader.
     */
    public Leader(String name) {
        this.name = name;
    }

    /**
     * Serializes the leader data to JSON.
     *
     * @param json The JSON object used for writing data.
     */
    @Override
    public void write(Json json) {
        json.writeValue("name", name);
        json.writeValue("puntuation", puntuation);
    }

    /**
     * Deserializes the leader data from JSON.
     *
     * @param json The JSON object used for reading data.
     * @param jsonData The JSON data to be read.
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        name = json.readValue("name", String.class, jsonData);
        puntuation = json.readValue(HashMap.class, Integer.class, jsonData.get("puntuation"));
    }
}
