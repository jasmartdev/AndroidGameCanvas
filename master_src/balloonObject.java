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
import java.util.Random;

import jasmartdev.untils.mySprites;
import jasmartdev.untils.Untils;

public class balloonObject extends movableObject{

	private char _char;

	public balloonObject(final mySprites spr1, final mySprites spr2, char c, int x, int y, int vx, int vy) {
		super(spr1, spr2, -1, x, y, vx, vy);
		_char = c;
	}
	
	public balloonObject(final mySprites spr1, final mySprites spr2, int id, int x, int y, int vx, int vy) {
		super(spr1, spr2, id, x, y, vx, vy);
		_char = ' ';
	}
	
	public balloonObject(final mySprites spr1, final mySprites spr2, char c, int x, int y) {
		super(spr1, spr2, x, y);
		_char = c;
	}

	public balloonObject(final mySprites spr1, final mySprites spr2, int x, int y) {
		super(spr1, spr2, x, y);
		_char = ' ';
	}

	public char getChar()
	{
		return _char;
	}
	
	public void setChar(char c)
	{
		_char = c;
	}
	public boolean equals(balloonObject other)
	{
		if(getChar() == other.getChar())
			return true;
		return false;
	}
	@Override
	public Rect getRect() {
		Rect r = new Rect(curRect);
		r.bottom += Define.BALL_RECY_OFF_Y;
		return r;
	}
	@Override
	public int getType()
	{
		return 0;
	}
	@Override
	public void updatePos(int delta) {
		this.x = this.x + (vx*delta)/Config.s_framePeriod;
		this.y = this.y + (vy*delta)/Config.s_framePeriod;
	}
	@Override	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.BLACK);
		myPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(getRect(), myPaint);
		myPaint.setStrokeWidth(8);		
		myPaint.setColor(Color.WHITE);
		canvas.drawRect(getSourceRect(), myPaint);
		if(getState() != objState.DESTROY)
		{
			Untils.drawString(canvas, Character.toString(_char) , getRect().centerX() - 2, getRect().centerY() - 2, Color.WHITE, Define.TEXT_SIZE_LARGE + 4, 0);
			Untils.drawString(canvas, Character.toString(_char) , getRect().centerX(), getRect().centerY(), Color.BLACK, Define.TEXT_SIZE_LARGE, 0);
		}
	}
}
