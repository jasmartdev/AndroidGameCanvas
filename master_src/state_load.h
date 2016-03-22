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
						#ifndef USE_SPLASH_SCREEN
						if(s_gameSprites[DATA.SPR_SPLASH] == null)
						{
							s_gameSprites[DATA.SPR_SPLASH] = new mySprites(R.drawable.splash, 0, 0);
							s_gameSprites[DATA.SPR_SPLASH].Load(s_mainActive.getApplicationContext());
							s_gameSprites[DATA.SPR_SPLASH].Scale(SCALE_WIDTH, SCALE_HEIGHT);
						}
						#endif
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