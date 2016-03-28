/**
 * 
 */
package Main.Ufo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.content.Context;
import java.lang.Math;

import Untils.mySprites;
import Untils.Untils;

public class balloonObject extends movableObject{

	private char _char;

	public balloonObject(mySprites spr1, mySprites spr2, char c, int x, int y, int vx, int vy) {
		super(spr1, spr2, -1, x, y, vx, vy);
		_char = c;
	}
	
	public balloonObject(mySprites spr1, mySprites spr2, int id, int x, int y, int vx, int vy) {
		super(spr1, spr2, id, x, y, vx, vy);
		_char = ' ';
	}
	
	public balloonObject(mySprites spr1, mySprites spr2, char c, int x, int y) {
		super(spr1, spr2, x, y);
		_char = c;
	}

	public balloonObject(mySprites spr1, mySprites spr2, int x, int y) {
		super(spr1, spr2, x, y);
		_char = ' ';
	}
	
	public balloonObject(balloonObject other) {
		super((movableObject)other);
		this._char = other._char;
	}
	
	public char getChar()
	{
		return _char;
	}
	
	public void setChar(char c)
	{
		_char = c;
	}
	
	@Override
	public int getType()
	{
		return 0;
	}
	@Override
	public void updatePos() {
		this.x = this.x + vx;
		this.y = this.y + vy;		
		Untils.Dbg("x:"+x+" y:"+y+" vx:"+vx+" vy:"+vy);
	}
	@Override	
	public void draw(Canvas canvas) {
		if(getState() == objState.FLY)
			sprNormal.draw(canvas, this.x, this.y);
		else if(getState() == objState.BURN)
			sprBurn.draw(canvas, this.x, this.y);
		Untils.drawString(canvas, Character.toString(_char) , getRect().centerX(), getRect().centerY(), Color.WHITE, 28, 0);
	}
}
