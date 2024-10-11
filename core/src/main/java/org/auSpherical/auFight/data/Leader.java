// Leader.java
package org.auSpherical.auFight.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.HashMap;

public class Leader implements Json.Serializable {

    public String name;
    public HashMap<Object, Object> puntuation = new HashMap<>();

    // Default constructor
    public Leader() {
        this.name = "Default Name"; // Set a default name
    }

    // Constructor with name parameter
    public Leader(String name) {
        this.name = name;
    }

    @Override
    public void write(Json json) {
        json.writeValue("name", name);
        json.writeValue("puntuation", puntuation);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        name = json.readValue("name", String.class, jsonData);
        puntuation = json.readValue(HashMap.class, Integer.class, jsonData.get("puntuation"));
    }
}
