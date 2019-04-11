package ru.geekbrain.myggame;

import com.badlogic.gdx.Game;

import ru.geekbrain.myggame.screen.MenuScreen;

public class MyGgame extends Game {


	//занимается инициализацией проекта, срабатывает при запуске проекта.
	@Override
	public void create () {
		setScreen(new MenuScreen());

	}


}
