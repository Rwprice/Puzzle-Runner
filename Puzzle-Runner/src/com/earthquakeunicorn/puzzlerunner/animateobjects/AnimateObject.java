package com.earthquakeunicorn.puzzlerunner.animateobjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.earthquakeunicorn.puzzlerunner.Entity;

public class AnimateObject extends Entity 
{	
	public float stateTime = 0f;
	public Animation walkAnimation;
	public TextureRegion currentFrame;
	public TextureRegion jumpFrame;
	public TextureRegion pushFrame;
	public TextureRegion[] walkFrames;
	public String state = "";
	
	public AnimateObject(TextureRegion t, Rectangle r) 
	{
		super(t, r);
		TextureRegion[][] tmp = TextureRegion.split(text.getTexture(), 32, 32);
		walkFrames = new TextureRegion[4];
		
		for(int i = 0; i < 4; i++)
		{
			walkFrames[i] = tmp[0][i];
		}
		
		walkAnimation = new Animation(0.15f, walkFrames);
		jumpFrame = tmp[0][5];
		pushFrame = tmp[0][4];
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(currentFrame, rect.x, rect.y); 
	}

	@Override
	public void update(float delta) 
	{
		
		
		if(state.equals("jump"))
		{
			currentFrame = jumpFrame;
		}
		
		else if(state.equals("push"))
		{
			currentFrame = pushFrame;
		}
		
		else if(state.equals("stand"))
		{
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		}
		
		else if(state.equals("run"))
		{
			stateTime += delta;
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		}
	}

	public void update(float delta, Camera camera) 
	{
		
	}
}
