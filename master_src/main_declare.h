MySurfaceView mySurfaceView;
private static MainActivity s_mainActive;
#ifdef USE_BG_BUFFER
final static float SCREEN_WIDTH = 480;
final static float SCREEN_HEIGHT = 800;
public static float SCREEN_WIDTH_PHONE;
public static float SCREEN_HEIGHT_PHONE;
#else
final static float SCREEN_WIDTH_ORIGINAL = 480;
final static float SCREEN_HEIGHT_ORIGINAL = 800;
public static float SCREEN_WIDTH;
public static float SCREEN_HEIGHT;
#endif
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