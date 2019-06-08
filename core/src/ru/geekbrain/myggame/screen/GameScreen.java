package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.geekbrain.myggame.base.BaseScreen;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.pool.EnemyShipsPool;
import ru.geekbrain.myggame.pool.ExplosionsPool;
import ru.geekbrain.myggame.sprite.Background;
import ru.geekbrain.myggame.sprite.Bullet;
import ru.geekbrain.myggame.sprite.ButtonLeft;
import ru.geekbrain.myggame.sprite.ButtonNewGame;
import ru.geekbrain.myggame.sprite.ButtonRight;
import ru.geekbrain.myggame.sprite.EnemyShip;
import ru.geekbrain.myggame.sprite.GameOverText;
import ru.geekbrain.myggame.sprite.Ship;
import ru.geekbrain.myggame.sprite.Star;
import ru.geekbrain.myggame.utils.EnemyShipsGenerator;
import ru.geekbrain.myggame.utils.Font;

public class GameScreen extends BaseScreen {

    public static final String FRAGS = "Frags: ";
    public static final String LEVEL = "Level: ";


    private enum State {PLAYING, PAUSE, GAME_OVER}

    private State state;

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
    private int frags = 0;
    private StringBuilder stringBuilderFrags;
    private StringBuilder stringBuilderHp;
    private StringBuilder stringBuilderLevel;

    private GameOverText gameOverText;
    private ButtonNewGame buttonNewGame;

    private EnemyShipsPool enemyShipsPool;
    private ExplosionsPool explosionsPool;

    private Music music;
    private Sound bulletSound;
    private Sound enemyBulletSound;
    private Sound explosionSound;

    private EnemyShipsGenerator enemyShipsGenerator;

    private Font font;

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
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        explosionsPool = new ExplosionsPool(atlas, explosionSound);
        ship = new Ship(atlas, bulletPool, bulletSound, explosionsPool);
        buttonRightTexture = new Texture("textures/arrow_right.png");
        buttonRight = new ButtonRight(new TextureRegion(buttonRightTexture), ship);

        buttonLeftTexture = new Texture("textures/arrow_left.png");
        buttonLeft = new ButtonLeft(new TextureRegion(buttonLeftTexture), ship);

        enemyShipsPool = new EnemyShipsPool(bulletPool, enemyBulletSound, worldBounds, ship,
                explosionsPool);

        enemyShipsGenerator = new EnemyShipsGenerator(atlas, enemyShipsPool, worldBounds);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();

        gameOverText = new GameOverText(atlas);
        buttonNewGame = new ButtonNewGame(atlas, worldBounds, this);

        font = new Font("font/font.fnt", "font/font.png");
        font.setFontSize(0.05f);
        stringBuilderFrags = new StringBuilder();
        stringBuilderHp = new StringBuilder();
        stringBuilderLevel = new StringBuilder();

        state = State.PLAYING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyedSprites();
        draw();
    }

    public Ship getShip() {
        return ship;
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star s : stars) {
            s.draw(batch);
        }

        if (state == State.PLAYING) {
            ship.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyShipsPool.drawActiveSprites(batch);
            buttonRight.draw(batch);
            buttonLeft.draw(batch);
        }

        if (state == State.GAME_OVER) {
            gameOverText.draw(batch);
            buttonNewGame.draw(batch);
        }

        explosionsPool.drawActiveSprites(batch);
        printInfo();
        batch.end();
    }

    //Вывод текста
    private void printInfo() {
//        font.draw(batch, "Frags: " + frags, worldBounds.getLeft(), worldBounds.getTop()); //так нельзя!!!
        stringBuilderFrags.setLength(0); //обнулить stringBuilderFrags
        font.draw(batch, stringBuilderFrags.append(FRAGS).append(frags), worldBounds.getLeft(), worldBounds.getTop());

        stringBuilderHp.setLength(0);
        font.draw(batch, stringBuilderHp.append(ship.getHp()), ship.getPos().x + 0.1f, ship.getPos().y);

        stringBuilderLevel.setLength(0);
        font.draw(batch, stringBuilderLevel.append(LEVEL).append(enemyShipsGenerator.getStage()), worldBounds.getRight(), worldBounds.getTop(), Align.right);
    }


    private void update(float delta) {
        for (Star s : stars) {
            s.update(delta);
        }

        explosionsPool.updateActiveSprites(delta);

        if (state == State.PLAYING) {
            ship.update(delta);
            enemyShipsGenerator.generate(delta, frags);
            bulletPool.updateActiveSprites(delta);
            enemyShipsPool.updateActiveSprites(delta);

            //попадание пулей во врагов
            checkCollisions();
        }
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star s : stars) {
            s.resize(worldBounds);
        }

        if (state == State.PLAYING) {
            ship.resize(worldBounds);
            buttonRight.resize(worldBounds);
            buttonLeft.resize(worldBounds);
        }
    }

    @Override
    public void pause() {
        super.pause();
        if (state == State.PLAYING) {
            state = State.PAUSE;
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (state == State.PAUSE) {
            state = State.PLAYING;
        }
    }

    public void freeAllDestroyedSprites() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyShipsPool.freeAllDestroyedActiveSprites();
        explosionsPool.freeAllDestroyedActiveSprites();
    }

    //попадание пулей во врагов
    public void checkCollisions() {
        List<EnemyShip> enemyShips = enemyShipsPool.getActiveObjects();
        List<Bullet> bullets = bulletPool.getActiveObjects();

        if (ship.getHp() <= 0) {
            ship.destroy();
            state = State.GAME_OVER;
        }


        for (Bullet bullet : bullets) {

            //если пуля врага попала по кораблю
            if (ship.isBulletCollision(bullet) && !ship.getClass().getName().equals(bullet.getOwner().getClass().getName())) {
                bullet.destroy();
                ship.damage(bullet.getDamage());
            }

            for (EnemyShip enemyShip : enemyShips) {

                //проверка что корабль столкнулся с врагом
                if (enemyShip.isMe(ship.pos)) {
                    enemyShip.destroy();
                    frags++;
                    ship.damage(enemyShip.getDamage());
                    explosionSound.play();
                }


                //if(enemyShip.isMe(bullet.pos)
                if (enemyShip.isBulletCollision(bullet) && //проверка что пуля попала во врага, причем долетела да его середины

                        //проверка что owner у пуль разные (чтобы враги не уничтожали друг друга)
                        !enemyShip.getClass().getName().equals(bullet.getOwner().getClass().getName())) {


                    //если у врага не осталось hp то он уничтожается
                    if (enemyShip.getHp() <= 0) {
                        enemyShip.destroy();
                        frags++;
                        bullet.destroy();

                    } else { //иначе пуля уничтожается а у врага отнимается hp
                        bullet.destroy();
                        enemyShip.damage(bullet.getDamage());
                    }
                }
            }
        }

    }

    //Новая игра
    public void refreshGameScreen() {
        state = State.PLAYING;
        bulletPool.freeAllActiveSprites();
        enemyShipsPool.freeAllActiveSprites();
        ship.refreshShip();
        frags = 0;
        enemyShipsGenerator.setStage(1);
        enemyShipsGenerator.setGenerateInterval(2f);
    }


    //Не забывать!!!!!!!!!!!!!!!!!!
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
        explosionSound.dispose();
        explosionsPool.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            ship.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            ship.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            buttonRight.touchDown(touch, pointer);
            buttonLeft.touchDown(touch, pointer);
            ship.touchDown(touch, pointer);
        }

        if (state == State.GAME_OVER) {
            buttonNewGame.touchDown(touch, pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            buttonRight.touchUp(touch, pointer);
            buttonLeft.touchUp(touch, pointer);
            ship.touchUp(touch, pointer);
        }

        if (state == State.GAME_OVER) {
            buttonNewGame.touchUp(touch, pointer);
        }
        return false;
    }
}
