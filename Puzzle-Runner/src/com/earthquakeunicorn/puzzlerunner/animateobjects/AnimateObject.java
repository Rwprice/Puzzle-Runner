package com.earthquakeunicorn.puzzlerunner.animateobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.earthquakeunicorn.puzzlerunner.Entity;

public class AnimateObject extends Entity 
{	
	public float stateTime = 0f;
	public Animation curAnimation;
	public TextureRegion currentFrame;
	
	public AnimateObject(Texture t, Rectangle r) 
	{
		super(t, r);
	}
	
	public void setCurAnimation(Animation a)
	{
		curAnimation = a;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(currentFrame, rect.x, rect.y); 
	}

	@Override
	public void update(float delta) 
	{
		//stateTime += delta;
        //currentFrame = curAnimation.getKeyFrame(stateTime, true);
	}

	public void update(float delta, Camera camera) 
	{
		// TODO Auto-generated method stub
		
	}
}
