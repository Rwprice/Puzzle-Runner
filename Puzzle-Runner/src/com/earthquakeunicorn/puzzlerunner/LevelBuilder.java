package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.earthquakeunicorn.puzzlerunner.animateobjects.Enemy;
import com.earthquakeunicorn.puzzlerunner.animateobjects.Player;
import com.earthquakeunicorn.puzzlerunner.blocks.Block;
import com.earthquakeunicorn.puzzlerunner.blocks.BreakableBlock;
import com.earthquakeunicorn.puzzlerunner.blocks.Spike;
import com.earthquakeunicorn.puzzlerunner.blocks.TeleportBlock;
import com.earthquakeunicorn.puzzlerunner.blocks.WinBlock;

public class LevelBuilder 
{	
	public static Level buildLevel(String filePath)
	{
		FileHandle file = Gdx.files.internal(filePath);
		Level level = new Level();
		
		String entireLevel = file.readString();
		
		String[] tokens = entireLevel.split("\n");
		
		for(int i = tokens.length-1; i >= 0;i--)
		{
			String curLine = tokens[i];
			
			for(int j = 0; j < curLine.length(); j++)
			{
				if(curLine.charAt(j) == '#')
					level.addBlock(new Block(new TextureRegion(new Texture(Gdx.files.internal("textures/block.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32)));
				else if(curLine.charAt(j) == '@')
					level.addPlayer(new Player(new TextureRegion(new Texture(Gdx.files.internal("textures/player.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32), 0, 0));
				else if(curLine.charAt(j) == '&')
					level.addBlock(new WinBlock(new TextureRegion(new Texture(Gdx.files.internal("textures/winblock.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32)));
				else if(curLine.charAt(j) == '^')
					level.addBlock(new Spike(new TextureRegion(new Texture(Gdx.files.internal("textures/spike.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32)));
				else if(curLine.charAt(j) == '*')
					level.addBlock(new TeleportBlock(new TextureRegion(new Texture(Gdx.files.internal("textures/teleport.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32)));
				else if(curLine.charAt(j) == '|')
					level.addBlock(new BreakableBlock(new TextureRegion(new Texture(Gdx.files.internal("textures/breakableblock.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32)));
				else if(curLine.charAt(j) == '?')
					level.addEnemy(new Enemy(new TextureRegion(new Texture(Gdx.files.internal("textures/enemy.png"))), new Rectangle(j*32, ((tokens.length-1)-i)*32, 32, 32), 0, 0));
			}
		}
		
		return level;
	}
}
