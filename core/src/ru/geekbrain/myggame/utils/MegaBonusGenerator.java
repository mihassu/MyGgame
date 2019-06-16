package ru.geekbrain.myggame.utils;

import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.math.Rnd;
import ru.geekbrain.myggame.pool.MegaBonusPool;
import ru.geekbrain.myggame.sprite.MegaBonus;

public class MegaBonusGenerator {


    private float generateInterval = 10f; //частота появления
    private float generateTimer;

    private MegaBonusPool megaBonusPool;
    private Rect worldBounds;

    public MegaBonusGenerator(MegaBonusPool megaBonusPool, Rect worldBounds) {
        this.megaBonusPool = megaBonusPool;
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {

        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            MegaBonus megaBonus = (MegaBonus) megaBonusPool.obtain();

            megaBonus.pos.x = Rnd.nextFloat(worldBounds.getLeft() + megaBonus.getHalfWidth(),
                    worldBounds.getRight() - megaBonus.getHalfWidth()); // позиция x появления
            megaBonus.setBottom(worldBounds.getTop()); //  появляется сверху за границей экрана
        }
    }
}
