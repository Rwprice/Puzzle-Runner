package com.earthquakeunicorn.puzzlerunner.blocks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Spike extends Block {

	public Spike(TextureRegion text, Rectangle rect) 
	{
		super(text, rect);
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		batch.draw(text, rect.x, rect.y);
	}


	@Override
	public void update(float delta) 
	{
		
	}
}
