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

import jasmartdev.untils.mySprites;
import jasmartdev.untils.Untils;

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
	public void updatePos(int delta) {
		this.x = this.x + vx*delta/Config.s_framePeriod;
		this.y = this.y + vy*delta/Config.s_framePeriod;
		// Untils.Dbg("x:"+x+" y:"+y+" vx:"+vx+" vy:"+vy);
	}
	@Override
	public int getType()
	{
		return 1;
	}
}
