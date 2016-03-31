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
		s_gun_angle = 9;
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
		updateGun();
		s_bullet.update(s_frameDelta);
	}
	else if(state == State.PAINT)
	{
		s_gameSprites[DATA.SPR_SPLASH].draw(s_canvas);
		paint_HUD();
		drawGun();
		btn_gamePause.draw(s_canvas);
		drawBalloons();
		s_bullet.draw(s_canvas);
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
	boolean endrow = true;
	for(balloonObject b: balloons)
	{
		if(b.isDestroy())
		{
			b.setY(Define.BALL_START_Y);
			continue;
		}
		endrow = false;
		if(b.getRect().bottom < 0)
		{
			b.destroy();
			continue;
		}
		if(s_bullet.isDestroy())
		{
			s_bullet.setY(Define.BULLET_Y);
		}
		else if(s_bullet.isFly() && s_bullet.getRect().intersect(b.getRect()))
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
		if(b.getRect().left < s_gameplay_rect.left || b.getRect().right > s_gameplay_rect.right)
		{
			b.setVx(-b.getVx());
		}
		for(balloonObject b2: balloons)
		{
			if(b2.isDestroy())
			{
				b2.setY(Define.BALL_START_Y);
				continue;
			}
			if(b2.getRect().bottom < 0)
			{
				b2.destroy();
				continue;
			}
			if(!b2.equals(b))
			{
				if(b2.getRect().intersect(b.getRect()))
				{
					b2.setVx(-b2.getVx());
					b.setVx(-b.getVx());
				}
				b2.update(s_frameDelta);
			}
		}
		b.update(s_frameDelta);
	}
	if(endrow)
	{
		genBalloons();
		startBalloons();
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
	s_bullet.setX(DATA.bulletStartX[s_gun_angle]);
	s_bullet.setY(DATA.bulletStartY[s_gun_angle]);
	s_bullet.setVx(DATA.bulletSpeedX[s_gun_angle]);
	s_bullet.setVy(DATA.bulletSpeedY[s_gun_angle]);
}
void updateGun()
{
	if(s_touch.getDrag())
	{
		if(s_touch.getDragDeltaX() > 0)
		{
			if(s_gun_angle < 16)
				s_gun_angle++;
		}
		else
		{
			if(s_gun_angle > 0)
				s_gun_angle--;
		}
	}
}
void drawGun()
{
	mySprites gun = new mySprites(DATA.gunID[s_gun_angle], Define.GUN_X, Define.GUN_Y);
	gun.Load(s_mainActive.getApplicationContext());
	gun.draw(s_canvas);
}