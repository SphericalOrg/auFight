package org.auSpherical.auFight;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ResourceManager {

    public static AssetManager assetManager = new AssetManager();

    public static void loadAllResources() {
        assetManager.load("cat.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }

    public static boolean update() {
        return assetManager.update();
    }

    public static TextureRegion getRegion(String name) {
        return assetManager.get("cat.atlas", TextureAtlas.class).findRegion(name);
    }

    public static Animation<TextureRegion> createAnimation(String baseName, int frameCount, float frameDuration) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= frameCount; i++) {
            frames.add(ResourceManager.getRegion(baseName + i));
        }
        return new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
