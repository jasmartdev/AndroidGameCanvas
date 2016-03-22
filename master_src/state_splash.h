public void s_game_STATE_SPLASH(int state)
{
	if(state == State.INIT)
	{
		#ifdef USE_SPLASH_SCREEN
		s_gameSprites[DATA.SPR_SPLASH] = new mySprites(R.drawable.splash, 0, 0);
		s_gameSprites[DATA.SPR_SPLASH].Load(s_mainActive.getApplicationContext());
		s_gameSprites[DATA.SPR_SPLASH].Scale(SCALE_WIDTH, SCALE_HEIGHT);
		#endif
	}
	else if(state == State.UPDATE)
	{
		if(s_touch.getTouch())
			s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.CYAN);
		s_gameSprites[DATA.SPR_SPLASH].draw(s_canvas);
		Untils.drawString(s_canvas, DATA.TXT_TOUCH_TO_CONTINUE, SCREEN_WIDTH/2, SCREEN_HEIGHT/2, Color.BLUE, 20, Align.CENTER);
		Untils.Dbg_scr(s_canvas, "W:"+SCREEN_WIDTH+" H:"+SCREEN_HEIGHT, 0, 10);
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_LOAD;
		s_load_type = loadType.LOAD_SYSTEM;
	}
}