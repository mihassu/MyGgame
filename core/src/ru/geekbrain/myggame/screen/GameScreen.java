package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.BaseScreen;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.pool.EnemyShipsPool;
import ru.geekbrain.myggame.sprite.Background;
import ru.geekbrain.myggame.sprite.ButtonLeft;
import ru.geekbrain.myggame.sprite.ButtonRight;
import ru.geekbrain.myggame.sprite.Ship;
import ru.geekbrain.myggame.sprite.Star;
import ru.geekbrain.myggame.utils.EnemyShipsGenerator;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Star[] stars;
    private final int STARS_COUNT = 100;
    private TextureAtlas atlas;
    private Ship ship;
    private Texture buttonRightTexture;
    private ButtonRight buttonRight;
    private Texture buttonLeftTexture;
    private ButtonLeft buttonLeft;
    private BulletPool bulletPool;

    private EnemyShipsPool enemyShipsPool;

    private Music music;
    private Sound bulletSound;
    private Sound enemyBulletSound;

    private EnemyShipsGenerator enemyShipsGenerator;

    @Override
    public void show() {
        super.show();

        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }


        bulletPool = new BulletPool();

        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        enemyBulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

        ship = new Ship(atlas, bulletPool, bulletSound);
        buttonRightTexture = new Texture("textures/arrow_right.png");
        buttonRight = new ButtonRight(new TextureRegion(buttonRightTexture), ship);

        buttonLeftTexture = new Texture("textures/arrow_left.png");
        buttonLeft = new ButtonLeft(new TextureRegion(buttonLeftTexture), ship);

        enemyShipsPool = new EnemyShipsPool(bulletPool, enemyBulletSound, worldBounds, ship);

        enemyShipsGenerator = new EnemyShipsGenerator(atlas, enemyShipsPool, worldBounds);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyedSprites();
        draw();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star s: stars) {
            s.draw(batch);
        }
        ship.draw(batch);
        buttonRight.draw(batch);
        buttonLeft.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyShipsPool.drawActiveSprites(batch);

        batch.end();
    }

    private void update(float delta) {
        for (Star s: stars) {
            s.update(delta);
        }
        ship.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyShipsPool.updateActiveSprites(delta);
        enemyShipsGenerator.generate(delta);


//        reloadTimer += delta;
//        if (reloadTimer >= reloadInterval) {
//            reloadTimer = 0f;
//            enemyAttack();
//        }


        //попадание пулей во врагов
        for (int i = 0; i < enemyShipsPool.getActiveObjects().size(); i++) {
            for (int j = 0; j < bulletPool.getActiveObjects().size(); j++) {
                if(enemyShipsPool.getActiveObjects().get(i).isMe(bulletPool.getActiveObjects().get(j).pos)) {
                    enemyShipsPool.getActiveObjects().get(i).destroy();
                    bulletPool.getActiveObjects().get(j).destroy();
                }
            }
        }
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star s: stars) {
            s.resize(worldBounds);
        }
        ship.resize(worldBounds);
        buttonRight.resize(worldBounds);
        buttonLeft.resize(worldBounds);
    }

    public void freeAllDestroyedSprites() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyShipsPool.freeAllDestroyedActiveSprites();
    }

//    public void enemyAttack() {
//        EnemyShip enemyShip = enemyShipsPool.obtain();
//        enemyShip.set( new Vector2(0f, 1f), new Vector2(Rnd.nextFloat(-0.5f, 0.5f), Rnd.nextFloat(-0.5f, -0.1f)));
//    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        buttonRightTexture.dispose();
        buttonLeftTexture.dispose();
        bulletPool.dispose();
        enemyShipsPool.dispose();
        music.dispose();
        bulletSound.dispose();
        enemyBulletSound.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonRight.touchDown(touch, pointer);
        buttonLeft.touchDown(touch, pointer);
        ship.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonRight.touchUp(touch, pointer);
        buttonLeft.touchUp(touch, pointer);
        ship.touchUp(touch, pointer);
        return false;
    }
}
