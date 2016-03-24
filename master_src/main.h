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
	#ifdef USE_RESET_TOUCH
	s_touch.resetTouch();
	#endif
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
	#ifdef USE_BG_BUFFER
	s_canvas_scale.drawBitmap(s_bg_buffer, new Rect(0, 0, (int)SCREEN_WIDTH, (int)SCREEN_HEIGHT), new Rect(0, 0, (int)SCREEN_WIDTH_PHONE, (int)SCREEN_HEIGHT_PHONE), null);
	#endif
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
			#ifdef USE_RESET_TOUCH
			s_touch.resetTouch();
			#endif
		}
		else if(s_dialog.isNo())
		{
			s_dialog.setActive(false);
			#ifdef USE_RESET_TOUCH
			s_touch.resetTouch();
			#endif
		}
	}
}
void drawLoandingBar(int per, int max, int x, int y, int w, int h, int color, int bcolor)
{
	Paint myPaint = new Paint();
	myPaint.setColor(Color.rgb(0, 0, 0));
	myPaint.setColor(bcolor);
	s_canvas.drawRect(x -  1, y - 1, x + w + 1, y + h + 1, myPaint);
	myPaint.setColor(color);
	s_canvas.drawRect(x, y, x + w*per/max, y + h, myPaint);
}
void drawLoandingBar(int per, int max, int x, int y, int w, int h)
{
	drawLoandingBar(per, max, x, y, w, h, Color.RED, Color.WHITE);
}
void drawLoandingBar(int per, int max)
{
	drawLoandingBar(per, max, 20, SCREEN_HEIGHT - 100, SCREEN_WIDTH - 40, 20);
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