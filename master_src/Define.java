package Main.Ufo;


public interface Define {

	final static int SCREEN_WIDTH = 2048;
	final static int SCREEN_HEIGHT = 2732;
	
	final static int GAMEPLAY_X = 50;
	final static int GAMEPLAY_Y = 50;
	final static int GAMEPLAY_W = SCREEN_WIDTH - (GAMEPLAY_X<<1);
	final static int GAMEPLAY_H = SCREEN_HEIGHT - (GAMEPLAY_Y<<1);
	final static int BALL_NUM = 4;
	final static int BALL_SPEED_Y = -3;
	final static int BALL_SPEED_DELTA_Y = 2;
	final static int BALL_SPEED_DELTA_X = 2;
	final static int BALL_START_X = GAMEPLAY_X;
	final static int BALL_START_DELTA_X = (SCREEN_WIDTH - (BALL_START_X<<1))/BALL_NUM;
	final static int BALL_START_OFF_X = BALL_START_DELTA_X>>1;
	final static int BALL_START_Y = SCREEN_HEIGHT + 2;
	final static int GUN_X = (SCREEN_WIDTH>>1) - 572;
	final static int GUN_Y = SCREEN_HEIGHT - 1015;
	final static int BULLET_X = SCREEN_WIDTH>>1;
	final static int BULLET_Y = SCREEN_HEIGHT + 2;
	final static int BULLET_SPEED = -40;
	
	final static int LOGO_FRAME = 100;
	
	final static int BTN_PLAY_Y = 10;
	final static int BTN_PLAY_X = 10;
	final static int BTN_HELP_Y = 250;
	final static int BTN_HELP_X = SCREEN_WIDTH - 10;
	final static int BTN_ABOUT_Y = 470;
	final static int BTN_ABOUT_X = 10;;
	final static int BTN_PAUSE_X = SCREEN_WIDTH;
	final static int BTN_CLOSE_X = SCREEN_WIDTH;
	
	final static int BTN_MUSIC_Y = 10;
	final static int BTN_MUSIC_X = SCREEN_WIDTH - 10;
	final static int BTN_SFX_X = SCREEN_WIDTH - 10 - 50;
	
	final static int TEXT_SIZE_SMALL = SCREEN_HEIGHT>>4;
	final static int TEXT_SIZE_MED = TEXT_SIZE_SMALL + SCREEN_HEIGHT>>5;
	final static int TEXT_SIZE_LARGE = TEXT_SIZE_MED + SCREEN_HEIGHT>>5;
	final static int TEXT_SIZE_VERY_LARGE = TEXT_SIZE_LARGE + SCREEN_HEIGHT>>5;
	
	
	final static int HUD_HP_Y = TEXT_SIZE_MED/2 + 4;
	final static int HUD_HP_X = 6;
	final static int HUD_HP_BAR_X = HUD_HP_X + 30;
	final static int HUD_HP_W = 200;
	final static int HUD_HP_H = TEXT_SIZE_MED;
	final static int HUD_LEVEL_Y = HUD_HP_Y + HUD_HP_H + 4;
	final static int HUD_LEVEL_X = 6;
	final static int HUD_HEIGHT = 82;
	
	final static int ANSWER_X = 40;
	final static int ANSWER_Y = 40;
	final static int ANSWER_OFF_X = 80;
	
	final static int IGM_TITLE_Y = 60;
	final static int IGM_BTN_Y = 170;
	final static int IGM_BTN_H = 120;
	
	final static int RESULT_TITLE_Y = 40;
	final static int RESULT_RATE_Y = RESULT_TITLE_Y + TEXT_SIZE_LARGE + 50;
	final static int RESULT_HIGHSCORE_Y = RESULT_RATE_Y + TEXT_SIZE_LARGE + 50;
	final static int RESULT_SCORE_Y = RESULT_HIGHSCORE_Y + TEXT_SIZE_LARGE + 5;
	final static int RESULT_RATE_ICON_Y = RESULT_TITLE_Y + TEXT_SIZE_LARGE + 12;
	final static int RESULT_RATE_ICON_X = SCREEN_WIDTH/2 + 20;
}