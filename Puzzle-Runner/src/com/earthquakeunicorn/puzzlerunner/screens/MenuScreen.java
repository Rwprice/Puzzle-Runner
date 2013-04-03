package com.earthquakeunicorn.puzzlerunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.earthquakeunicorn.puzzlerunner.InputHandler;
import com.earthquakeunicorn.puzzlerunner.PuzzleRunner;

public class MenuScreen implements Screen 
{
	OrthographicCamera camera;
	SpriteBatch batch;
	PuzzleRunner game;
	
	Texture bg;
	
	public MenuScreen(PuzzleRunner game)
	{
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		bg = new Texture(Gdx.files.internal("textures/startMenu.png"));
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		InputHandler.handleMenuInput();
		
		if(InputHandler.back)
			Gdx.app.exit();
		
		if(InputHandler.coords.x > 290 && InputHandler.coords.x < 480
				&& InputHandler.coords.y > 340)
			game.levelSelect();
		
		batch.begin();
		batch.draw(bg, 0, 0, 800, 480);
		batch.end();
	}

	@Override
	public void resize(int width, int height) 
	{
		
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
