package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.math.Rnd;
import ru.geekbrain.myggame.utils.Regions;

public class EnemyShip extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();

    public EnemyShip() {
        regions = new TextureRegion[2];
        float vx = Rnd.nextFloat(-0.5f, 0.5f);
        float vy = Rnd.nextFloat(-0.5f, -0.1f);
        v = new Vector2(vx, vy);
    }

    public void set(TextureAtlas atlas, Vector2 pos0, float height, Rect worldBounds){
        this.regions = Regions.split(atlas.findRegion("enemy2"), 1 , 2, 2);
        this.pos.set(pos0);
        setHeightProportion(height); //установить размер
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if(isOutside(worldBounds)) {
            destroy();
        }
        if(getRight() > worldBounds.getRight() || getLeft() < worldBounds.getLeft()) {
            v.x = -v.x;
        }

    }
}
