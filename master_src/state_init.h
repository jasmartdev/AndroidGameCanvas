public void s_game_STATE_INIT(int state)
{
	if(state == State.INIT)
	{
		s_gameSprites = new mySprites[DATA.SPR_MAX];
		s_ref = getSharedPreferences(refName, 0);
	}
	else if(state == State.UPDATE)
	{
		s_exit_state = true;
	}
	else if(state == State.PAINT)
	{
	}
	else if(state == State.EXIT)
	{
		s_game_state = gameState.STATE_LOGO;
	}
}