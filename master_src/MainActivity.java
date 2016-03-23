package Main.Ufo;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.WindowManager;
import java.util.Random;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
#ifdef USE_BG_BUFFER
import android.graphics.Rect;
#endif

import Untils.Untils;
import Untils.myButtons;
import Untils.mySprites;
import Untils.myTouch;
import Untils.Align;
import Untils.mySound;
import Untils.myDialog;


public class MainActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Untils.Dbg("MainActivity onCreate");
		super.onCreate(savedInstanceState);
		s_mainActive = this;
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		#ifdef USE_BG_BUFFER
		SCREEN_WIDTH_PHONE = display.getWidth();
		SCREEN_HEIGHT_PHONE = display.getHeight();
		SCALE_WIDTH = 1;
		SCALE_HEIGHT = 1;
		#else
		SCREEN_WIDTH = display.getWidth();
		SCREEN_HEIGHT = display.getHeight();
		SCALE_WIDTH = SCREEN_WIDTH/SCREEN_WIDTH_ORIGINAL;
		SCALE_HEIGHT = SCREEN_HEIGHT/SCREEN_HEIGHT_ORIGINAL;
		#endif
		mySurfaceView = new MySurfaceView(this);
		setContentView(mySurfaceView);
		Untils.Dbg("MainActivity onCreate SCREEN_WIDTH:"+SCREEN_WIDTH+" SCREEN_HEIGHT:"+SCREEN_HEIGHT);
	}

	@Override
	protected void onResume() {
		Untils.Dbg("MainActivity onResume");
		super.onResume();
		mySurfaceView.onResumeMySurfaceView();
	}

	@Override
	protected void onPause() {
		Untils.Dbg("MainActivity onPause");
		super.onPause();
		mySurfaceView.onPauseMySurfaceView();
	}
	
	@Override
    public void onStop()
    {
		Untils.Dbg("MainActivity onStop");
        super.onStop();
		mySurfaceView.onStopMySurfaceView();
    }
	
	@Override
    public void onDestroy()
    {
		Untils.Dbg("MainActivity onDestroy");
        super.onDestroy();
		mySurfaceView.onDestroyMySurfaceView();
    }
	
	@Override
	public void onBackPressed() {
		Untils.Dbg("MainActivity onBackPressed");
		s_isBackKeyPressed = true;
	}
	
#include "main_declare.h"

	class MySurfaceView extends SurfaceView implements Runnable{
	
		public MySurfaceView(Context context) {
			super(context);
			surfaceHolder = getHolder();
			s_game_state = gameState.STATE_INIT;
			s_init_state = true;
			#ifdef USE_BG_BUFFER
			s_touch = new myTouch(SCREEN_WIDTH/SCREEN_WIDTH_PHONE, SCREEN_HEIGHT/SCREEN_HEIGHT_PHONE);
			s_bg_buffer = Bitmap.createBitmap((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);
			s_canvas = new Canvas(s_bg_buffer);
			#else
			s_touch = new myTouch();
			#endif
			Untils.Dbg("MySurfaceView MySurfaceView");
		}

		public void onResumeMySurfaceView(){
			Untils.Dbg("MySurfaceView onResumeMySurfaceView");
			running = true;
			thread = new Thread(this);
			thread.start();
		}

		public void onPauseMySurfaceView(){
			Untils.Dbg("MySurfaceView onPauseMySurfaceView");
			boolean retry = true;
			running = false;
			while(retry){
				try {
					thread.join();
					retry = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void onStopMySurfaceView(){
			Untils.Dbg("MySurfaceView onStopMySurfaceView");
		}

		public void onDestroyMySurfaceView(){
			Untils.Dbg("MySurfaceView onDestroyMySurfaceView");
		}

		@Override
		public void run() {
			Untils.Dbg("MySurfaceView run");
			while(running){
				if(surfaceHolder.getSurface().isValid()){
					#ifdef USE_BG_BUFFER
					s_canvas_scale = surfaceHolder.lockCanvas();
					#else
					s_canvas = surfaceHolder.lockCanvas();
					#endif
					if(s_init_state)
						Game_Init();
					Game_Update();
					Game_Paint();
					if(s_exit_state)
						Game_Exit();
					#ifdef USE_BG_BUFFER
					surfaceHolder.unlockCanvasAndPost(s_canvas_scale);
					#else
					surfaceHolder.unlockCanvasAndPost(s_canvas);
					#endif
				}
			}
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {

			return s_touch.onTouchEvent(event);
		}

#include "sur_declare.h"
#include "main.h"
#include "state_init.h"
#include "state_logo.h"
#include "state_splash.h"
#include "state_load.h"
#include "state_play.h"
#include "state_mainmenu.h"
#include "state_help.h"
#include "state_about.h"
#include "state_igm.h"
#include "state_result.h"

	}
}
