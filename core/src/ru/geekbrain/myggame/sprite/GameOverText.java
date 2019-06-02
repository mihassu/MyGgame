package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;

public class GameOverText extends Sprite {

    public GameOverText(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.2f);

    }

    @Override
    public void resize(Rect worldBounds) {
        pos.y = worldBounds.getTop() - 0.1f;
        pos.x = worldBounds.getHalfWidth();

    }
}
