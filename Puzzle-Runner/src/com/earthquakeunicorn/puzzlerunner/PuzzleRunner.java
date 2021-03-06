package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.earthquakeunicorn.puzzlerunner.screens.GameScreen;
import com.earthquakeunicorn.puzzlerunner.screens.LevelSelectScreen;
import com.earthquakeunicorn.puzzlerunner.screens.MenuScreen;

public class PuzzleRunner extends Game 
{
	GameScreen gameScreen;
	MenuScreen menuScreen;
	LevelSelectScreen levelSelectScreen;
	
	@Override
	public void create() 
	{
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}
	
	public void levelSelect()
	{
		levelSelectScreen = new LevelSelectScreen(this);
		setScreen(levelSelectScreen);
	}
	
	public void selectLevel(String levelPath)
	{
		gameScreen = new GameScreen(this, levelPath);
		setScreen(gameScreen);
	}
	
	public void returnToLevelSelect()
	{
		gameScreen = null;
		levelSelectScreen = new LevelSelectScreen(this);
		setScreen(levelSelectScreen);
	}
	
	public void returnToMainMenu()
	{
		levelSelectScreen = null;
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}
}
