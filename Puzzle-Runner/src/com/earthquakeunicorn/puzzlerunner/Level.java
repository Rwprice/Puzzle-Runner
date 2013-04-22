package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.earthquakeunicorn.puzzlerunner.animateobjects.Enemy;
import com.earthquakeunicorn.puzzlerunner.animateobjects.Player;
import com.earthquakeunicorn.puzzlerunner.blocks.Block;

public class Level extends Actor
{
	public Array<Block> blocks;
	public Array<Enemy> enemies;
	
	public Player player;
	
	public Level()
	{
		blocks = new Array<Block>();
		enemies = new Array<Enemy>();
	}
	
	public void addBlock(Block block)
	{
		blocks.add(block);
	}
	
	public void addPlayer(Player p)
	{
		player = p;
	}
	
	public void addEnemy(Enemy e)
	{
		enemies.add(e);
	}
	
	public void update(float delta, Camera camera)
	{
		for(Block cur : blocks)
			cur.update(delta);
		
		for(Enemy cur : enemies)
			cur.update(delta);
		
		player.update(delta, camera);
	}
	
	public void draw(SpriteBatch batch)
	{
		for(Block cur : blocks)
			cur.draw(batch);
		
		for(Enemy cur : enemies)
			cur.draw(batch);
		
		player.draw(batch);
	}
	
	public void winUpdate(float delta, Camera camera)
	{
		player.hasWon = true;
		player.update(delta, camera);
	}
	
	public void reset()
	{
		player.reset();
		for(Block cur : blocks)
			cur.reset();
		for(Enemy cur : enemies)
			cur.reset();
	}
}
