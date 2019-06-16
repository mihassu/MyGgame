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
        //считаем новую сорость звезд (sumV): обнуляем, добавляем скорость крорабля и умножаем
        // на скаляр 0.2f(т.е. уменьшаем на 20%), поворачиваем на 180 град (т.о. добавляется
        // боковое движение звездам), добавляем скорость
        // по оси y чтобы звезды летели вниз тоже
        sumV.setZero().mulAdd(trackigV, 0.2f).rotate(180f).add(v);
        pos.mulAdd(sumV, delta);
        checkBounds();
    }
}
