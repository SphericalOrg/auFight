package org.auSpherical.auFight;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Manages game resources, including textures and animations.
 * This class handles loading, retrieving, and disposing resources.
 */
public class ResourceManager {

    public static AssetManager assetManager = new AssetManager();

    /**
     * Loads all game resources. This method should be called once at the start.
     */
    public static void loadAllResources() {
        assetManager.load("cat.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }

    /**
     * Updates the asset manager to load assets.
     *
     * @return true if all assets are loaded, false otherwise.
     */
    public static boolean update() {
        return assetManager.update();
    }

    /**
     * Retrieves a texture region from the atlas by name.
     *
     * @param name The name of the texture region to retrieve.
     * @return The texture region corresponding to the given name.
     */
    public static TextureRegion getRegion(String name) {
        return assetManager.get("cat.atlas", TextureAtlas.class).findRegion(name);
    }

    /**
     * Creates an animation from a series of texture regions.
     *
     * @param baseName The base name of the texture regions.
     * @param frameCount The number of frames in the animation.
     * @param frameDuration The duration of each frame in the animation.
     * @return The created animation.
     */
    public static Animation<TextureRegion> createAnimation(String baseName, int frameCount, float frameDuration) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= frameCount; i++) {
            frames.add(ResourceManager.getRegion(baseName + i));
        }
        return new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
    }

    /**
     * Disposes of the asset manager and all resources it manages.
     */
    public static void dispose() {
        assetManager.dispose();
    }
}
