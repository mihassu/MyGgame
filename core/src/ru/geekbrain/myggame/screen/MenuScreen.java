package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;



import ru.geekbrain.myggame.base.BaseScreen;


public class MenuScreen extends BaseScreen{
    private Texture img;
    private Texture background;
    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 buffer;
    private float a;
    private final Vector2 up = new Vector2(0,1);
    private final Vector2 down = new Vector2(0,-1);
    private final Vector2 left = new Vector2(-1,0);
    private final Vector2 right = new Vector2(1,0);




    @Override
    public void show() {
        super.show();
        img = new Texture("kot3.jpg");
        background = new Texture("Bricks23_col.jpg");
        touch = new Vector2();
        pos = new Vector2(20, 20);
        v = new Vector2();
        buffer = new Vector2();

    }


    @Override
    public void render(float delta) {
        super.render(delta);
//        v.scl(a); // с ускорением почему то картинка не всегда останавоивается в точке
        buffer.set(touch);

        if(buffer.sub(pos).len() > 0.5f) {
            pos.add(v);

        } else {
            pos.set(touch);
        }

//        pos.add(v);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.setColor(0.454f, 0.32f, 0.68f, 0.8f); //установить фон и прозразность
        batch.draw(img, pos.x, pos.y, 200, 200); //рисование текстуры в точке с координатами x, y (левый нижний угол текстуры).
        batch.end();

        if (pos.y > Gdx.graphics.getHeight() - 200) {
            System.out.println("Картинка на краю");
            v.setZero();
            //Установить картинку на край экрана, т.к. из-за ускорения она пролетает за край
            pos.set(pos.x, Gdx.graphics.getHeight() - 200);
        }
        if (pos.y < 0) {
            System.out.println("Картинка на краю");
            v.setZero();
            //Установить картинку на край экрана, т.к. из-за ускорения она пролетает за край
            pos.set(pos.x, 0);
        }
        if (pos.x > Gdx.graphics.getWidth() - 200) {
            System.out.println("Картинка на краю");
            v.setZero();
            //Установить картинку на край экрана, т.к. из-за ускорения она пролетает за край
            pos.set(Gdx.graphics.getWidth() - 200, pos.y);
        }
        if (pos.x < 0) {
            System.out.println("Картинка на краю");
            v.setZero();
            //Установить картинку на край экрана, т.к. из-за ускорения она пролетает за край
            pos.set(0, pos.y);
        }

//        if((Math.abs(touch.x - pos.x) < 1) && (Math.abs(touch.y - pos.y) < 1)){
//            System.out.println("Картинка в точке");
//            v.setZero();
//            touch.set(-100,-100); //чтобы остановить срабатывание условия
//        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getBackBufferHeight() - screenY);
        v.set(touch.cpy().sub(pos)); //определяем вектор направления на точку
        v.setLength(0.5f);
//        v.nor(); // нормирование
        a = 1.05f;
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        a = 1.05f;

        if (keycode == 19){
            v.set(up);
        }

        if (keycode == 20){
            v.set(down);
        }

        if (keycode == 21){
            v.set(left);
        }

        if(keycode == 22) {
            v.set(right);
        }

        return false;
//        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        v.setZero();
        a = 1.0f;

        return false;
//        return super.keyUp(keycode);
    }
}
