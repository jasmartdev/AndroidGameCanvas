public void s_game_STATE_PLAY(int state)
{
	if(state == State.INIT)
	{
		btn_gamePause.setActive(false);
		s_level = 1;
		s_score = 0;
		genQuestion();
		genBalloons();
		resetBullet();
		startBalloons();
		mMovieStart = 0;
	}
	else if(state == State.UPDATE)
	{
		if(s_touch.getTouch())
			startBullet();
		if(s_bullet.isDestroy())
			resetBullet();
		btn_gamePause.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_gamePause.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_IGM;
			s_exit_state = true;
		}
		updateBalloons();
		s_bullet.update();
	}
	else if(state == State.PAINT)
	{
		s_gameSprites[DATA.SPR_BGR].draw(s_canvas);
		drawBalloons();
		s_bullet.draw(s_canvas);
		paint_HUD();
		btn_gamePause.draw(s_canvas);
		long now = android.os.SystemClock.uptimeMillis();
		if (mMovieStart == 0) {
			mMovieStart = now;
		}
		if (s_gun != null) {
			int dur = s_gun.duration();
			Untils.Dbg("s_gun dur:"+dur);
			if (dur == 0) {
				dur = 1000;
			}
			int relTime = (int) ((now - mMovieStart) % dur);
			Untils.Dbg("s_gun relTime:"+relTime);
			s_gun.setTime(relTime);
			s_gun.draw(s_canvas, Define.GUN_X, Define.GUN_Y);
		}
		Untils.drawRect(s_canvas, s_gameplay_rect);
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
		if(s_answer_map[i])
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.ANSWER_X + Define.ANSWER_OFF_X*i, Define.ANSWER_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
		else
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.ANSWER_X + Define.ANSWER_OFF_X*i, Define.ANSWER_Y, Color.WHITE, Define.TEXT_SIZE_MED, Align.LEFT);
	}
}

void genQuestion()
{
	s_answer = new char[] {'H', 'O', 'A', 'N', 'G'};
	s_answer_map = new boolean[s_answer.length];
	Arrays.fill(s_answer_map, false);
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
	int num = Define.BALL_NUM;
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
		balloons[--num] = new balloonObject(s_gameSprites[DATA.SPR_BALLOON1 + r.nextInt(5)], s_gameSprites[DATA.SPR_BALLOON_BURN], c, Define.BALL_START_X + Define.BALL_START_DELTA_X*num + r.nextInt(Define.BALL_START_OFF_X), Define.BALL_START_Y, r.nextInt(Define.BALL_SPEED_DELTA_X + 1) - (Define.BALL_SPEED_DELTA_X>>1), Define.BALL_SPEED_Y + r.nextInt(Define.BALL_SPEED_DELTA_Y));
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
	{
		if(s_bullet.isFly() && s_bullet.getRect().intersect(b.getRect()))
		{
			s_bullet.burn();
			b.burn();
			char c = b.getChar();
			for(int i = 0; i < s_answer.length; i++)
			{
				if(c == s_answer[i] && !s_answer_map[i])
				{
					s_answer_collected[i] = c;
					s_answer_map[i] = true;
				}
			}
		}
		if(b.getRect().bottom < 0)
			b.destroy();
		if(b.getRect().left < s_gameplay_rect.left || b.getRect().right > s_gameplay_rect.right)
			b.setVx(-b.getVx());
		for(balloonObject b2: balloons)
		{
			if(!b2.equals(b))
			{
				if(b2.getRect().intersect(b.getRect()))
				{
					b2.setVx(-b2.getVx());
					b.setVx(-b.getVx());
				}
				b2.update();
			}
		}
		b.update();
	}
}
void resetBullet()
{
	s_bullet.reset();
	s_bullet.setX(Define.BULLET_X);
	s_bullet.setY(Define.BULLET_Y);
}
void startBullet()
{
	s_bullet.start();
	s_bullet.setX(Define.BULLET_X);
	s_bullet.setY(Define.BULLET_Y);
	s_bullet.setVx(-1);
	s_bullet.setVy(-20);
}