package ru.geekbrain.myggame.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class MatrixUtil {

    public MatrixUtil() {
    }

    //mat - преобразует из прямоугольника source в прямоугольник destin

    public static void calcTransitionMatrix(Matrix4 matrix, Rect source, Rect destin) {
        float scaleX = destin.getWigth() / source.getWigth();
        float scaleY = destin.getHeight() / source.getHeight();
        matrix.idt().
                translate(destin.pos.x, destin.pos.y, 0f). //перемещаем позицию
                scale(scaleX, scaleY, 1f). //масштабирование
                translate(-source.pos.x, -source.pos.y, 0f); //перемещаем на -source.pos.x и -source.pos.y, т.е. в начало с.к.
    }

    public static void calcTransitionMatrix(Matrix3 matrix, Rect source, Rect destin) {
        float scaleX = destin.getWigth() / source.getWigth();
        float scaleY = destin.getHeight() / source.getHeight();
        matrix.idt().
                translate(destin.pos.x, destin.pos.y). //перемещаем позицию
                scale(scaleX, scaleY). //масштабирование
                translate(-source.pos.x, -source.pos.y); //перемещаем позицию
    }
}
