package com.earthquakeunicorn.puzzlerunner.animateobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends AnimateObject 
{
	public boolean isAlive = true;
	public boolean isOnScreen = false;
	private Vector2 initialPosition;
	private int speed = 4;
	
	public Enemy(Texture t, Rectangle r, int col, int rows) 
	{
		super(t, r);
		initialPosition = new Vector2(r.x, r.y);
	}

	@Override
	public void update(float delta, Camera camera) 
	{
		super.update(delta);
		
		if(isOnScreen)
		{
			//All update stuff
		}
		
		else
		{
			if(rect.x < camera.position.x + camera.viewportWidth / 2)
			{
				isOnScreen = true;
			}
			
			else if(rect.y < camera.position.y + camera.viewportHeight / 2 
					&& rect.y > camera.position.y - camera.viewportHeight / 2)
			{
				isOnScreen = true;
			}
				
		}
	}

	@Override
	public void draw(SpriteBatch batch) 
	{
		super.draw(batch);
	}
}
