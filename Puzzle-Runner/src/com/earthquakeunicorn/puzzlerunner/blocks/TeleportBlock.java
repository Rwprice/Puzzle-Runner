package com.earthquakeunicorn.puzzlerunner.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TeleportBlock extends Block {

	public TeleportBlock(Texture text, Rectangle rect) 
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
