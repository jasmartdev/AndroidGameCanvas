public void s_game_STATE_PLAY(int state)
{
	if(state == State.INIT)
	{
		btn_gamePause.setActive(false);
		s_score = 0;
		genQuestion();
		genBalloons();
		resetBullet();
		startBalloons();
		s_gun_angle = Define.GUN_ANGLE_CENTER;
		s_gun_fire = 0;
	}
	else if(state == State.UPDATE)
	{
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
		if(s_question_finish)
		{
			genQuestion();
			genBalloons();
			resetBullet();
			startBalloons();
		}
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
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.ANSWER_X + Define.ANSWER_OFF_X*i, Define.ANSWER_Y, Color.BLUE, Define.TEXT_SIZE_MED, Align.LEFT);
		else
			Untils.drawString(s_canvas, String.valueOf(s_answer[i]), Define.ANSWER_X + Define.ANSWER_OFF_X*i, Define.ANSWER_Y, Color.WHITE, Define.TEXT_SIZE_MED, Align.LEFT);
		Untils.drawString(s_canvas, "SCORE: "+s_score, SCREEN_WIDTH>>1, Define.ANSWER_Y, Color.BLUE, Define.TEXT_SIZE_MED, Align.LEFT);
		Untils.drawPage(s_canvas, Untils.wrapText(s_question, SCREEN_WIDTH - 40, Define.TEXT_SIZE_SMALL), SCREEN_WIDTH/2, 100, Color.BLUE, Define.TEXT_SIZE_SMALL, 5, Align.TOP | Align.HCENTER);
	}
}

void genQuestion()
{
	Random r = new Random();
	int i = r.nextInt(s_questions.length() - 1) + 1;
	try {
		s_question = s_questions.getJSONObject(i).getString("title");
		String ans = s_questions.getJSONObject(i).getString("answer");
		s_answer = ans.toCharArray();
	} catch (Exception e) {
		Untils.Dbg("Unhandled exception while genQuestion JSONObject"+e);
		e.printStackTrace();
	}
	s_answer_map = new boolean[s_answer.length];
	Arrays.fill(s_answer_map, false);
	s_answer_collected = new char[s_answer.length];
	Arrays.fill(s_answer_collected, ' ');
	s_question_finish = false;
}

void genBalloons()
{
	List<Character> answer = new ArrayList<Character>();
	for(int i = 0; i < s_answer.length; i++)
	{
		if(!s_answer_map[i])
			answer.add(s_answer[i]);
	}
	boolean isInAns = false;
	int num = Define.BALL_NUM;
	balloons = new balloonObject[num];
	List<Character> genballs = new ArrayList<Character>();
	while(genballs.size() < num - 1)
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
	while(!isInAns)
	{
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'A');
		if(answer.contains(c))
		{
			genballs.add(r.nextInt(Define.BALL_NUM - 1), c);
			isInAns = true;
		}
	}
	for(char c: genballs)
	{
		Random r = new Random();
		balloons[--num] = new balloonObject(s_gameSprites[DATA.SPR_BALLOON1 + r.nextInt(5)], s_gameSprites[DATA.SPR_BALLOON_BURN], c, Define.BALL_START_X + Define.BALL_START_DELTA_X*num + r.nextInt(Define.BALL_START_OFF_X), Define.BALL_START_Y + r.nextInt(Define.BALL_START_OFF_Y), r.nextInt(Define.BALL_SPEED_DELTA_X + 1) - (Define.BALL_SPEED_DELTA_X>>1), Define.BALL_SPEED_Y + r.nextInt(Define.BALL_SPEED_DELTA_Y));
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
			b.updateRect();
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
			s_bullet.updateRect();
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
					s_score += 8;
				}
				boolean finish = true;
				if(!s_answer_map[i])
					finish = false;
				s_question_finish = finish;
			}
			s_score -= 3;
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
				b2.updateRect();
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
	s_gun_fire = 0;
}
void updateGun()
{
	if(s_touch.getDrag())
	{
		if(s_touch.getDragDeltaX() > 0)
		{
			if(s_gun_angle < Define.GUN_ANGLE_MAX)
				s_gun_angle++;
		}
		else
		{
			if(s_gun_angle > 0)
				s_gun_angle--;
		}
	}
	Rect r = new Rect(Define.GUN_RECT_X, Define.GUN_RECT_Y, Define.GUN_RECT_W, Define.GUN_RECT_H);
	if(s_bullet.isFly() && !s_gameplay_rect.contains(s_bullet.getRect()))
	{
		s_bullet.destroy();
	}
	else if(!s_bullet.isFly() && s_touch.getTouch() && r.contains(s_touch.getX(), s_touch.getY()))
	{
		startBullet();
		s_gun = new mySprites(DATA.gunfireID[s_gun_angle], Define.GUN_X, Define.GUN_Y);
		s_gun.Load(s_mainActive.getApplicationContext());
		s_gun_fire = 6;
	}
	if(s_gun_fire > 0)
		s_gun_fire--;
}
void drawGun()
{
	if(s_gun_fire == 0)
	{
		s_gun = new mySprites(DATA.gunID[s_gun_angle], Define.GUN_X, Define.GUN_Y);
		s_gun.Load(s_mainActive.getApplicationContext());
	}
	s_gun.draw(s_canvas);
	float startX = (float)DATA.bulletStartX[s_gun_angle];
	float startY = (float)DATA.bulletStartY[s_gun_angle];
	float deltaX;
	float deltaY;
	float endX;
	float endY;
	Untils.Dbg("s_gun_angle:"+s_gun_angle);
	if(s_gun_angle > Define.GUN_ANGLE_CENTER)
	{
		deltaX = Define.SCREEN_WIDTH - Define.GAMEPLAY_X - startX;
		deltaY = (deltaX*DATA.bulletSpeedY[s_gun_angle]/DATA.bulletSpeedX[s_gun_angle]);
		endY = startY + deltaY;
		// Untils.Dbg("111 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY);
		if(endY < Define.GAMEPLAY_Y)
		{
			endY = Define.GAMEPLAY_Y;
			deltaX = (deltaY*DATA.bulletSpeedX[s_gun_angle]/DATA.bulletSpeedY[s_gun_angle]);
			endX = startX + deltaX;
			// Untils.Dbg("222 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY+" endX:"+endX);
		}
		else
		{
			endX = Define.SCREEN_WIDTH - Define.GAMEPLAY_X;
			// Untils.Dbg("333 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY+" endX:"+endX);
		}
	}
	else if(s_gun_angle < Define.GUN_ANGLE_CENTER)
	{
		deltaX = startX - Define.GAMEPLAY_X;
		deltaY = (deltaX*DATA.bulletSpeedY[s_gun_angle]/DATA.bulletSpeedX[s_gun_angle]);
		endY = startY - deltaY;
		// Untils.Dbg("444 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY);
		if(endY < Define.GAMEPLAY_Y)
		{
			endY = Define.GAMEPLAY_Y;
			deltaX = (deltaY*DATA.bulletSpeedX[s_gun_angle]/DATA.bulletSpeedY[s_gun_angle]);
			endX = startX + deltaX;
			// Untils.Dbg("555 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY+" endX:"+endX);
		}
		else
		{
			endX = Define.GAMEPLAY_X;
			// Untils.Dbg("666 deltaX:"+deltaX+" deltaY:"+deltaY+" endY:"+endY+" endX:"+endX);
		}
	}
	else
	{
		endX = startX + 1;
		endY = Define.GAMEPLAY_Y;
		// Untils.Dbg("777 endY:"+endY+" endX:"+endX);
	}
	Paint myPaint = new Paint();
	myPaint.setColor(Color.RED);
	myPaint.setStyle(Paint.Style.FILL);
	myPaint.setStrokeWidth(6);
	s_canvas.drawLine(startX, startY, endX, endY, myPaint);
	Untils.drawRect(s_canvas, new Rect(Define.GUN_RECT_X, Define.GUN_RECT_Y, Define.GUN_RECT_W, Define.GUN_RECT_H));
}