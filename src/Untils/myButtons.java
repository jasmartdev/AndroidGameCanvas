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

import Untils.Align;


public class myButtons {

	private Bitmap bitmap;
	private Bitmap bitmap_touched;
	private int dataID;
	private int dataID2;
	private Rect sourceRect;
	private Rect curRect;
	private int btnWidth;
	private int btnHeight;	
	private float x;
	private float y;
	private int align;
	private int btnState;
	private boolean btnActive;
	private int btnID;
	private boolean useScale;
	private float scale_x;
	private float scale_y;
	private String text;
	private int textAlign;
	private int textColor;
	private int textSize;
	
	public myButtons(int dataID, int dataID2, int id, float x, float y, int align, String text, int textColor, int textSize, int textAlign) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		useScale = false;
		this.text = new String(text);
		this.textColor = textColor;
		this.textSize = textSize;
		this.textAlign = textAlign;
	}
	
	public myButtons(int dataID, int dataID2, int id, int x, int y, int align, String text, int textColor, int textSize, int textAlign) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		useScale = false;
		this.text = new String(text);
		this.textColor = textColor;
		this.textSize = textSize;
		this.textAlign = textAlign;
	}
	
	public myButtons(int dataID, int dataID2, int id, float x, float y, int align) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		useScale = false;
		this.text = null;
		this.textColor = 0xff000000;
		this.textSize = 14;
		this.textAlign = Align.CENTER;
	}
	
	public myButtons(int dataID, int dataID2, int id, int x, int y, int align) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		useScale = false;
		this.text = null;
		this.textColor = 0xff000000;
		this.textSize = 14;
		this.textAlign = Align.CENTER;
	}
	
	public myButtons(myButtons otherButton)
	{
		this.dataID = otherButton.dataID;
		this.dataID2 = otherButton.dataID2;
		this.bitmap = Bitmap.createBitmap(otherButton.bitmap);
		this.bitmap_touched = Bitmap.createBitmap(otherButton.bitmap_touched);
		this.x = otherButton.x;
		this.y = otherButton.y;
		this.btnWidth = otherButton.btnWidth;
		this.btnHeight = otherButton.btnHeight;
		this.sourceRect = new Rect(otherButton.getSourceRect());
		this.curRect = new Rect(otherButton.getRect());
		this.align = otherButton.align;
		this.btnID = otherButton.btnID;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		this.useScale = otherButton.useScale;
		this.text = new String(otherButton.getText());
		this.textColor = otherButton.textColor;
		this.textSize = otherButton.textSize;
		this.textAlign = otherButton.textAlign;
	}
	
	public void Load(Context context)
	{
		this.bitmap = BitmapFactory.decodeResource(context.getResources(), dataID);
		this.bitmap_touched = BitmapFactory.decodeResource(context.getResources(), dataID2);
		this.btnWidth = bitmap.getWidth();
		this.btnHeight = bitmap.getHeight();
		sourceRect = new Rect(0, 0, btnWidth, btnHeight);
		curRect = new Rect((int)x, (int)y, (int)(x + btnWidth), (int)(y + btnHeight));
	}
	
	public interface buttonState {
		final static int UNTOUCH = 0;
		final static int TOUCH = 1;
	}	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public Bitmap getBitmap_touched() {
		return bitmap_touched;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public void setBitmap_touched(Bitmap bitmap) {
		this.bitmap_touched = bitmap_touched;
	}
	public Rect getRect() {
		Rect r = new Rect(curRect);
		if((align & Align.HCENTER) != 0)
		{
			r.left -= getWidth()/2;
			r.right -= getWidth()/2;
		}
		else if((align & Align.RIGHT) != 0)
		{
			r.left -= getWidth();
			r.right -= getWidth();
		}
		if((align & Align.VCENTER) != 0)
		{
			r.top -= getHeight()/2;
			r.bottom -= getHeight()/2;
		}
		else if((align & Align.BOTTOM) != 0)
		{
			r.top -= getHeight();
			r.bottom -= getHeight();
		}
		return r;
	}
	public Rect getSourceRect() {
		return sourceRect;
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}
	public int getState() {
		return btnState;
	}
	public void setState(int state) {
		this.btnState = state;
	}
	public int getID() {
		return btnID;
	}
	public void setID(int id) {
		this.btnID = id;
	}
	public boolean getActive() {
		return btnActive;
	}
	public void setActive(boolean active) {
		this.btnActive = active;
	}
	public int getLeft() {
		return getRect().left;
	}
	public int getRight() {
		return getRect().right;
	}
	public int getTop() {
		return getRect().top;
	}
	public int getBottom() {
		return getRect().bottom;
	}
	public int getCenterX() {
		return getRect().centerX();
	}
	public int getCenterY() {
		return getRect().centerY();
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = new String(text);
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public int gettextSize() {
		return textSize;
	}
	public void settextSize(int textSize) {
		this.textSize = textSize;
	}
	public int getTextAlign() {
		return textAlign;
	}
	public void setTextAlign(int textAlign) {
		this.textAlign = textAlign;
	}
	public void Scale(float scale_x, float scale_y)
	{
		useScale = true;
		this.scale_x = scale_x;
		this.scale_y = scale_y;
		this.btnWidth = (int)(bitmap.getWidth()*scale_x);
		this.btnHeight = (int)(bitmap.getHeight()*scale_y);
		curRect = new Rect((int)x, (int)y, (int)(x + btnWidth), (int)(y + btnHeight));
	}
	public void Scale(float s)
	{
		useScale = true;
		scale_x = s;
		scale_y = s;
		this.btnWidth = (int)(bitmap.getWidth()*scale_x);
		this.btnHeight = (int)(bitmap.getHeight()*scale_y);
		curRect = new Rect((int)x, (int)y, (int)(x + btnWidth), (int)(y + btnHeight));
	}
	public void UnScale()
	{
		useScale = false;
		scale_x = 1;
		scale_y = 1;
		this.btnWidth = bitmap.getWidth();
		this.btnHeight = bitmap.getHeight();
		curRect = new Rect((int)x, (int)y, (int)(x + btnWidth), (int)(y + btnHeight));
	}
	
	public int getWidth() {
		return btnWidth;
	}
	public void setWidth(int btnWidth) {
		this.btnWidth = btnWidth;
	}
	public int getHeight() {
		return btnHeight;
	}
	public void setHeight(int btnHeight) {
		this.btnHeight = btnHeight;
	}
	public void update(boolean touch, float x, float y) {
		if(touch)
		{
			if(Untils.isTouchInRect(x, y, getRect()))
				setState(buttonState.TOUCH);
			else
				setState(buttonState.UNTOUCH);
		}
		else
		{
			if(getState() == buttonState.TOUCH && Untils.isTouchInRect(x, y, getRect()))
			{
				setActive(true);
			}
			else
			{
				setActive(false);
			}			
			setState(buttonState.UNTOUCH);
		}
	}
	
	public void draw(Canvas canvas) {
		if(getState() == buttonState.UNTOUCH)
			canvas.drawBitmap(bitmap, sourceRect, getRect(), null);
		else
			canvas.drawBitmap(bitmap_touched, sourceRect, getRect(), null);
		if(text != null)
		{
			Untils.drawString(canvas, text, (int)(getCenterX()), (int)(getCenterY()), textColor, textSize, textAlign);
		}
		if(Configs.debug_Rect)
			Untils.drawRect(canvas, getRect());
	}
}
