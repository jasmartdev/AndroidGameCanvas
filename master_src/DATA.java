package Main.Ufo;


public interface DATA {

//define for sprite
	final static int SPR_SPLASH = 0;
	final static int SPR_LOGO = SPR_SPLASH + 1;
	final static int SPR_DIS0 = SPR_LOGO + 1;
	final static int SPR_DIS_BURN = SPR_DIS0 + 1;
	final static int SPR_BTN_PLAY_UP = SPR_DIS_BURN + 1;
	final static int SPR_BTN_PLAY_DOWN = SPR_BTN_PLAY_UP + 1;
	final static int SPR_BTN_PAUSE_UP = SPR_BTN_PLAY_DOWN + 1;
	final static int SPR_BTN_PAUSE_DOWN = SPR_BTN_PAUSE_UP + 1;
	final static int SPR_BGR_1 = SPR_BTN_PAUSE_DOWN + 1;
	final static int SPR_BGR_2 = SPR_BGR_1 + 1;
	final static int SPR_BGR_3 = SPR_BGR_2 + 1;
	final static int SPR_BGR_4 = SPR_BGR_3 + 1;
	final static int SPR_BGR_5 = SPR_BGR_4 + 1;
	final static int SPR_GROUND = SPR_BGR_5 + 1;
	final static int SPR_STAR0 = SPR_GROUND + 1;
	final static int SPR_STAR1 = SPR_STAR0 + 1;
	final static int SPR_STAR2 = SPR_STAR1 + 1;
	final static int SPR_STAR3 = SPR_STAR2 + 1;
	final static int SPR_GUN = SPR_STAR3 + 1;
	final static int SPR_GUN_FIRE = SPR_GUN + 1;
	final static int SPR_MAX = SPR_GUN_FIRE + 1;
	
//define for string
	final String TXT_TOUCH_TO_CONTINUE = "TOUCH ON SCREEN TO CONTINUE";
	final String TXT_BTN_PLAY = "PLAY";
	final String TXT_BTN_HELP = "HELP";
	final String TXT_BTN_ABOUT = "ABOUT";
	final String TXT_HELP = "UNIVERSITY ARMY INVASIVE OUR PLANET. TRY TO FIGHT TO PROTECT EARTH. TOUCH ON THE UFO TO DETROY IT.";
	final String TXT_ABOUT = "GAME IS CREATED BY GAMOFUN2014 GROUP.\nCONTACT: gamemofun2014@gmail.com";
	final String TXT_IGM_TITLE = "IN GAME MENU";
	final String TXT_IGM_RESUME = "RESUME GAME";
	final String TXT_IGM_REPLAY = "REPLAY GAME";
	final String TXT_IGM_MM = "EXIT TO MAIN MENU";
	final String TXT_IGM_EXIT = "EXIT GAME";
	final String TXT_IGM_REPLAY_CF = "Do you want to replay?";
	final String TXT_IGM_MM_CF = "Do you want to return to the main menu?";
	final String TXT_IGM_EXIT_CF = "Do you want to exit the game?";
	final String TXT_YES = "YES";
	final String TXT_NO = "NO";
	final String TXT_RESULT = "END GAME";
	
//define for sound
	final static int SOUND_M_TITLE = 0;
	final static int SOUND_SFX_MENU_CONFIRM = SOUND_M_TITLE + 1;
	final static int SOUND_SFX_DIS_BURN = SOUND_SFX_MENU_CONFIRM + 1;
	final static int SOUND_MAX = SOUND_SFX_DIS_BURN + 1;
	
	final static int soundID[] = {
		R.raw.m_title,
		R.raw.sfx_menu_confirm,
		R.raw.sfx_dis_burn,
	};

}