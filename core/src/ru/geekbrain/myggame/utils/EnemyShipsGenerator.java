package ru.geekbrain.myggame.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.math.Rnd;
import ru.geekbrain.myggame.pool.EnemyShipsPool;
import ru.geekbrain.myggame.sprite.EnemyShip;

public class EnemyShipsGenerator {

    private static final float ENEMY_SMALL_HEIGHT = 0.15f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.5f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.03f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.4f;
    private static final int ENEMY_MEDIUM_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 2;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.05f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_BIG_HP = 3;

    private Rect worldBounds;

    private float generateInterval = 1f; //частота появления врагов
    private float generateTimer;

    private TextureRegion[] enemySmallRegion; // текстура врагов
    private TextureRegion[] enemyMediumRegion; // текстура врагов
    private TextureRegion[] enemyBigRegion; // текстура врагов


    //скорости врагов (случайная величина по Х, чтобы корабль летел не прямо)
    //    private final Vector2 enemySmallV = new Vector2(0, -0.2f); //скорость врагов
    private final Vector2 enemySmallV = new Vector2(Rnd.nextFloat(-0.3f, 0.3f), -0.3f);
    private final Vector2 enemyMediumV = new Vector2(Rnd.nextFloat(-0.2f, 0.2f), -0.2f);
    private final Vector2 enemyBigV = new Vector2(Rnd.nextFloat(-0.1f, 0.1f), -0.1f);

    private final TextureRegion bulletRegion; //текстура пули врагов
    private final EnemyShipsPool enemyShipsPool;

    public EnemyShipsGenerator(TextureAtlas atlas, EnemyShipsPool enemyShipsPool, Rect worldBounds) {
        TextureRegion enemySmallTexture = atlas.findRegion("enemy0");//находим в атласе текстуру
        this.enemySmallRegion = Regions.split(enemySmallTexture, 1, 2, 2); //разбиваем ее на массив

        TextureRegion enemyMediumTexture = atlas.findRegion("enemy1");//находим в атласе текстуру
        this.enemyMediumRegion = Regions.split(enemyMediumTexture, 1, 2, 2); //разбиваем ее на массив

        TextureRegion enemyBigTexture = atlas.findRegion("enemy2");//находим в атласе текстуру
        this.enemyBigRegion = Regions.split(enemyBigTexture, 1, 2, 2); //разбиваем ее на массив

        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.enemyShipsPool = enemyShipsPool;
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyShipsPool.obtain();

            float enemyType = (float) Math.random(); //генерируестся случайное число
            if (enemyType < 0.5f) { //в зависимости от случайного числа герерируется корабль врагов
                enemyShip.set(
                        enemySmallRegion,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP
                );
            } else if (enemyType < 0.8f) { //в зависимости от случайного числа герерируется корабль врагов
                enemyShip.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_DAMAGE,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP
                );
            } else { //в зависимости от случайного числа герерируется корабль врагов
                enemyShip.set(
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP
                );
            }

            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth()); // позиция x появления врага
            enemyShip.setBottom(worldBounds.getTop()); // корабль появляется сверху за границей экрана
        }
    }
}
