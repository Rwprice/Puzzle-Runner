package com.earthquakeunicorn.puzzlerunner.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class TeleportBlock extends Block 
{
	private ParticleEffect effect;
	private Array<ParticleEmitter> effectEmitters;
	
	public TeleportBlock(TextureRegion text, Rectangle rect) 
	{
		super(text, rect);
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particles/teleport.p"),Gdx.files.internal("particles"));
		effectEmitters = new Array<ParticleEmitter>(effect.getEmitters());
		
		effect.setPosition(rect.x + rect.width/2, rect.y + rect.height);
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		batch.draw(text, rect.x, rect.y);
		effect.draw(batch, Gdx.graphics.getDeltaTime());
	}


	@Override
	public void update(float delta) 
	{
		
	}
	
	public void activated()
	{
		effectEmitters.get(0).start();
	}

}
