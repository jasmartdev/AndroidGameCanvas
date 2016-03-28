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

public class bulletObject extends movableObject{

	public bulletObject(mySprites spr1, mySprites spr2, int x, int y, int vx, int vy) {
		super(spr1, spr2, -1, x, y, vx, vy);
	}
	
	public bulletObject(mySprites spr1, mySprites spr2, int x, int y) {
		super(spr1, spr2, x, y);
	}

	public bulletObject(bulletObject other) {
		super((movableObject)other);
	}
	
	@Override
	public int getType()
	{
		return 1;
	}
}
