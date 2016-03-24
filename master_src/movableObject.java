/**
 * 
 */
package Main.Ufo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.content.Context;
import java.lang.Math;

import Untils.mySprites;
import Untils.Untils;

public class movableObject {

	protected mySprites sprNormal;
	protected mySprites sprBurn;
	protected Rect curRect;
	protected Rect sourceRect;
	protected int x;
	protected int y;
	protected float angle;
	protected float speed;
	protected int ID;
	protected int state;
	
	interface objState {
		final static int NONE = 0;
		final static int FLY = 1;
		final static int BURN = 2;
		final static int DESTROY = 3;
	}

	public movableObject(mySprites spr1, mySprites spr2, int id, int x, int y, float angle, float speed) {
		this.sprNormal = spr1;
		this.sprBurn = spr2;
		this.ID = id;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		reset();
	}
	
	public movableObject(mySprites spr1, mySprites spr2, int x, int y) {
		this.sprNormal = spr1;
		this.sprBurn = spr2;
		this.ID = -1;
		this.x = x;
		this.y = y;
		this.angle = 0;
		this.speed = 0;
		reset();
	}
	
	public movableObject(movableObject other) {
		this.sprNormal = new mySprites(other.sprNormal);
		this.sprBurn = new mySprites(other.sprBurn);
		this.ID = other.ID;
		this.x = other.x;
		this.y = other.y;
		this.angle = other.angle;
		this.speed = other.speed;
		reset();
	}
	
	public Rect getRect() {
		return new Rect(curRect);
	}
	public void setRect(Rect Rect) {
		this.curRect = Rect;
	}
	public Rect getSourceRect() {
		return new Rect(sourceRect);
	}
	public void setSourceRect(Rect Rect) {
		this.sourceRect = Rect;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		this.ID = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void updateRect() {
		this.curRect.left = sourceRect.left + (int)this.x;
		this.curRect.right = sourceRect.right + (int)this.x;
		this.curRect.top = sourceRect.top + (int)this.y;		
		this.curRect.bottom = sourceRect.bottom + (int)this.y;
	}
	public void updatePos() {
		int vx = (int)(this.speed*this.angle);
		int vy = (int)(this.speed/this.angle);
		this.x = this.x + vx;
		this.y = this.y - vy;
	}
	public boolean isInRect(Rect r) {
		return r.contains(curRect);
	}
	public boolean isFly() {
		return (getState() == objState.FLY);
	}
	public boolean isBurn() {
		return (getState() == objState.BURN);
	}
	public boolean isDestroy() {
		return (getState() == objState.DESTROY);
	}
	public void start()
	{
		setState(objState.FLY);
	}
	public void reset()
	{
		sourceRect =  new Rect(sprNormal.getRect());
		curRect = new Rect();
		this.curRect.left = sourceRect.left + (int)this.x;
		this.curRect.right = sourceRect.right + (int)this.x;
		this.curRect.top = sourceRect.top + (int)this.y;		
		this.curRect.bottom = sourceRect.bottom + (int)this.y;
		setState(objState.NONE);
		this.sprBurn.setLoop(1);
		this.sprNormal.setLoop(-1);
	}
	public void update(boolean touch, int x, int y) {
		if(getState() == objState.FLY)
		{
			sprNormal.update();
			if(touch)
			{
				if(Untils.isTouchInRect(x, y, curRect))
				{
					setState(objState.BURN);
				}
			}
			updatePos();
			updateRect();
		}
		else if(getState() == objState.BURN)
		{
			sprBurn.update();
			if(sprBurn.getFinish())
			{
				setState(objState.DESTROY);
			}
		}
	}
	
	public void draw(Canvas canvas) {
		if(getState() == objState.FLY)
			sprNormal.draw(canvas, this.x, this.y);
		else if(getState() == objState.BURN)
			sprBurn.draw(canvas, this.x, this.y);
	}
}
