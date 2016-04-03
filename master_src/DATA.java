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
	final static int SPR_GUN_FIRST = SPR_BTN_PAUSE_DOWN + 1;
	final static int SPR_GUN_LAST = SPR_GUN_FIRST + 16;
	final static int SPR_GUN_FIRE_FIRST = SPR_GUN_LAST + 1;
	final static int SPR_GUN_FIRE_LAST = SPR_GUN_FIRE_FIRST + 16;
	final static int SPR_BALLOON1 = SPR_GUN_FIRE_LAST + 1;
	final static int SPR_BALLOON2 = SPR_BALLOON1 + 1;
	final static int SPR_BALLOON3 = SPR_BALLOON2 + 1;
	final static int SPR_BALLOON4 = SPR_BALLOON3 + 1;
	final static int SPR_BALLOON5 = SPR_BALLOON4 + 1;
	final static int SPR_BALLOON_BURN = SPR_BALLOON5 + 1;
	final static int SPR_BULLET = SPR_BALLOON_BURN + 1;
	final static int SPR_BULLET_BURN = SPR_BULLET + 1;
	final static int SPR_MAX = SPR_BULLET_BURN + 1;
	
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
	final static int gunID[] = {
		R.drawable.gun_1,
		R.drawable.gun_2,
		R.drawable.gun_3,
		R.drawable.gun_4,
		R.drawable.gun_5,
		R.drawable.gun_6,
		R.drawable.gun_7,
		R.drawable.gun_8,
		R.drawable.gun_9,
		R.drawable.gun_10,
		R.drawable.gun_11,
		R.drawable.gun_12,
		R.drawable.gun_13,
		R.drawable.gun_14,
		R.drawable.gun_15,
		R.drawable.gun_16,
		R.drawable.gun_17,
	};
	final static int gunfireID[] = {
		R.drawable.gun_1a,
		R.drawable.gun_2a,
		R.drawable.gun_3a,
		R.drawable.gun_4a,
		R.drawable.gun_5a,
		R.drawable.gun_6a,
		R.drawable.gun_7a,
		R.drawable.gun_8a,
		R.drawable.gun_9a,
		R.drawable.gun_10a,
		R.drawable.gun_11a,
		R.drawable.gun_12a,
		R.drawable.gun_13a,
		R.drawable.gun_14a,
		R.drawable.gun_15a,
		R.drawable.gun_16a,
		R.drawable.gun_17a,
	};
	final static int bulletStartX[] = {
		Define.BULLET_START_X - 160,
		Define.BULLET_START_X - 140,
		Define.BULLET_START_X - 120,
		Define.BULLET_START_X - 100,
		Define.BULLET_START_X - 80,
		Define.BULLET_START_X - 60,
		Define.BULLET_START_X - 40,
		Define.BULLET_START_X - 20,
		Define.BULLET_START_X,
		Define.BULLET_START_X + 20,
		Define.BULLET_START_X + 40,
		Define.BULLET_START_X + 60,
		Define.BULLET_START_X + 80,
		Define.BULLET_START_X + 100,
		Define.BULLET_START_X + 120,
		Define.BULLET_START_X + 140,
		Define.BULLET_START_X + 160,
	};
	final static int bulletStartY[] = {
		Define.BULLET_START_Y - 160,
		Define.BULLET_START_Y - 140,
		Define.BULLET_START_Y - 120,
		Define.BULLET_START_Y - 100,
		Define.BULLET_START_Y - 80,
		Define.BULLET_START_Y - 60,
		Define.BULLET_START_Y - 40,
		Define.BULLET_START_Y - 20,
		Define.BULLET_START_Y,
		Define.BULLET_START_Y + 20,
		Define.BULLET_START_Y + 40,
		Define.BULLET_START_Y + 60,
		Define.BULLET_START_Y + 80,
		Define.BULLET_START_Y + 100,
		Define.BULLET_START_Y + 120,
		Define.BULLET_START_Y + 140,
		Define.BULLET_START_Y + 160,
	};
	final static int bulletSpeedX[] = {
		-8*Define.BULLET_SPEED_RO,
		-7*Define.BULLET_SPEED_RO,
		-6*Define.BULLET_SPEED_RO,
		-5*Define.BULLET_SPEED_RO,
		-4*Define.BULLET_SPEED_RO,
		-3*Define.BULLET_SPEED_RO,
		-2*Define.BULLET_SPEED_RO,
		-1*Define.BULLET_SPEED_RO,
		0,
		1*Define.BULLET_SPEED_RO,
		2*Define.BULLET_SPEED_RO,
		3*Define.BULLET_SPEED_RO,
		4*Define.BULLET_SPEED_RO,
		5*Define.BULLET_SPEED_RO,
		6*Define.BULLET_SPEED_RO,
		7*Define.BULLET_SPEED_RO,
		8*Define.BULLET_SPEED_RO,
	};
	final static int bulletSpeedY[] = {
		Define.BULLET_SPEED+8*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+7*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+6*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+5*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+4*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+3*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+2*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+1*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED,
		Define.BULLET_SPEED+1*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+2*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+3*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+4*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+5*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+6*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+7*Define.BULLET_SPEED_RO,
		Define.BULLET_SPEED+8*Define.BULLET_SPEED_RO,
	};
}