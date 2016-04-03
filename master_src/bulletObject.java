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

	public bulletObject(final mySprites spr1, final mySprites spr2, int x, int y, int vx, int vy) {
		super(spr1, spr2, -1, x, y, vx, vy);
	}
	
	public bulletObject(final mySprites spr1, final mySprites spr2, int x, int y) {
		super(spr1, spr2, x, y);
	}

	@Override
	public void updatePos(int delta) {
		this.x = this.x + vx*delta/Config.s_framePeriod;
		this.y = this.y + vy*delta/Config.s_framePeriod;
	}
	@Override
	public int getType()
	{
		return 1;
	}
}
