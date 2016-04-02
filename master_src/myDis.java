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

import jasmartdev.untils.mySprites;
import jasmartdev.untils.Untils;

public class myDis {

	private mySprites sprFly;
	private mySprites sprBurn;
	private Rect curRect;
	private Rect sourceRect;
	private int x;
	private int y;
	private double angle;
	private float speed;
	private int disID;
	private int state;
	
	public static int score = 0;
	public static int count = 0;
	
	public interface disState {
		final static int NONE = 0;
		final static int FLY = 1;
		final static int BURN = 2;
		final static int OUT = 3;
	}

	public myDis(mySprites spr1, mySprites spr2, int id, int x, int y, double angle, float speed) {
		this.sprFly = spr1;
		this.sprBurn = spr2;
		this.disID = id;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		curRect = new Rect(sprFly.getRect());
		sourceRect =  new Rect(sprFly.getRect());
		state = disState.NONE;
		this.sprBurn.setLoop(1);
	}
	
	public myDis(myDis otherDis) {
		this.sprFly = new mySprites(otherDis.sprFly);
		this.sprBurn = new mySprites(otherDis.sprBurn);
		this.disID = otherDis.disID;
		this.x = otherDis.x;
		this.y = otherDis.y;
		this.angle = otherDis.angle;
		this.speed = otherDis.speed;
		this.curRect = new Rect(otherDis.getRect());
		this.sourceRect =  new Rect(otherDis.getSourceRect());
		this.state = otherDis.getState();
		this.sprBurn.setLoop(1);
	}
	public void resetScore()
	{
		score = 0;
	}
	public static int getScore()
	{
		return score;
	}
	public void resetCount()
	{
		count = 0;
	}
	public static int getCount()
	{
		return count;
	}
	public Rect getRect() {
		return curRect;
	}
	public void setRect(Rect Rect) {
		this.curRect = Rect;
	}
	public Rect getSourceRect() {
		return sourceRect;
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
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getID() {
		return disID;
	}
	public void setID(int id) {
		this.disID = id;
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
	public boolean checkOut(int x, int y) {
		return (this.x > x || this. y < y);
	}
	public boolean isFly() {
		return (getState() == disState.FLY);
	}
	public boolean isBurn() {
		return (getState() == disState.BURN);
	}
	public boolean isOut() {
		return (getState() == disState.OUT);
	}
	public void start()
	{
		setState(disState.FLY);
		count++;
	}
	public void reset()
	{
		curRect = new Rect(sprFly.getRect());
		sourceRect =  new Rect(sprFly.getRect());
		setState(disState.NONE);
		this.sprBurn.setLoop(1);
	}
	public void update(boolean touch, int x, int y) {
		if(getState() == disState.FLY)
		{
			sprFly.update();
			if(touch)
			{
				if(Untils.isTouchInRect(x, y, curRect))
				{
					setState(disState.BURN);
				}
			}
			updatePos();
			updateRect();
		}
		else if(getState() == disState.BURN)
		{
			sprBurn.update();
			if(sprBurn.getFinish())
			{
				score++;
				setState(disState.OUT);
			}
		}
	}
	
	public void draw(Canvas canvas) {
		if(getState() == disState.FLY)
			sprFly.draw(canvas, this.x, this.y);
		else if(getState() == disState.BURN)
			sprBurn.draw(canvas, this.x, this.y);
	}
}
