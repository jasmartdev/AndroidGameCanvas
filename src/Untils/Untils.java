/**
 * 
 */
package Untils;

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import java.lang.StringBuffer;

public class Untils {

	public static boolean isTouchInRect(float x, float y, float left, float top, float w, float h)
	{
		return (x > left && x < left + w && y > top && y < top + h);
	}
	
	public static boolean isTouchInRect(float x, float y, Rect r)
	{
		return r.contains((int)x,(int)y);
	}
	
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
		  Matrix matrix = new Matrix();
		  matrix.postRotate(angle);
		  return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}
	
	public static void Dbg(String s)
	{
		System.out.println("+++ DEBUG +++ : "+s);
	}
	public static void Dbg_scr(Canvas s_canvas, String s, int x, int y)
	{
		s_canvas.drawText(s, x, y, new Paint());
	}
	public static void Dbg_scr(Canvas s_canvas, String s, float x, float y)
	{
		s_canvas.drawText(s, (int)x, (int)y, new Paint());
	}
	public static void drawRect(Canvas canvas, Rect r)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.RED);
		myPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(r, myPaint);
	}
	public static void drawString(Canvas canvas, String s, int x, int y, int cl, float size, int align)
	{
		Paint myPaint = new Paint();		
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(s, x, (int)(y + size/2), myPaint);
	}
	public static void drawString(Canvas canvas, String s, float x, float y, int cl, float size, int align)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(s, (int)x, (int)(y + size/2), myPaint);
	}
	public static void drawPage(Canvas canvas, String s, int x, int y, int cl, float size, int linespace, int align)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		int countline = countLine(s);
		float dy = -(countline*(size/2+linespace));
		for(String line: s.split("\n")){			
			canvas.drawText(line, (int)(x), (int)(y + dy), myPaint);
			dy+=(size+linespace);
		}
	}
	public static void drawPage(Canvas canvas, String s, int x, int y, int cl, float size, int align)
	{
		drawPage(canvas, s, x, y, cl, size, 0, align);
	}
	public static void drawPage(Canvas canvas, String s, float x, float y, int cl, float size, int linespace, int align)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		int countline = countLine(s);
		float dy = -(countline*(size/2+linespace));
		for(String line: s.split("\n")){			
			canvas.drawText(line, (int)(x), (int)(y + dy), myPaint);
			dy+=(size+linespace);
		}
	}
	public static void drawPage(Canvas canvas, String s, float x, float y, int cl, float size, int align)
	{
		drawPage(canvas, s, x, y, cl, size, 0, align);
	}
	public static String wrapText(String s, float w, float size)
	{
		Paint myPaint = new Paint();
		myPaint.setTextSize(size);
		StringBuffer sbf = new StringBuffer(s);
		int start = 0, last = 0;
		for(int i = 0; i < sbf.length(); i++)
		{
			if(myPaint.measureText(sbf, start, i) < w)
			{
				if(sbf.charAt(i) == ' ' || sbf.charAt(i) == '\t' || sbf.charAt(i) == '.')
				{
					last = i;
				}
			}			
			else
			{
				sbf.setCharAt(last,'\n');
				start = last;
				last = i;					
			}
		}
		return sbf.toString();
	}
	public static String wrapText(String s, float w, int size)
	{
		Paint myPaint = new Paint();
		myPaint.setTextSize(size);
		StringBuffer sbf = new StringBuffer(s);
		int start = 0, last = 0;
		for(int i = 0; i < sbf.length(); i++)
		{
			if(myPaint.measureText(sbf, start, i) < w)
			{
				if(sbf.charAt(i) == ' ' || sbf.charAt(i) == '\t')
				{
					last = i;
				}
			}			
			else
			{
				sbf.setCharAt(last,'\n');
				start = last;
				last = i;					
			}
		}
		return sbf.toString();
	}
	public static int countLine(String s)
	{
		int count = 1;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '\n')
			{
				count++;
			}
		}
		return count;
	}
}
