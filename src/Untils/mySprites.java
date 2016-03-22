/**
 * 
 */
package Untils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.content.Context;

import Untils.Untils;
import Untils.Configs;

/**
 * @author impaler
 *
 */
public class mySprites {
	
	private static final String TAG = mySprites.class.getSimpleName();

	private Bitmap bitmap;
	private Rect sourceRect;
	private Rect curRect;
	private int frameNr;
	private int currentFrame;
	private long frameTicker;
	private int framePeriod;	
	private int spriteWidth;
	private int spriteHeight;	
	private int x;
	private int y;
	private int dataID;
	private int loop;
	private Rect scaleRect;
	private boolean useScale;
	private float scale_x;
	private float scale_y;
	
	public mySprites(int dataID, float x, float y, int frameCount, int fps) {
		this.dataID = dataID;
		this.x = (int)x;
		this.y = (int)y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 1000 / fps;
		frameTicker = 0l;
		loop = -1;
		useScale = false;
	}
	
	public mySprites(int dataID, float x, float y, int frameCount) {
		this.dataID = dataID;
		this.x = (int)x;
		this.y = (int)y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 1000 / 15;
		frameTicker = 0l;
		loop = -1;
		useScale = false;
	}
	
	public mySprites(int dataID, float x, float y) {
		this.dataID = dataID;
		this.x = (int)x;
		this.y = (int)y;
		currentFrame = 0;
		frameNr = 1;		
		framePeriod = 1000 / 10;
		frameTicker = 0l;
		loop = -1;
		useScale = false;
	}
	
	public mySprites(mySprites otherSprite)
	{
		this.bitmap = Bitmap.createBitmap(otherSprite.getBitmap());
		this.spriteWidth = otherSprite.spriteWidth;
		this.spriteHeight = otherSprite.spriteHeight;
		this.sourceRect = new Rect(otherSprite.getSourceRect());
		this.curRect = new Rect(otherSprite.getRect());
		this.dataID = otherSprite.dataID;
		this.x = otherSprite.x;
		this.y = otherSprite.y;
		currentFrame = 0;
		this.frameNr = otherSprite.frameNr;		
		this.framePeriod = otherSprite.framePeriod;
		this.frameTicker = otherSprite.frameTicker;
		this.loop = otherSprite.loop;
		this.useScale = otherSprite.useScale;
		this.scale_x = otherSprite.scale_x;
		this.scale_y = otherSprite.scale_y;
	}
	
	public void Load(Context context)
	{
		this.bitmap = BitmapFactory.decodeResource(context.getResources(), dataID);
		spriteWidth = this.bitmap.getWidth() / frameNr;
		spriteHeight = this.bitmap.getHeight();
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Rect getRect() {
		if(useScale)
			return curRect;
		else
			return sourceRect;
	}
	public Rect getSourceRect() {
		return sourceRect;
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}
	public int getFrameNr() {
		return frameNr;
	}
	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}
	public int getCurrentFrame() {
		return currentFrame;
	}
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	public int getFramePeriod() {
		return framePeriod;
	}
	public void setFramePeriod(int framePeriod) {
		this.framePeriod = framePeriod;
	}
	public int getSpriteWidth() {
		if(useScale)
			return (int)(spriteWidth*scale_x);
		else
			return spriteWidth;
	}
	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}
	public int getSpriteHeight() {
	if(useScale)
		return (int)(spriteHeight*scale_y);
	else
		return spriteHeight;
	}
	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
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
	public int getLoop() {
		return loop;
	}
	public void setLoop(int loop) {
		this.loop = loop;
		currentFrame = 0;
	}
	public boolean getFinish()
	{
		return (loop == 0);
	}
	public void resetFrame()
	{
		currentFrame = 0;
	}
	public void Scale(float x, float y)
	{
		useScale = true;
		scale_x = x;
		scale_y = y;
		curRect = new Rect(0, 0, (int)(spriteWidth*scale_x), (int)(spriteHeight*scale_y));
	}
	public void Scale(float s)
	{
		useScale = true;
		scale_x = s;
		scale_y = s;
		curRect = new Rect(0, 0, (int)(spriteWidth*scale_x), (int)(spriteHeight*scale_y));
	}
	public void UnScale()
	{
		useScale = false;
		scale_x = 1;
		scale_y = 1;
		curRect = new Rect(sourceRect);
	}
	public void update(long gameTime) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			currentFrame++;
			if (currentFrame >= frameNr) {
				if(loop > 0)
					loop--;
				if(loop != 0)
					currentFrame = 0;
			}
		}
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
		if(useScale)
		{
			this.curRect.left = this.sourceRect.left;
			this.curRect.right = this.sourceRect.left + (int)(spriteWidth*scale_x);
		}
	}
	
	public void update() {
		update(System.currentTimeMillis());
	}
	public void draw(Canvas canvas) {
		Rect destRect;
		destRect = new Rect(getX(), getY(), getX() + this.getSpriteWidth(), getY() + this.getSpriteHeight());
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		if(Configs.debug_Rect)
			Untils.drawRect(canvas, this.getRect());
		if(Configs.debug_Sprite)
		{
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawText("l:"+loop+"f:"+currentFrame+"/"+frameNr, getX(), getY() + 10, p);
		}
	}
	public void draw(Canvas canvas, int x, int y) {
		Rect destRect;
		destRect = new Rect(getX() + x, getY() + y, getX() + x + this.getSpriteWidth(), getY() + y + this.getSpriteHeight());
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		if(Configs.debug_Rect)
		{
			Rect r = new Rect(getX() + x, getY() + y, getX() + x + this.getSpriteWidth(), getY() + y + this.getSpriteHeight());
			Untils.drawRect(canvas, r);
		}
		if(Configs.debug_Sprite)
		{
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawText("l:"+loop+"f:"+currentFrame+"/"+frameNr, getX(), getY() + 10, p);
		}
	}
}
