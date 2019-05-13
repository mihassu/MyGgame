package ru.geekbrain.myggame.pool;

import ru.geekbrain.myggame.base.SpritesPool;
import ru.geekbrain.myggame.sprite.EnemyShip;

public class EnemyShipsPool extends SpritesPool<EnemyShip> {

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip();
    }
}
