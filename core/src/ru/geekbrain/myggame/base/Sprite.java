package ru.geekbrain.myggame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Level;

import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.utils.Regions;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame; //текущий кадр
    private boolean isDestroed;


    public Sprite() {}

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = Regions.split(region, rows, cols, frames); //разбиваем текстуру утилитой Regions
    }

    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isDestroed() {
        return isDestroed;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(regions[frame],
                getLeft(), getBottom(), // позиция - левая нижняя точка
                halfWidth, halfHeight, //точка вращения
                getWigth(), getHeight(), //ширина, высота
                scale, scale, //масштабирование по x и по y
                angle); //угол вращения
    }

    public void resize(Rect worldBounds){}

    public void update(float delta) {}


    public boolean touchDown(Vector2 touch, int pointer) {

        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {

        return false;
    }

    public void destroy() {
        isDestroed = true;
    }

    public void hide() {isDestroed = true;}

    public void flushDesrtoy() {
        isDestroed = false;
    }
}
