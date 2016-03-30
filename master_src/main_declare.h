MySurfaceView mySurfaceView;
private static MainActivity s_mainActive;
#ifdef USE_BG_BUFFER
final static int SCREEN_WIDTH = 2048;
final static int SCREEN_HEIGHT = 2732;
public static int SCREEN_WIDTH_PHONE;
public static int SCREEN_HEIGHT_PHONE;
#else
final static int SCREEN_WIDTH_ORIGINAL = 480;
final static int SCREEN_HEIGHT_ORIGINAL = 800;
public static int SCREEN_WIDTH;
public static int SCREEN_HEIGHT;
#endif
public static float SCALE_WIDTH;
public static float SCALE_HEIGHT;
public boolean s_isBackKeyPressed = false;
public final String refName = "gamofun2014_dis";
public static long s_frameTicker = 0;
public static int s_frameDelta = 0;


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