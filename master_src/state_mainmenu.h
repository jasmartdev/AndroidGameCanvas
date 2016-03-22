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