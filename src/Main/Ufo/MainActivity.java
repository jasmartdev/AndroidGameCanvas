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

import android.graphics.Rect;


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
		
		SCREEN_WIDTH_PHONE = display.getWidth();
		SCREEN_HEIGHT_PHONE = display.getHeight();
		SCALE_WIDTH = 1;
		SCALE_HEIGHT = 1;
		
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
	
MySurfaceView mySurfaceView;
private static MainActivity s_mainActive;

final static float SCREEN_WIDTH = 480;
final static float SCREEN_HEIGHT = 800;
public static float SCREEN_WIDTH_PHONE;
public static float SCREEN_HEIGHT_PHONE;

public static float SCALE_WIDTH;
public static float SCALE_HEIGHT;
public boolean s_isBackKeyPressed = false;
public final String refName = "gamofun2014_dis";

public static int ScaleX(float x)
{
	return (int)(x*SCALE_WIDTH);
}
public static int ScaleX(int x)
{
	return (int)(x*SCALE_WIDTH);
}
public static int ScaleY(float y)
{
	return (int)(y*SCALE_HEIGHT);
}
public static int ScaleY(int y)
{
	return (int)(y*SCALE_HEIGHT);
}
interface gameState {
	final static int STATE_INIT = 0;
	final static int STATE_LOAD = STATE_INIT + 1;
	final static int STATE_LOGO = STATE_LOAD + 1;
	final static int STATE_SPLASH = STATE_LOGO + 1;
	final static int STATE_PLAY = STATE_SPLASH + 1;
	final static int STATE_MAINMENU = STATE_PLAY + 1;
	final static int STATE_HELP = STATE_MAINMENU + 1;
	final static int STATE_ABOUT = STATE_HELP + 1;
	final static int STATE_IGM = STATE_ABOUT + 1;
	final static int STATE_RESULT = STATE_IGM + 1;
}

interface State {
	final static int INIT = 0;
	final static int UPDATE = INIT + 1;
	final static int PAINT = UPDATE + 1;
	final static int EXIT = PAINT + 1;
}

interface loadType {
	final static int LOAD_SYSTEM = 0;
	final static int LOAD_GAMEPLAY = LOAD_SYSTEM + 1;
	final static int LOAD_MAINMENU = LOAD_GAMEPLAY + 1;
}

interface buttonID {
	final static int BUTTON_PLAY = 0;
	final static int BUTTON_HELP = 1;
	final static int BUTTON_ABOUT = 2;
	final static int BUTTON_PAUSE = 3;
	final static int BUTTON_CLOSE = 4;
	final static int BUTTON_IGM_RESUME = 5;
	final static int BUTTON_IGM_REPLAY = 6;
	final static int BUTTON_IGM_MM = 7;
	final static int BUTTON_IGM_EXIT = 8;
	final static int BUTTON_SFX = 9;
	final static int BUTTON_MUSIC = 10;
}

interface dialogID {
	final static int DIALOG_REPLAY = 0;
	final static int DIALOG_MM = 1;
	final static int DIALOG_EXIT = 2;
}

interface Define {
	final static int NUM_DIS_MAX = 5;
	final static int NUM_DIS_LEVEL = 5;
	final static float BASE_ANGLE = 2;
	final static float ANGLE_DELTA = 2;
	final static float BASE_SPEED = 2;
	final static float SPEED_DELTA = 0.5f;
	final static float BASE_START_X = ScaleX(-100);
	final static float BASE_START_Y = ScaleY(200);
	final static int START_Y_DELTA = 30;
	final static float NUM_STAR1 = 0.6f;
	final static float NUM_STAR2 = 0.75f;
	final static float NUM_STAR3 = 0.9f;
	final static int LOGO_FRAME = 10;
	
	final static int BTN_PLAY_Y = ScaleY(10);
	final static int BTN_PLAY_X = ScaleX(10);
	final static int BTN_HELP_Y = ScaleY(250);
	final static int BTN_HELP_X = (int)(SCREEN_WIDTH - 10);
	final static int BTN_ABOUT_Y = ScaleY(470);
	final static int BTN_ABOUT_X = ScaleX(10);;
	final static int BTN_PAUSE_X = (int)(SCREEN_WIDTH);
	final static int BTN_CLOSE_X = (int)(SCREEN_WIDTH);
	
	final static int BTN_MUSIC_Y = ScaleY(10);
	final static int BTN_MUSIC_X = (int)(SCREEN_WIDTH - 10);
	final static int BTN_SFX_X = (int)(SCREEN_WIDTH - 10 - ScaleX(50));
	
	final static int TEXT_SIZE_SMALL = 7 + ScaleX(9);
	final static int TEXT_SIZE_MED = 7 + ScaleX(13);
	final static int TEXT_SIZE_LARGE = 7 + ScaleX(18);
	final static int TEXT_SIZE_VERY_LARGE = 7 + ScaleX(38);
	
	
	final static int HUD_HP_Y = TEXT_SIZE_MED/2 + 4;
	final static int HUD_HP_X = ScaleX(6);
	final static int HUD_HP_BAR_X = HUD_HP_X + 30;
	final static int HUD_HP_W = ScaleX(200);
	final static int HUD_HP_H = TEXT_SIZE_MED;
	final static int HUD_LEVEL_Y = HUD_HP_Y + HUD_HP_H + 4;
	final static int HUD_LEVEL_X = ScaleX(6);
	final static int HUD_HEIGHT = ScaleX(82);//HUD_LEVEL_Y + TEXT_SIZE_MED/2 + 4;
	
	final static int IGM_TITLE_Y = ScaleY(60);
	final static int IGM_BTN_Y = ScaleY(170);
	final static int IGM_BTN_H = ScaleY(120);
	
	final static int RESULT_TITLE_Y = ScaleY(40);
	final static int RESULT_RATE_Y = RESULT_TITLE_Y + TEXT_SIZE_LARGE + ScaleY(50);
	final static int RESULT_HIGHSCORE_Y = RESULT_RATE_Y + TEXT_SIZE_LARGE + ScaleY(50);
	final static int RESULT_SCORE_Y = RESULT_HIGHSCORE_Y + TEXT_SIZE_LARGE + ScaleX(5);
	final static float RESULT_RATE_ICON_Y = RESULT_TITLE_Y + TEXT_SIZE_LARGE + ScaleY(12);
	final static float RESULT_RATE_ICON_X = SCREEN_WIDTH/2 + ScaleX(20);
	
	final static float GROUND_Y = SCREEN_HEIGHT - ScaleX(253);
	
	final static float GUN_Y = SCREEN_HEIGHT - ScaleX(250);
	final static float GUN_OFF_X = ScaleX(50);
	
	final static int BLOOD_MAX = 10;
	final static int BLOOD_PER = 5;
}


	class MySurfaceView extends SurfaceView implements Runnable{
	
		public MySurfaceView(Context context) {
			super(context);
			surfaceHolder = getHolder();
			s_game_state = gameState.STATE_INIT;
			s_init_state = true;
			
			s_touch = new myTouch(SCREEN_WIDTH/SCREEN_WIDTH_PHONE, SCREEN_HEIGHT/SCREEN_HEIGHT_PHONE);
			
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
					
					s_canvas_scale = surfaceHolder.lockCanvas();
					s_bg_buffer = Bitmap.createBitmap((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);
					s_canvas = new Canvas(s_bg_buffer);
					
					if(s_init_state)
						Game_Init();
					Game_Update();
					Game_Paint();
					if(s_exit_state)
						Game_Exit();
					
					surfaceHolder.unlockCanvasAndPost(s_canvas_scale);
					
				}
			}
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {

			return s_touch.onTouchEvent(event);
		}

Thread thread = null;
SurfaceHolder surfaceHolder;
Canvas s_canvas;

Canvas s_canvas_scale;
Bitmap s_bg_buffer;

mySound snd_player;
volatile int s_game_state = -1;
volatile int s_game_next_state = -1;
volatile int s_load_step_max = -1;
volatile int s_load_step = 0;
volatile int s_load_type = -1;
volatile boolean s_init_state = false;
volatile boolean s_exit_state = false;
volatile boolean s_skip_init_state = false;
volatile boolean running = false;
volatile boolean music_enable;
volatile boolean sfx_enable;
volatile myTouch s_touch;
volatile myDis[] dis;
volatile myDialog s_dialog;
volatile boolean s_showdialog = false;
volatile int s_blood = 100;
volatile float s_rate = 0;
volatile int s_level = 1;
volatile int s_score = 0;
volatile int s_highscore = 0;
volatile int[] s_levelUp = {0, 15, 35, 60, 100, 99999};
myButtons btn_play;
myButtons btn_help;
myButtons btn_about;
myButtons btn_gamePause;
myButtons btn_close;
myButtons btn_resume;
myButtons btn_replay;
myButtons btn_mm;
myButtons btn_exit;
myButtons btn_music_on;
myButtons btn_music_off;
myButtons btn_sfx_on;
myButtons btn_sfx_off;
volatile mySprites[] s_gameSprites;
volatile long s_frameCount;
volatile SharedPreferences s_ref;

public void Game_Init()
{
	switch(s_game_state)
	{
		case gameState.STATE_INIT:
			s_game_STATE_INIT(State.INIT);
			break;
		case gameState.STATE_LOGO:
			s_game_STATE_LOGO(State.INIT);
			break;
		case gameState.STATE_SPLASH:
			s_game_STATE_SPLASH(State.INIT);
			break;
		case gameState.STATE_LOAD:
			s_game_STATE_LOAD(State.INIT);
			break;
		case gameState.STATE_PLAY:
			s_game_STATE_PLAY(State.INIT);
			break;
		case gameState.STATE_MAINMENU:
			s_game_STATE_MAINMENU(State.INIT);
			break;
		case gameState.STATE_HELP:
			s_game_STATE_HELP(State.INIT);
			break;
		case gameState.STATE_ABOUT:
			s_game_STATE_ABOUT(State.INIT);
			break;
		case gameState.STATE_IGM:
			s_game_STATE_IGM(State.INIT);
			break;
		case gameState.STATE_RESULT:
			s_game_STATE_RESULT(State.INIT);
			break;
		default:
	}
	s_init_state = false;
	s_frameCount = 0;
	
}
public void Game_Update()
{
	if(s_dialog != null && s_dialog.getActive())
	{
		s_dialog.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		dialog_update();
	}
	else
	{
		switch(s_game_state)
		{
			case gameState.STATE_INIT:
				s_game_STATE_INIT(State.UPDATE);
				break;
			case gameState.STATE_LOGO:
				s_game_STATE_LOGO(State.UPDATE);
				break;
			case gameState.STATE_SPLASH:
				s_game_STATE_SPLASH(State.UPDATE);
				break;
			case gameState.STATE_LOAD:
				s_game_STATE_LOAD(State.UPDATE);
				break;
			case gameState.STATE_PLAY:
				s_game_STATE_PLAY(State.UPDATE);
				break;
			case gameState.STATE_MAINMENU:
				s_game_STATE_MAINMENU(State.UPDATE);
				break;
			case gameState.STATE_HELP:
				s_game_STATE_HELP(State.UPDATE);
				break;
			case gameState.STATE_ABOUT:
				s_game_STATE_ABOUT(State.UPDATE);
				break;
			case gameState.STATE_IGM:
				s_game_STATE_IGM(State.UPDATE);
				break;
			case gameState.STATE_RESULT:
				s_game_STATE_RESULT(State.UPDATE);
				break;
			default:
		}
	}
	s_isBackKeyPressed = false;
	s_frameCount++;
}
public void Game_Paint()
{
	if(s_dialog != null && s_dialog.getActive())
	{
		s_dialog.draw(s_canvas);
	}
	else
	{
		switch(s_game_state)
		{
			case gameState.STATE_INIT:
				s_game_STATE_INIT(State.PAINT);
				break;
			case gameState.STATE_LOGO:
				s_game_STATE_LOGO(State.PAINT);
				break;
			case gameState.STATE_SPLASH:
				s_game_STATE_SPLASH(State.PAINT);
				break;
			case gameState.STATE_LOAD:
				s_game_STATE_LOAD(State.PAINT);
				break;
			case gameState.STATE_PLAY:
				s_game_STATE_PLAY(State.PAINT);
				break;
			case gameState.STATE_MAINMENU:
				s_game_STATE_MAINMENU(State.PAINT);
				break;
			case gameState.STATE_HELP:
				s_game_STATE_HELP(State.PAINT);
				break;
			case gameState.STATE_ABOUT:
				s_game_STATE_ABOUT(State.PAINT);
				break;
			case gameState.STATE_IGM:
				s_game_STATE_IGM(State.PAINT);
				break;
			case gameState.STATE_RESULT:
				s_game_STATE_RESULT(State.PAINT);
				break;
			default:
		}
	}
	
	s_canvas_scale.drawBitmap(s_bg_buffer, new Rect(0, 0, (int)SCREEN_WIDTH, (int)SCREEN_HEIGHT), new Rect(0, 0, (int)SCREEN_WIDTH_PHONE, (int)SCREEN_HEIGHT_PHONE), null);
	
}
public void Game_Exit()
{
	switch(s_game_state)
	{
		case gameState.STATE_INIT:
			s_game_STATE_INIT(State.EXIT);
			break;
		case gameState.STATE_LOGO:
			s_game_STATE_LOGO(State.EXIT);
			break;
		case gameState.STATE_SPLASH:
			s_game_STATE_SPLASH(State.EXIT);
			break;
		case gameState.STATE_LOAD:
			s_game_STATE_LOAD(State.EXIT);
			break;
		case gameState.STATE_PLAY:
			s_game_STATE_PLAY(State.EXIT);
			break;
		case gameState.STATE_MAINMENU:
			s_game_STATE_MAINMENU(State.EXIT);
			break;
		case gameState.STATE_HELP:
			s_game_STATE_HELP(State.EXIT);
			break;
		case gameState.STATE_ABOUT:
			s_game_STATE_ABOUT(State.EXIT);
			break;
		case gameState.STATE_IGM:
			s_game_STATE_IGM(State.EXIT);
			break;
		case gameState.STATE_RESULT:
			s_game_STATE_RESULT(State.EXIT);
			break;
		default:
	}
	s_exit_state = false;
	if(s_skip_init_state)
		s_skip_init_state = false;
	else
		s_init_state = true;
}
void dialog_update()
{
	if(s_dialog.getActive())
	{
		if(s_dialog.isYes())
		{
			switch(s_dialog.getID())
			{
				case dialogID.DIALOG_REPLAY:						
					s_game_next_state = gameState.STATE_PLAY;
					s_exit_state = true;
					break;
				case dialogID.DIALOG_MM:						
					s_game_next_state = gameState.STATE_LOAD;
					s_load_type = loadType.LOAD_MAINMENU;
					s_exit_state = true;
					break;
				case dialogID.DIALOG_EXIT:
					exit();
					break;
				default:
			}
			s_dialog.setActive(false);
			
		}
		else if(s_dialog.isNo())
		{
			s_dialog.setActive(false);
			
		}
	}
}
void drawLoandingBar(int per, int max, float x, float y, float w, float h, int color, int bcolor)
{
	Paint myPaint = new Paint();
	myPaint.setColor(Color.rgb(0, 0, 0));
	myPaint.setColor(bcolor);
	s_canvas.drawRect(x -  1, y - 1, x + w + 1, y + h + 1, myPaint);
	myPaint.setColor(color);
	s_canvas.drawRect(x, y, x + w*per/max, y + h, myPaint);
}
void drawLoandingBar(int per, int max, float x, float y, float w, float h)
{
	drawLoandingBar(per, max, x, y, w, h, Color.RED, Color.WHITE);
}
void drawLoandingBar(int per, int max)
{
	drawLoandingBar(per, max, (float)20, SCREEN_HEIGHT - 100, SCREEN_WIDTH - 40, (float)20);
}
boolean levelUp()
{
	if(myDis.getCount() >= s_levelUp[s_level])
	{
		dis[s_level].start();
		s_level++;
		return true;
	}
	return false;
}
void exit()
{
	snd_player.stopAll();
	finish();
}

public void s_game_STATE_INIT(int state)
{
	if(state == State.INIT)
	{
		s_gameSprites = new mySprites[DATA.SPR_MAX];
		s_ref = getSharedPreferences(refName, 0);
	}
	else if(state == State.UPDATE)
	{
		s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_LOGO;
	}
}

public void s_game_STATE_LOGO(int state)
{
	if(state == State.INIT)
	{				
		s_gameSprites[DATA.SPR_LOGO] = new mySprites(R.drawable.logo, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/2 - 120);
		s_gameSprites[DATA.SPR_LOGO].Load(s_mainActive.getApplicationContext());
	}
	else if(state == State.UPDATE)
	{
		if(s_frameCount > Define.LOGO_FRAME)
			s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.rgb(74, 246, 14));
		s_canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, myPaint);
		s_gameSprites[DATA.SPR_LOGO].draw(s_canvas);
	}
	else if(state == State.EXIT)
	{
		
		s_game_state = gameState.STATE_LOAD;
		s_load_type = loadType.LOAD_SYSTEM;
		
	}
}

public void s_game_STATE_SPLASH(int state)
{
	if(state == State.INIT)
	{
		
	}
	else if(state == State.UPDATE)
	{
		if(s_touch.getTouch())
			s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		s_gameSprites[DATA.SPR_SPLASH].draw(s_canvas);
		Untils.drawString(s_canvas, DATA.TXT_TOUCH_TO_CONTINUE, SCREEN_WIDTH/2, SCREEN_HEIGHT/2, Color.BLUE, 20, Align.CENTER);
		Untils.Dbg_scr(s_canvas, "W:"+SCREEN_WIDTH+" H:"+SCREEN_HEIGHT, 0, 10);
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_LOAD;
		s_load_type = loadType.LOAD_SYSTEM;
	}
}

public void s_game_STATE_LOAD(int state)
{
	if(state == State.INIT)
	{
		if(s_load_type == loadType.LOAD_SYSTEM)
		{
			s_load_step = 0;
			s_load_step_max = 10;
		}
		else if(s_load_type == loadType.LOAD_GAMEPLAY)
		{
			s_load_step = 0;
			s_load_step_max = 10;
		}
		else if(s_load_type == loadType.LOAD_MAINMENU)
		{
			s_load_step = 0;
			s_load_step_max = 4;
		}
	}
	else if(state == State.UPDATE)
	{
		if(s_load_step >= s_load_step_max)
		{
			s_exit_state = true;
		}
		else
		{
			if(s_load_type == loadType.LOAD_SYSTEM)
			{						
				switch(s_load_step)
				{
					case 0: //init and load RMS
						music_enable = true;
						sfx_enable = true;
						s_highscore = s_ref.getInt("s_highscore", 0);
					case 1: //load buttons
						if(btn_play == null)
						{
							btn_play = new myButtons(R.drawable.btn_play_up, R.drawable.btn_play_down, buttonID.BUTTON_PLAY, Define.BTN_PLAY_X, Define.BTN_PLAY_Y, 0x0);
							btn_play.Load(s_mainActive.getApplicationContext());
							btn_play.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_help == null)
						{
							btn_help = new myButtons(R.drawable.btn_blue_up, R.drawable.btn_blue_down, buttonID.BUTTON_HELP, Define.BTN_HELP_X, Define.BTN_HELP_Y, Align.RIGHT, DATA.TXT_BTN_HELP, Color.WHITE, Define.TEXT_SIZE_VERY_LARGE, Align.CENTER);
							btn_help.Load(s_mainActive.getApplicationContext());
							btn_help.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_about == null)
						{
							btn_about = new myButtons(R.drawable.btn_green_up, R.drawable.btn_green_down, buttonID.BUTTON_ABOUT, Define.BTN_ABOUT_X, Define.BTN_ABOUT_Y, 0x0, DATA.TXT_BTN_ABOUT, Color.WHITE, Define.TEXT_SIZE_VERY_LARGE, Align.CENTER);
							btn_about.Load(s_mainActive.getApplicationContext());
							btn_about.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_close == null)
						{
							btn_close = new myButtons(R.drawable.close_up, R.drawable.close_down, buttonID.BUTTON_CLOSE, Define.BTN_PAUSE_X, 0, Align.RIGHT);
							btn_close.Load(s_mainActive.getApplicationContext());
							btn_close.Scale(SCALE_WIDTH);
						}						
						if(btn_music_on == null)
						{
							btn_music_on = new myButtons(R.drawable.music_on, R.drawable.music_on, buttonID.BUTTON_MUSIC, Define.BTN_MUSIC_X, Define.BTN_MUSIC_Y, Align.RIGHT);
							btn_music_on.Load(s_mainActive.getApplicationContext());
							btn_music_on.Scale(SCALE_WIDTH);
						}
						if(btn_music_off == null)
						{
							btn_music_off = new myButtons(R.drawable.music_off, R.drawable.music_off, buttonID.BUTTON_MUSIC, Define.BTN_MUSIC_X, Define.BTN_MUSIC_Y, Align.RIGHT);
							btn_music_off.Load(s_mainActive.getApplicationContext());
							btn_music_off.Scale(SCALE_WIDTH);
						}						
						if(btn_sfx_on == null)
						{
							btn_sfx_on = new myButtons(R.drawable.sfx_on, R.drawable.sfx_on, buttonID.BUTTON_SFX, Define.BTN_SFX_X, Define.BTN_MUSIC_Y, Align.RIGHT);
							btn_sfx_on.Load(s_mainActive.getApplicationContext());
							btn_sfx_on.Scale(SCALE_WIDTH);
						}
						if(btn_sfx_off == null)
						{
							btn_sfx_off = new myButtons(R.drawable.sfx_off, R.drawable.sfx_off, buttonID.BUTTON_SFX, Define.BTN_SFX_X, Define.BTN_MUSIC_Y, Align.RIGHT);
							btn_sfx_off.Load(s_mainActive.getApplicationContext());
							btn_sfx_off.Scale(SCALE_WIDTH);
						}
						break;
					case 2: //load sprites
						
						if(s_gameSprites[DATA.SPR_SPLASH] == null)
						{
							s_gameSprites[DATA.SPR_SPLASH] = new mySprites(R.drawable.splash, 0, 0);
							s_gameSprites[DATA.SPR_SPLASH].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_SPLASH].Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						
						break;
					case 3: //load sound
						snd_player = new mySound(s_mainActive.getApplicationContext(), DATA.soundID);
						snd_player.loadAll();
						snd_player.setM_enable(music_enable);
						snd_player.setSfx_enable(sfx_enable);
						break;
					case 4: //load dialog
						s_dialog = new myDialog(R.drawable.dialog_bg, R.drawable.dialog_yes_up, R.drawable.dialog_yes_down, R.drawable.dialog_no_up, R.drawable.dialog_no_down, DATA.TXT_YES, DATA.TXT_NO, Define.TEXT_SIZE_MED, SCREEN_WIDTH/2, SCREEN_HEIGHT/4, SCALE_WIDTH, SCALE_HEIGHT);
						s_dialog.Load(s_mainActive.getApplicationContext());
					default:
				}
			}
			else if(s_load_type == loadType.LOAD_GAMEPLAY)
			{
				switch(s_load_step)
				{
					case 0: //load buttons
						if(btn_gamePause == null)
						{
							btn_gamePause = new myButtons(R.drawable.pause_up, R.drawable.pause_down, buttonID.BUTTON_PAUSE, Define.BTN_PAUSE_X, 0, Align.RIGHT);
							btn_gamePause.Load(s_mainActive.getApplicationContext());
							btn_gamePause.Scale(SCALE_WIDTH);
						}
						if(btn_resume == null)
						{
							btn_resume = new myButtons(R.drawable.button_up, R.drawable.button_down, buttonID.BUTTON_IGM_RESUME, SCREEN_WIDTH/2, Define.IGM_BTN_Y, Align.HCENTER, DATA.TXT_IGM_RESUME, Color.WHITE, Define.TEXT_SIZE_MED, Align.CENTER);
							btn_resume.Load(s_mainActive.getApplicationContext());
							btn_resume.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_replay == null)
						{
							btn_replay = new myButtons(R.drawable.button_up, R.drawable.button_down, buttonID.BUTTON_IGM_REPLAY, SCREEN_WIDTH/2, Define.IGM_BTN_Y + Define.IGM_BTN_H, Align.HCENTER, DATA.TXT_IGM_REPLAY, Color.WHITE, Define.TEXT_SIZE_MED, Align.CENTER);
							btn_replay.Load(s_mainActive.getApplicationContext());
							btn_replay.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_mm == null)
						{
							btn_mm = new myButtons(R.drawable.button_up, R.drawable.button_down, buttonID.BUTTON_IGM_MM, SCREEN_WIDTH/2, Define.IGM_BTN_Y + Define.IGM_BTN_H*2, Align.HCENTER, DATA.TXT_IGM_MM, Color.WHITE, Define.TEXT_SIZE_MED, Align.CENTER);
							btn_mm.Load(s_mainActive.getApplicationContext());
							btn_mm.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						if(btn_exit == null)
						{
							btn_exit = new myButtons(R.drawable.button_up, R.drawable.button_down, buttonID.BUTTON_IGM_EXIT, SCREEN_WIDTH/2, Define.IGM_BTN_Y + Define.IGM_BTN_H*3, Align.HCENTER, DATA.TXT_IGM_EXIT, Color.WHITE, Define.TEXT_SIZE_MED, Align.CENTER);
							btn_exit.Load(s_mainActive.getApplicationContext());
							btn_exit.Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						break;
					case 1: //load sprites
						if(s_gameSprites[DATA.SPR_DIS0] ==  null)
						{
							s_gameSprites[DATA.SPR_DIS0] = new mySprites(R.drawable.dis0, 0, 0);
							s_gameSprites[DATA.SPR_DIS0].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_DIS0].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_DIS_BURN] ==  null)
						{
							s_gameSprites[DATA.SPR_DIS_BURN] = new mySprites(R.drawable.dis_burn, 0, 0, 6);
							s_gameSprites[DATA.SPR_DIS_BURN].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_DIS_BURN].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_BGR_1] == null)
						{
							s_gameSprites[DATA.SPR_BGR_1] = new mySprites(R.drawable.bgr_1, 0, (float)Define.HUD_HEIGHT);
							s_gameSprites[DATA.SPR_BGR_1].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_BGR_1].Scale(SCREEN_WIDTH/300);
						}
						if(s_gameSprites[DATA.SPR_BGR_2] == null)
						{
							s_gameSprites[DATA.SPR_BGR_2] = new mySprites(R.drawable.bgr_2, 0, (float)Define.HUD_HEIGHT);
							s_gameSprites[DATA.SPR_BGR_2].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_BGR_2].Scale(SCREEN_WIDTH/300);
						}
						if(s_gameSprites[DATA.SPR_BGR_3] == null)
						{
							s_gameSprites[DATA.SPR_BGR_3] = new mySprites(R.drawable.bgr_3, 0, (float)Define.HUD_HEIGHT);
							s_gameSprites[DATA.SPR_BGR_3].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_BGR_3].Scale(SCREEN_WIDTH/300);
						}
						if(s_gameSprites[DATA.SPR_BGR_4] == null)
						{
							s_gameSprites[DATA.SPR_BGR_4] = new mySprites(R.drawable.bgr_4, 0, (float)Define.HUD_HEIGHT);
							s_gameSprites[DATA.SPR_BGR_4].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_BGR_4].Scale(SCREEN_WIDTH/300);
							s_gameSprites[DATA.SPR_BGR_5] = s_gameSprites[DATA.SPR_BGR_4];
						}
						if(s_gameSprites[DATA.SPR_GROUND] == null)
						{
							s_gameSprites[DATA.SPR_GROUND] = new mySprites(R.drawable.ground, 0, (float)Define.GROUND_Y);
							s_gameSprites[DATA.SPR_GROUND].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_GROUND].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_STAR0] == null)
						{
							s_gameSprites[DATA.SPR_STAR0] = new mySprites(R.drawable.star0, Define.RESULT_RATE_ICON_X, Define.RESULT_RATE_ICON_Y);
							s_gameSprites[DATA.SPR_STAR0].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_STAR0].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_STAR1] == null)
						{
							s_gameSprites[DATA.SPR_STAR1] = new mySprites(R.drawable.star1, Define.RESULT_RATE_ICON_X, Define.RESULT_RATE_ICON_Y);
							s_gameSprites[DATA.SPR_STAR1].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_STAR1].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_STAR2] == null)
						{
							s_gameSprites[DATA.SPR_STAR2] = new mySprites(R.drawable.star2, Define.RESULT_RATE_ICON_X, Define.RESULT_RATE_ICON_Y);
							s_gameSprites[DATA.SPR_STAR2].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_STAR2].Scale(SCALE_WIDTH);
						}
						if(s_gameSprites[DATA.SPR_STAR3] == null)
						{
							s_gameSprites[DATA.SPR_STAR3] = new mySprites(R.drawable.star3, Define.RESULT_RATE_ICON_X, Define.RESULT_RATE_ICON_Y);
							s_gameSprites[DATA.SPR_STAR3].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_STAR3].Scale(SCALE_WIDTH);
						}
						
						if(s_gameSprites[DATA.SPR_GUN] == null)
						{
							s_gameSprites[DATA.SPR_GUN] = new mySprites(R.drawable.gun, 0f, Define.GUN_Y);
							s_gameSprites[DATA.SPR_GUN].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_GUN].Scale(SCALE_WIDTH);
						}
						break;
					case 2: //load dis
						dis = new myDis[Define.NUM_DIS_MAX];
						for(int i = 0; i < Define.NUM_DIS_MAX; i++)
						{
							dis[i] = new myDis(s_gameSprites[DATA.SPR_DIS0], s_gameSprites[DATA.SPR_DIS_BURN], 0, Define.BASE_START_X, Define.BASE_START_Y, Define.BASE_ANGLE, Define.BASE_SPEED);
						}
						break;
					default:
				}
			}
			else if(s_load_type == loadType.LOAD_MAINMENU)
			{
				switch(s_load_step)
				{
					case 0:
						break;
					default:
				}
			}
			s_load_step++;
		}
	}
	else if(state == State.PAINT)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.BLACK);
		s_canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, myPaint);
		drawLoandingBar(s_load_step, s_load_step_max);
	}
	else if(state == State.EXIT)
	{
		if(s_load_type == loadType.LOAD_SYSTEM)
		{
			s_game_state = gameState.STATE_MAINMENU;
		}
		else if(s_load_type == loadType.LOAD_GAMEPLAY)
		{
			s_game_state = gameState.STATE_PLAY;
		}
		else if(s_load_type == loadType.LOAD_MAINMENU)
		{
			s_game_state = gameState.STATE_MAINMENU;
		}				
	}
}

public void s_game_STATE_PLAY(int state)
{
	if(state == State.INIT)
	{
		btn_gamePause.setActive(false);
		s_level = 1;
		s_score = 0;
		dis[0].resetScore();
		dis[0].resetCount();
		dis[0].start();
		s_blood = Define.BLOOD_MAX;
	}
	else if(state == State.UPDATE)
	{
		btn_gamePause.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_gamePause.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_IGM;
			s_exit_state = true;
		}
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
		{
			if(dis[i].checkOut(SCREEN_WIDTH, 0) || dis[i].isOut())
			{
				if(dis[i].checkOut(SCREEN_WIDTH, 0))
				{
					s_blood -= Define.BLOOD_PER;
					if(s_blood <= 0)
					{
						s_game_next_state = gameState.STATE_RESULT;
						s_exit_state = true;
					}
				}
				else if(dis[i].isOut())
				{
					s_score += s_level;
					if(s_rate >= Define.NUM_STAR3)
					{
						s_score += (int)(s_level/3);
					}
					else if(s_rate >= Define.NUM_STAR2)
					{
						s_score += (int)(s_level/4);
					}
					else if(s_rate >= Define.NUM_STAR1)
					{
						s_score += (int)(s_level/5);
					}
				}
				Random rd = new Random();
				int dy = rd.nextInt(Define.START_Y_DELTA);
				dis[i].setY(Define.BASE_START_Y + dy + Define.START_Y_DELTA*i);
				dis[i].setX(Define.BASE_START_X);
				float da = rd.nextFloat()*Define.ANGLE_DELTA;
				dis[i].setAngle(Define.BASE_ANGLE + da);
				float ds = rd.nextFloat()*Define.SPEED_DELTA;
				dis[i].setSpeed(Define.BASE_SPEED + ds + Define.SPEED_DELTA*i);
				dis[i].reset();
				dis[i].start();
			}
			else if(dis[i].isBurn())
			{
				snd_player.playSFX(DATA.SOUND_SFX_DIS_BURN);
			}
		}
		s_rate = (float)myDis.getScore()/myDis.getCount();
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
		{
			dis[i].update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		}				
		levelUp();
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.BLUE);
		s_gameSprites[DATA.SPR_GROUND].draw(s_canvas);
		s_gameSprites[DATA.SPR_BGR_1 + s_level - 1].draw(s_canvas);
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
			dis[i].draw(s_canvas);
		paint_HUD();
		s_gameSprites[DATA.SPR_GUN].draw(s_canvas, (int)(s_touch.getX() - Define.GUN_OFF_X), 0);
		btn_gamePause.draw(s_canvas);
	}
	else if(state == State.EXIT)
	{				
		s_game_state = s_game_next_state;
	}
}

void paint_HUD()
{
	Paint myPaint = new Paint();
	myPaint.setColor(Color.GREEN);
	s_canvas.drawRect(0, 0, SCREEN_WIDTH, Define.HUD_HEIGHT, myPaint);
	Untils.drawString(s_canvas, "HP:", Define.HUD_HP_X, Define.HUD_HP_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
	if(s_blood >= 0)
		drawLoandingBar(s_blood, Define.BLOOD_MAX, Define.HUD_HP_BAR_X, Define.HUD_HP_Y - Define.HUD_HP_H/2, Define.HUD_HP_W, Define.HUD_HP_H, Color.RED, Color.WHITE);
	else
		drawLoandingBar(0, Define.BLOOD_MAX, Define.HUD_HP_BAR_X, Define.HUD_HP_Y - Define.HUD_HP_H/2, Define.HUD_HP_W, Define.HUD_HP_H, Color.RED, Color.WHITE);
	Untils.drawString(s_canvas, "LEVEL: "+s_level+"   SCORE: "+s_score, Define.HUD_LEVEL_X, Define.HUD_LEVEL_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
}

public void s_game_STATE_MAINMENU(int state)
{
	if(state == State.INIT)
	{
		btn_play.setActive(false);
		btn_help.setActive(false);
		btn_about.setActive(false);
		snd_player.playM(DATA.SOUND_M_TITLE);
	}
	else if(state == State.UPDATE)
	{
		btn_play.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_help.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_about.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_music_on.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_sfx_on.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(s_isBackKeyPressed)
		{
			s_dialog.setTitle(DATA.TXT_IGM_EXIT);
			s_dialog.setContent(DATA.TXT_IGM_EXIT_CF);
			s_dialog.setID(dialogID.DIALOG_EXIT);
			s_dialog.setActive(true);
		}
		if(btn_play.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_LOAD;			
			s_load_type = loadType.LOAD_GAMEPLAY;
			s_exit_state = true;
		}
		else if(btn_help.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_HELP;
			s_exit_state = true;
		}
		else if(btn_about.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_ABOUT;
			s_exit_state = true;
		}
		else if(btn_music_on.getActive())
		{
			music_enable = !music_enable;
			if(!music_enable)
				snd_player.pauseAll();
			snd_player.setM_enable(music_enable);
			snd_player.playM(DATA.SOUND_M_TITLE);
		}
		else if(btn_sfx_on.getActive())
		{
			sfx_enable = !sfx_enable;
			snd_player.setSfx_enable(sfx_enable);
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		s_gameSprites[DATA.SPR_SPLASH].draw(s_canvas);
		btn_play.draw(s_canvas);
		btn_help.draw(s_canvas);
		btn_about.draw(s_canvas);
		if(music_enable)
			btn_music_on.draw(s_canvas);
		else
			btn_music_off.draw(s_canvas);
		if(sfx_enable)
			btn_sfx_on.draw(s_canvas);
		else
			btn_sfx_off.draw(s_canvas);
	}
	else if(state == State.EXIT)
	{
		s_game_state = s_game_next_state;
	}
}

public void s_game_STATE_HELP(int state)
{
	if(state == State.INIT)
	{
		btn_close.setActive(false);
	}
	else if(state == State.UPDATE)
	{
		btn_close.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_close.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_exit_state = true;
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		btn_close.draw(s_canvas);
		Untils.drawPage(s_canvas, Untils.wrapText(DATA.TXT_HELP, SCREEN_WIDTH - 40, Define.TEXT_SIZE_SMALL), SCREEN_WIDTH/2, SCREEN_HEIGHT/2, Color.BLUE, Define.TEXT_SIZE_SMALL, 5, Align.CENTER);
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_MAINMENU;
	}
}

public void s_game_STATE_ABOUT(int state)
{
	if(state == State.INIT)
	{
		btn_close.setActive(false);
	}
	else if(state == State.UPDATE)
	{
		btn_close.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_close.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_exit_state = true;
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		btn_close.draw(s_canvas);
		Untils.drawPage(s_canvas, Untils.wrapText(DATA.TXT_ABOUT, SCREEN_WIDTH - 40, Define.TEXT_SIZE_SMALL), SCREEN_WIDTH/2, SCREEN_HEIGHT/2, Color.BLUE, Define.TEXT_SIZE_SMALL, 5, Align.CENTER);
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_MAINMENU;
	}
}

public void s_game_STATE_IGM(int state)
{
	if(state == State.INIT)
	{
		btn_resume.setActive(false);
		btn_replay.setActive(false);
		btn_mm.setActive(false);
		btn_exit.setActive(false);
	}
	else if(state == State.UPDATE)
	{
		btn_resume.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_replay.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_mm.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_exit.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_resume.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_PLAY;
			s_skip_init_state = true;
			s_exit_state = true;
		}
		else if(btn_replay.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_dialog.setTitle(DATA.TXT_IGM_REPLAY);
			s_dialog.setContent(DATA.TXT_IGM_REPLAY_CF);
			s_dialog.setID(dialogID.DIALOG_REPLAY);
			s_dialog.setActive(true);
			btn_replay.setActive(false);
		}
		else if(btn_mm.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_dialog.setTitle(DATA.TXT_IGM_MM);
			s_dialog.setContent(DATA.TXT_IGM_MM_CF);
			s_dialog.setID(dialogID.DIALOG_MM);
			s_dialog.setActive(true);
			btn_mm.setActive(false);
		}
		else if(btn_exit.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_dialog.setTitle(DATA.TXT_IGM_EXIT);
			s_dialog.setContent(DATA.TXT_IGM_EXIT_CF);
			s_dialog.setID(dialogID.DIALOG_EXIT);
			s_dialog.setActive(true);
			btn_exit.setActive(false);
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		Untils.drawString(s_canvas, DATA.TXT_IGM_TITLE, SCREEN_WIDTH/2, Define.IGM_TITLE_Y, Color.WHITE, Define.TEXT_SIZE_LARGE, Align.CENTER);
		btn_resume.draw(s_canvas);
		btn_replay.draw(s_canvas);
		btn_mm.draw(s_canvas);
		btn_exit.draw(s_canvas);
	}
	else if(state == State.EXIT)
	{
		s_game_state = s_game_next_state;
	}
}

public void s_game_STATE_RESULT(int state)
{
	if(state == State.INIT)
	{
		btn_replay.setActive(false);
		btn_mm.setActive(false);
		btn_exit.setActive(false);
		if(s_highscore < s_score)
		{
			s_highscore = s_score;
			SharedPreferences.Editor editor = s_ref.edit();
			editor.putInt("s_highscore", s_highscore);
			editor.commit();
		}
	}
	else if(state == State.UPDATE)
	{
		btn_replay.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_mm.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		btn_exit.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_replay.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_PLAY;
			s_exit_state = true;
		}
		else if(btn_mm.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_LOAD;
			s_load_type = loadType.LOAD_MAINMENU;
			s_exit_state = true;
		}
		else if(btn_exit.getActive())
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_dialog.setTitle(DATA.TXT_IGM_EXIT);
			s_dialog.setContent(DATA.TXT_IGM_EXIT_CF);
			s_dialog.setID(dialogID.DIALOG_EXIT);
			s_dialog.setActive(true);
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		Untils.drawString(s_canvas, DATA.TXT_RESULT, SCREEN_WIDTH/2, Define.RESULT_TITLE_Y, Color.WHITE, Define.TEXT_SIZE_LARGE, Align.CENTER);
		Untils.drawString(s_canvas, "RATE:  ", SCREEN_WIDTH/2, Define.RESULT_RATE_Y, Color.WHITE, Define.TEXT_SIZE_LARGE, Align.RIGHT);
		if(s_rate >= Define.NUM_STAR3)
		{
			s_gameSprites[DATA.SPR_STAR3].draw(s_canvas);
		}
		else if(s_rate >= Define.NUM_STAR2)
		{
			s_gameSprites[DATA.SPR_STAR2].draw(s_canvas);
		}
		else if(s_rate >= Define.NUM_STAR1)
		{
			s_gameSprites[DATA.SPR_STAR1].draw(s_canvas);
		}
		else
		{
			s_gameSprites[DATA.SPR_STAR0].draw(s_canvas);
		}
		Untils.drawString(s_canvas, "HIGH SCORE: "+s_highscore, SCREEN_WIDTH/2, Define.RESULT_HIGHSCORE_Y, Color.RED, Define.TEXT_SIZE_LARGE, Align.CENTER);
		Untils.drawString(s_canvas, "SCORE:      "+s_score, SCREEN_WIDTH/2, Define.RESULT_SCORE_Y, Color.WHITE, Define.TEXT_SIZE_LARGE, Align.CENTER);
		btn_replay.draw(s_canvas);
		btn_mm.draw(s_canvas);
		btn_exit.draw(s_canvas);
	}
	else if(state == State.EXIT)
	{
		s_game_state = s_game_next_state;
	}
}


	}
}
