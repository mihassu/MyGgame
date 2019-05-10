package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrain.myggame.base.ScaledTouchUpButton;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.screen.GameScreen;

public class ButtonPlay extends ScaledTouchUpButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        setHeightProportion(0.3f);
        this.game = game;
    }

    @Override
    protected void action() {
        game.setScreen(new GameScreen());
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.02f);
        setLeft(worldBounds.getLeft() + 0.02f);
    }
}
