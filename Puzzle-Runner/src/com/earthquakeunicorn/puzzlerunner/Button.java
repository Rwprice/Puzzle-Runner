package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.earthquakeunicorn.puzzlerunner.InputHandler;

public class Button {
		
	private TextureRegion[] images;
	private int width, height, x, y;
	private int curState;
	private boolean released;
	
	public Button(Texture img, int x, int y, int w, int h) {
		images = new TextureRegion[2];
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		for( int i = 0; i < 2; i ++) {
			images[i] = new TextureRegion(img, i*w, 0, w, h);
		}
		curState = 0;
		released = false;
	}
	
	public void update() {
		if( (InputHandler.coords.x > this.x && InputHandler.coords.x < this.x + this.width) 
				&& (InputHandler.coords.y > this.y && InputHandler.coords.y < this.y + this.height) ) {
			curState = 1;
		}
		else {
			if(curState == 1)
				released = true;
			curState = 0;
		}		
	}
	public void draw(SpriteBatch batch) {
		batch.draw(images[curState], x, y);
	}
	
	public boolean wasClicked() { 
		if(released) { 
			released = false;
			return true;
		}
		return false;
	}

}
