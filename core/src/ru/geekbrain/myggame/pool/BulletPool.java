package ru.geekbrain.myggame.pool;


import ru.geekbrain.myggame.base.SpritesPool;
import ru.geekbrain.myggame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {


    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
