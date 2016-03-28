public void s_game_STATE_PLAY(int state)
{
	if(state == State.INIT)
	{
		Untils.Dbg("00000000000000000000000000");
		btn_gamePause.setActive(false);
		s_level = 1;
		s_score = 0;
		genQuestion();
		genBalloons();
		s_blood = Define.BLOOD_MAX;
		// s_bullet.start();
		startBalloons();
	}
	else if(state == State.UPDATE)
	{
		Untils.Dbg("1111111111111111111111");
		btn_gamePause.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_gamePause.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_IGM;
			s_exit_state = true;
		}
		updateBalloons();
	}
	else if(state == State.PAINT)
	{
		Untils.Dbg("2222222222222222222222222");
		s_gameSprites[DATA.SPR_BGR].draw(s_canvas);
		drawBalloons();
		// s_bullet.draw(s_canvas);
		paint_HUD();
		s_gameSprites[DATA.SPR_GUN].draw(s_canvas, (int)(s_touch.getX() - Define.GUN_OFF_X), 0);
		btn_gamePause.draw(s_canvas);
	}
	else if(state == State.EXIT)
	{				
		s_game_state = s_game_next_state;
	}
}

void paint_HUD()
{
	for(int i = 0; i < s_answer.length; i++)
	{
		if(s_answer_map[i] == 1)
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.HUD_HP_X + 20*i, Define.HUD_HP_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
		else
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.HUD_HP_X + 20*i, Define.HUD_HP_Y, Color.WHITE, Define.TEXT_SIZE_MED, Align.LEFT);
	}
}

void genQuestion()
{
	s_answer = new char[] {'H', 'O', 'A', 'N', 'G'};
	s_answer_map = new int[s_answer.length];
	Arrays.fill(s_answer_map, 0);
	s_answer_collected = new char[s_answer.length];
	Arrays.fill(s_answer_collected, ' ');
}

void genBalloons()
{
	List<Character> answer = new ArrayList<Character>();
	for(char c: s_answer)
	{
		answer.add(c);
	}
	for(char c: s_answer_collected)
	{
		if(answer.contains(c))
			answer.remove(c);
	}
	boolean isInAns = false;
	int num = 4;
	balloons = new balloonObject[num];
	List<Character> genballs = new ArrayList<Character>();
	while(!isInAns)
	{
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'A');
		if(answer.contains(c))
		{
			genballs.add(c);
			isInAns = true;
		}
	}
	while(genballs.size() < num)
	{
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'A');
		if(!genballs.contains(c))
		{
			if(!answer.contains(c))
			{
				genballs.add(c);
			}
		}
	}
	for(char c: genballs)
	{
		Random r = new Random();
		balloons[--num] = new balloonObject(s_gameSprites[DATA.SPR_BALLOON], s_gameSprites[DATA.SPR_BALLOON_BURN], c, r.nextInt(SCREEN_WIDTH), Define.BASE_START_Y, 0, -5);
		Untils.Dbg("genBalloons num:"+num+" c:"+c);
	}
}
void startBalloons()
{
	for(balloonObject b: balloons)
		b.start();
}
void drawBalloons()
{
	for(balloonObject b: balloons)
		b.draw(s_canvas);
}
void updateBalloons()
{
	for(balloonObject b: balloons)
		b.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
}