/**
 * 
 */
package Untils;

import android.view.MotionEvent;

public class myTouch {

	public static float x;
	public static float y;
	public static float x_scale;
	public static float y_scale;
	public static int btnState;
	public static boolean istouch = false;
	
	public interface touchState {
		final static int UNTOUCH = 0;
		final static int TOUCH = 1;
	}
	
	public myTouch() {
		this.x = -1;
		this.y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
		x_scale = 1;
		y_scale = 1;
	}
		
	public myTouch(float x_scale, float y_scale) {
		this.x = -1;
		this.y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
		this.x_scale = x_scale;
		this.y_scale = y_scale;
	}
	
	public boolean onTouchEvent(MotionEvent event) {

		x = (event.getX())*x_scale;
		y = (event.getY())*y_scale;
		Untils.Dbg("x_scale"+x_scale+"y_scale"+y_scale+"x"+x+"y"+y+"event.getX()"+event.getX()+"event.getY()"+event.getY());
		int action = event.getAction();
		switch(action){
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				istouch = true;
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_OUTSIDE:
				istouch = false;
				break;
			default:
		}
		return istouch;
	}
	public static void resetTouch()
	{
		x = -1;
		y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
	}
	public boolean getTouch() {
		return istouch;
	}
	public void setTouch(boolean touch) {
		this.istouch = touch;
	}
	public int getState() {
		return btnState;
	}
	public void setState(int state) {
		this.btnState = state;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x*x_scale;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y*y_scale;
	}
}
