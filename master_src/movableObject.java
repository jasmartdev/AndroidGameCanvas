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
import java.util.*;

import jasmartdev.untils.mySprites;
import jasmartdev.untils.Untils;

abstract public class movableObject {

	protected mySprites sprNormal;
	protected mySprites sprBurn;
	protected Rect curRect;
	protected Rect sourceRect;
	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	protected int ID;
	protected int state;
	
	interface objState {
		final static int NONE = 0;
		final static int FLY = 1;
		final static int BURN = 2;
		final static int DESTROY = 3;
	}

	public movableObject(final mySprites spr1, final mySprites spr2, int id, int x, int y, int vx, int vy) {
		this.sprNormal = spr1;
		this.sprBurn = spr2;
		this.ID = id;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		reset();
	}
	
	public movableObject(final mySprites spr1, final mySprites spr2, int x, int y) {
		this.sprNormal = spr1;
		this.sprBurn = spr2;
		this.ID = -1;
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
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
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
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
	public int getType()
	{
		return -1;
	}
	public void updateRect() {
		curRect = new Rect(sourceRect);
		curRect.offset(this.x, this.y);
	}
	abstract public void updatePos(int delta);
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
		sprNormal.reset();
		sourceRect =  sprNormal.getRect();
		Untils.Dbg("reset sourceRect.left"+sourceRect.left+" sprNormal:"+sprNormal);
		curRect = new Rect(sourceRect);
		curRect.offset(this.x, this.y);
		setState(objState.NONE);
		sprBurn.setLoop(1);
		sprNormal.setLoop(-1);
	}
	public void burn()
	{
		setState(objState.BURN);
	}
	public void destroy()
	{
		setState(objState.DESTROY);
	}
	public void update(int delta) {
		if(getState() == objState.FLY)
		{
			sprNormal.update();
			updatePos(delta);
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
