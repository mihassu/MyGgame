package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.ScaledTouchUpButton;
import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.screen.GameScreen;

public class ButtonNewGame extends ScaledTouchUpButton {

    private Rect worldBounds;
    private GameScreen gameScreen;
    private Ship ship;

    @Override
    protected void action() {
        gameScreen.setState();
        gameScreen.refreshGameScreen();
        ship.setHp(10);
    }

    public ButtonNewGame(TextureAtlas atlas, Rect worldBounds, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.worldBounds = worldBounds;
        this.gameScreen = gameScreen;
        setHeightProportion(0.1f);
        setTop(worldBounds.getTop() - 0.2f);
        pos.x = worldBounds.getHalfWidth();
        this.ship = gameScreen.getShip();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - 0.2f);
        pos.x = worldBounds.getHalfWidth();

    }

//    @Override
//    public boolean touchDown(Vector2 touch, int pointer) {
//        if (isMe(touch)) {
//            gameScreen.setState();
//            gameScreen.refreshGameScreen();
//            ship.setHp(10);
//        }
//
//        return false;
//
//    }
}
