public void s_game_STATE_ABOUT(int state)
{
	if(state == State.INIT)
	{
		btn_close.setActive(false);
	}
	else if(state == State.UPDATE)
	{
		btn_close.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_close.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_exit_state = true;
		}
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		btn_close.draw(s_canvas);
		Untils.drawPage(s_canvas, Untils.wrapText(DATA.TXT_ABOUT, SCREEN_WIDTH - 40, Define.TEXT_SIZE_SMALL), SCREEN_WIDTH/2, SCREEN_HEIGHT/2, Color.BLUE, Define.TEXT_SIZE_SMALL, 5, Align.CENTER);
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_MAINMENU;
	}
}