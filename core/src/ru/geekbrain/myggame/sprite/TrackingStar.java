package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class TrackingStar extends Star {

    private final Vector2 trackigV; //скорость корабля
    private final Vector2 sumV = new Vector2();

    public TrackingStar(TextureAtlas atlas, Vector2 trackigV) {
        super(atlas);
        this.trackigV = trackigV;
    }

    @Override
    public void update(float delta) {
        sumV.setZero().mulAdd(trackigV, 0.2f).rotate(180f).add(v);
        pos.mulAdd(sumV, delta);
        checkBounds();
    }
}
