public void s_game_STATE_LOGO(int state)
{
	if(state == State.INIT)
	{				
		s_gameSprites[DATA.SPR_LOGO] = new mySprites(R.drawable.logo, SCREEN_WIDTH/2 - 120, SCREEN_HEIGHT/2 - 120);
		s_gameSprites[DATA.SPR_LOGO].Load(s_mainActive.getApplicationContext());
	}
	else if(state == State.UPDATE)
	{
		if(s_frameCount > Define.LOGO_FRAME)
			s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.rgb(74, 246, 14));
		s_canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, myPaint);
		s_gameSprites[DATA.SPR_LOGO].draw(s_canvas);
	}
	else if(state == State.EXIT)
	{
		#ifdef USE_SPLASH_SCREEN
		s_game_state = gameState.STATE_SPLASH;
		#else
		s_game_state = gameState.STATE_LOAD;
		s_load_type = loadType.LOAD_SYSTEM;
		#endif
	}
}