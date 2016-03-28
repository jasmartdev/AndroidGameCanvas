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