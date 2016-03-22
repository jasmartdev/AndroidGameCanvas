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