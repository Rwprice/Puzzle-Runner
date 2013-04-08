package com.earthquakeunicorn.puzzlerunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.earthquakeunicorn.puzzlerunner.HUD;
import com.earthquakeunicorn.puzzlerunner.InputHandler;
import com.earthquakeunicorn.puzzlerunner.Level;
import com.earthquakeunicorn.puzzlerunner.LevelBuilder;
import com.earthquakeunicorn.puzzlerunner.PuzzleRunner;

public class GameScreen implements Screen
{
	public static Level level;
	public static OrthographicCamera camera;
	private Rectangle viewport;
	
	SpriteBatch batch;
	PuzzleRunner game;
	HUD hud;
	
	private static final int VIRTUAL_WIDTH = 800;
    private static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	
	public static enum State
	{
		win,
		lose,
		pause,
		playing
	}
	
	public static State state;
	
	public GameScreen(PuzzleRunner game, String levelPath)
	{
		level = LevelBuilder.buildLevel(levelPath);
		this.game = game;
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		
		hud = new HUD();
		
		state = State.playing;
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
		
		InputHandler.handleGameInput();
		
		if(InputHandler.reset)
			reset();
		
		if(InputHandler.back)
			game.returnToLevelSelect();
		
		switch(state)
		{
		
			case playing:
				
				if(InputHandler.menu)
					state = State.pause;
				
				camera.position.x++;
				
				if(level.player.rect.y + 50 <= 0)
					camera.position.y = 0;
				else
					camera.position.y = level.player.rect.y + 50;
				camera.update();
				
				level.update(delta, camera);
				
				if(!level.player.isAlive)
					state = State.lose;
				else if(level.player.hasWon)
					state = State.win;
				
				hud.update(camera);
				
				batch.setProjectionMatrix(camera.combined);
				
				batch.begin();
				level.draw(batch);
				hud.draw(batch, state);
				batch.end();
				
				break;
				
			case win:
				
				batch.setProjectionMatrix(camera.combined);
				level.winUpdate(delta, camera);
				
				batch.begin();
				level.draw(batch);
				hud.draw(batch, state);
				batch.end();
				
				break;
				
			case lose:
				
				batch.setProjectionMatrix(camera.combined);
				
				batch.begin();
				level.draw(batch);
				hud.draw(batch, state);
				batch.end();
				
				break;
				
			case pause:
				
				if(InputHandler.back)
					state = State.playing;
				
				batch.setProjectionMatrix(camera.combined);
				
				batch.begin();
				level.draw(batch);
				hud.draw(batch, state);
				batch.end();
				
				break;
		}
		
	}
	
	public void reset()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		level.reset();
		camera.position.y = level.player.rect.y + 100;
		state = State.playing;
	}

	@Override
    public void resize(int width, int height)
    {
        // calculate new viewport
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
        
        InputHandler.setScale(scale);
    }
    

	@Override
	public void show() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}

}
