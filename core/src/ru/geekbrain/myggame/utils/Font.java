package ru.geekbrain.myggame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Font extends BitmapFont {

    public Font(String fontFile, String imageFile) {

        super(Gdx.files.internal(fontFile), Gdx.files.internal(imageFile), false, false);
//        getRegion(); //возвращает текстуру шрифта

        //установить фильтр сглаживания на увеличение и на уменьшение
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public void setFontSize(float fontSize) {
        //getData() - получаем доступ к символам
        //getCapHeight() - размер заглавной буквы
        getData().setScale(fontSize / getCapHeight());

    }

    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int align) {
        //вызываем метод draw у суперкласса и передаем в него параметры которые нам нужны,
        //остальные (0f и false) задаем по умолчанию
        return draw(batch, str, x, y, 0f, align, false);
    }
}
