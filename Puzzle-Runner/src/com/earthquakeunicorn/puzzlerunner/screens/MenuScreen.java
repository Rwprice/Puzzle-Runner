package com.earthquakeunicorn.puzzlerunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.earthquakeunicorn.puzzlerunner.InputHandler;
import com.earthquakeunicorn.puzzlerunner.PuzzleRunner;
import com.earthquakeunicorn.puzzlerunner.Button;

public class MenuScreen implements Screen 
{
	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private PuzzleRunner game;
	private Rectangle viewport;
	
	Texture bg, startTex;
	Button[] buttons;
	
	private static final int VIRTUAL_WIDTH = 800;
    private static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	
	public MenuScreen(PuzzleRunner game)
	{
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		bg = new Texture(Gdx.files.internal("textures/menuBG.png"));
		startTex = new Texture(Gdx.files.internal("textures/startButton.png"));
		buttons = new Button[1];
		buttons[0] = new Button(startTex, VIRTUAL_WIDTH/2 - 75, VIRTUAL_HEIGHT/2 + 50, 150, 50);
		batch.setProjectionMatrix(camera.combined);
		
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
        InputHandler.handleMenuInput();
        
		if(InputHandler.back)
			Gdx.app.exit();
		
		for( Button b : buttons) {
			b.update();
		}
		
		if(buttons[0].wasClicked()) {
			game.levelSelect();
		}
		
		batch.begin();
		batch.draw(bg, 0, 0, 800, 480);
		for( Button b : buttons) {
			b.draw(batch);
		}
		batch.end();
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
