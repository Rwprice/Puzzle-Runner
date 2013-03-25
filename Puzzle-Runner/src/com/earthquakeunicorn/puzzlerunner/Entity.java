package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity 
{
	public Texture text;
	public Rectangle rect, top, bottom, left, right;
	public int size = 3;
	public boolean active = true;
	
	public Entity(Texture t, Rectangle r)
	{
		text = t;
		rect = r;
		
		top = new Rectangle(rect.x + size, rect.y + rect.height - size * 2, rect.width - size * 2, size);
		bottom = new Rectangle(rect.x + size, rect.y + size, rect.width - size * 2, size);
		left = new Rectangle(rect.x + size, rect.y + size, size, rect.height - size * 2);
		right = new Rectangle(rect.x + rect.width - size * 2, rect.y + size, size, rect.height - size * 2);
	}
	
	public boolean isOnBottomOf(Entity e)
	{
		if(e.active)
			return top.overlaps(e.bottom);
		else
			return false;
	}
	
	public boolean isOnTopOf(Entity e)
	{
		if(e.active)
			return bottom.overlaps(e.top);
		else
			return false;
	}
	
	public boolean isOnLeftOf(Entity e)
	{
		if(e.active)
			return right.overlaps(e.left);
		else
			return false;
	}
	
	public boolean isOnRightOf(Entity e)
	{
		if(e.active)
			return left.overlaps(e.right);
		else
			return false;
	}
	
	public void move(Vector2 vec)
	{
		rect.x = vec.x;
		rect.y = vec.y;
		
		top.x = vec.x;
		top.y = vec.y + rect.height - size;
		
		left.x = vec.x;
		left.y = vec.y;
		
		right.x = vec.x + rect.width - size;
		right.y = vec.y;
		
		bottom.x = vec.x;
		bottom.y = vec.y;
	}
	
	public abstract void update(float delta);
	
	public abstract void draw(SpriteBatch batch);
}
