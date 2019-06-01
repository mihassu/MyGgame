package ru.geekbrain.myggame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    protected final List<T> activeObjects = new ArrayList<T>();
    protected final List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    //получить объект: либо новый, либо из пула
    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1); //remove - возвращает объект под индексом и удаляет его
        }

        activeObjects.add(object);
        System.out.println(getClass().getName() + " active/destroyed " + activeObjects.size() + ":" + freeObjects.size());
        return object;
    }

    //обновить активные объекты
    public void updateActiveSprites(float delta) {
        for (Sprite sprite: activeObjects) {
            if (!sprite.isDestroed()) { //обновляем только те объекты которые не уничтожены
                sprite.update(delta);
            }
        }
    }

    //отрисовать активные объекты
    public void drawActiveSprites(SpriteBatch batch) {
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if (!sprite.isDestroed()) { //отрисовываем только те объекты которые не уничтожены
                sprite.draw(batch);
            }
        }
    }

    //переместить все destroyed объекты в список свободных объектов
    public void freeAllDestroyedActiveSprites() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroed()) {
                free(sprite);
                i--;
                sprite.flushDesrtoy();
            }
        }
        System.out.println(getClass().getName() + " active/destroyed " + activeObjects.size() + ":" + freeObjects.size());

    }

    //освободить объект (переместить из пула активных в свободные)
    public void free(T object) {
        if(activeObjects.remove(object)) {
            freeObjects.add(object);
        }
    }

    //удалить все объекты из пула
    public void freeAllActiveSprites() {
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    public void dispose() {
        freeObjects.clear();
        activeObjects.clear();
    }
}
