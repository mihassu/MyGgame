package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.Sprite;

public class Explosion extends Sprite {

    private float exploseTimer;
    private float exploseInterval = 0.02f;
    private Vector2 v;
    private Sound explosionSound;

    public Explosion() {
    }

    public Explosion(TextureAtlas atlas, Sound explosionSound) {
        super(atlas.findRegion("explosion"), 9, 9, 74);
        this.explosionSound = explosionSound;
    }

    public void set(float height, Vector2 pos0, Vector2 v) {
        explosionSound.play();
        setHeightProportion(height);
        this.pos.set(pos0);
        this.v = v;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta); //чтобы взрыв двигался

        exploseTimer += delta;
        if (exploseTimer >= exploseInterval) {
            exploseTimer = 0f;

            if(++frame == regions.length) {
                destroy();
            }
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0; //после окончания взрыва переходим на первый кадр
    }
}
