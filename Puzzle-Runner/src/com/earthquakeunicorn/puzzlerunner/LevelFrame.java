package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LevelFrame extends Entity 
{
	String filePath;
	String levelName;
	BitmapFont font;
	boolean locked;
	
	public LevelFrame(TextureRegion t, Rectangle r, String path, String levelName) 
	{
		super(t, r);
		this.filePath = path;
		this.levelName = levelName;
		
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"),
		         Gdx.files.internal("font/font_0.png"), false);
		
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	@Override
	public void update(float delta) 
	{
		
	}

	@Override
	public void draw(SpriteBatch batch) 
	{
		batch.draw(text, rect.x, rect.y, rect.width, rect.height);
		font.draw(batch, levelName,rect.x + 10, rect.y + 30);
	}
	
	public boolean isLocked()
	{
		return locked;
	}
	
	public void lock()
	{
		locked = true;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public String getLevelName()
	{
		return levelName;
	}
	
	public boolean isTouched(Vector3 coords)
	{
		if(coords.x > rect.x && coords.x < rect.x + rect.width
				&& coords.y > rect.y && coords.y < rect.y + rect.height)
			return true;
		else 
			return false;
	}

}
