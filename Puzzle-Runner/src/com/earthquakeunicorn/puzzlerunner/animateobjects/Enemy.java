package com.earthquakeunicorn.puzzlerunner.animateobjects;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.earthquakeunicorn.puzzlerunner.screens.GameScreen;

public class Enemy extends AnimateObject 
{
	public boolean isAlive = true;
	private boolean isOnScreen = false;
	private boolean onTheHunt = false;
	private boolean hasFlownPast = false;
	private Vector2 initialPosition;
	private double speed = 0.2;
	private double momentum = 0;
	private double maxSpeed = 6;
	private double stoppingSpeed = 0.3;
	private float stoppedAt;
	public int life = 5;
	
	public Enemy(Texture t, Rectangle r, int col, int rows) 
	{
		super(t, r);
		initialPosition = new Vector2(r.x, r.y);
	}

	@Override
	public void update(float delta, Camera camera) 
	{
		super.update(delta);
		if(life <= 0)
			isAlive = false;
		
		if(isOnScreen && isAlive)
		{
			Rectangle pRect = GameScreen.level.player.rect;
			
			//All update stuff
			if(rect.overlaps(pRect))
				GameScreen.level.player.isAlive = false;
			
			
			//Fly past
			if(rect.x > pRect.x - 25 && !onTheHunt && !hasFlownPast)
			{
				rect.x -= momentum;
				if(momentum < maxSpeed)
					momentum += speed;
			}
			
			//Wait to attack
			else if(!onTheHunt)
			{
				if(!hasFlownPast)
				{
					hasFlownPast = true;
					stoppedAt = TimeUtils.nanoTime();
				}
				
				//Start decel
				rect.x -= momentum;
				if(momentum > 0)
					momentum -= stoppingSpeed;
				else 
					momentum = 0;
				
				if(TimeUtils.nanoTime() - stoppedAt > 200000000f)
				{
					onTheHunt = true;
				}
			}
			
			//Mangle that bitch
			else
			{
				if(rect.x > pRect.x + pRect.width)
					rect.x -= momentum;
				
				else if(rect.x < pRect.x)
					rect.x += momentum;
				
				if(rect.y > pRect.y)
					rect.y -= 2;
				
				if(rect.y < pRect.y)
					rect.y += 2;
				
				if(momentum < maxSpeed)
					momentum += speed;
			}
		}
		
		else if(isAlive)
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
		if(isAlive)
			super.draw(batch);
	}
	
	public void reset()
	{
		isAlive = true;
		isOnScreen = false;
		onTheHunt = false;
		hasFlownPast = false;
		momentum = 0;
		life = 5;
		rect.x = initialPosition.x;
		rect.y = initialPosition.y;
	}
}
