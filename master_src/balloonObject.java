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

public class balloonObject extends movableObject{

	public balloonObject(mySprites spr1, mySprites spr2, int id, int x, int y, float angle, float speed) {
		super(spr1, spr2, id, x, y, angle, speed);
	}
	
	public balloonObject(mySprites spr1, mySprites spr2, int x, int y) {
		super(spr1, spr2, x, y);
	}
	
	public balloonObject(balloonObject other) {
		super((movableObject)other);
	}
	@Override
	public void updatePos() {
		int vx = (int)(this.speed*this.angle);
		int vy = (int)(this.speed/this.angle);
		this.x = this.x + vx;
		this.y = this.y - vy;
	}
}
