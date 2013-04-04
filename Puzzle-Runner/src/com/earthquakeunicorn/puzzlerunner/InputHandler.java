package com.earthquakeunicorn.puzzlerunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.earthquakeunicorn.puzzlerunner.screens.GameScreen;

public class InputHandler 
{	
	public static boolean right, left, jump, reset, back, menu;
	public static Vector3 coords;
	private static float lastBackRead = 0;
	private static float lastMenuRead = 0;
	private static float scale;
	
	public static void setScale(float s)
	{
		scale = s;
		System.out.println(scale);
	}
	
	public static void handleGameInput()
	{
		right = false;
		left = false;
		jump = false;
		reset = false;
		back = false;
		menu = false;
		
		coords = new Vector3();
		
		if(Gdx.input.isKeyPressed(Keys.D))
			right = true;
		
		if(Gdx.input.isKeyPressed(Keys.A))
			left = true;
		
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			jump = true;
		
		if(Gdx.input.isKeyPressed(Keys.R))
			reset = true;
		
		if(Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE) && TimeUtils.nanoTime() - lastBackRead > 200000000f)
		{
			back = true;
			lastBackRead = TimeUtils.nanoTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.TAB) || Gdx.input.isKeyPressed(Keys.MENU) && TimeUtils.nanoTime() - lastMenuRead > 200000000f)
		{
			menu = true;
			lastMenuRead = TimeUtils.nanoTime();
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(Gdx.input.isTouched(i))
			{
				
				int x = Gdx.input.getX(i);
				int y = Gdx.input.getY(i);
				
				System.out.println(x + "  " + y);
				
				if(x > 150 * scale && y > 400 * scale && x < 300 * scale)
					right = true;
				
				else if(x < 150 * scale && y > 400 * scale)
					left = true;
				
				else if(x > 650 * scale && y > 400 * scale)
					jump = true;
				
				else if(x < 80 * scale && y < 80 * scale)
					reset = true;
				
				else 
				{
					coords.x = x;
					coords.y = y;
					coords.z = 0;
					
					GameScreen.camera.unproject(coords);
				}
			}
		}
	}
	
	public static void handleMenuInput()
	{
		coords = new Vector3();
		back = false;
		left = false;
		right = false;
		
		if(Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE) && TimeUtils.nanoTime() - lastBackRead > 300000000f)
		{
			back = true;
			lastBackRead = TimeUtils.nanoTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.D))
			right = true;
		
		if(Gdx.input.isKeyPressed(Keys.A))
			left = true;
		
		if(Gdx.input.isTouched())
		{
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();

			coords.x = x;
			coords.y = y;
		}
	}
}
