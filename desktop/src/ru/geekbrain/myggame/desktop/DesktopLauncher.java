package ru.geekbrain.myggame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrain.myggame.MyGgame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f / 4f;
		config.width = 400;
		config.height = (int) (config.width / aspect);
		config.resizable = false;
		new LwjglApplication(new MyGgame(), config);
	}
}
