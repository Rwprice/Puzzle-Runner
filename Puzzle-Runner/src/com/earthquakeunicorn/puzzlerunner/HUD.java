package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.earthquakeunicorn.puzzlerunner.screens.GameScreen;

public class HUD 
{
	private Texture arrowTex, jumpTex, refreshTex, pauseTex;
	private Sprite arrowSprite, jumpSprite, refreshSprite, pauseSprite;
	
	private int relativeX;
	private int relativeY;
	
	public HUD()
	{
		arrowTex = new Texture(Gdx.files.internal("textures/arrows.png"));
		jumpTex = new Texture(Gdx.files.internal("textures/jump.png"));
		refreshTex = new Texture(Gdx.files.internal("textures/refresh.png"));
		pauseTex = new Texture(Gdx.files.internal("textures/pauseMenu.png"));
		
		arrowSprite = new Sprite(arrowTex);
		jumpSprite = new Sprite(jumpTex);
		refreshSprite = new Sprite(refreshTex);
		pauseSprite = new Sprite(pauseTex);
	}
	
	public void update(OrthographicCamera camera)
	{
		relativeX = (int) (camera.position.x - camera.viewportWidth/2);
		relativeY = (int) (camera.position.y - camera.viewportHeight/2);
		
		arrowSprite.setBounds(relativeX, relativeY, 300, 80);
		jumpSprite.setBounds(relativeX + 650, relativeY, 80, 80);
		refreshSprite.setBounds(relativeX, relativeY + 400, 80, 80);
		refreshSprite.setBounds(relativeX, relativeY + 400, 80, 80);
		pauseSprite.setBounds(relativeX + 200, relativeY + 120, 400, 240);
		
	}
	
	public void draw(SpriteBatch batch, GameScreen.State state)
	{
		switch(state)
		{
			case playing:
				arrowSprite.draw(batch);
				jumpSprite.draw(batch);
				break;
			
			case pause:
				pauseSprite.draw(batch);
				break;
		}
		
		refreshSprite.draw(batch);
	}
}
