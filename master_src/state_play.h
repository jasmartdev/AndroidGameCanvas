public void s_game_STATE_PLAY(int state)
{
	if(state == State.INIT)
	{
		btn_gamePause.setActive(false);
		s_level = 1;
		s_score = 0;
		dis[0].resetScore();
		dis[0].resetCount();
		dis[0].start();
		s_blood = Define.BLOOD_MAX;
		balloons[0].start();
		
	}
	else if(state == State.UPDATE)
	{
		btn_gamePause.update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		if(btn_gamePause.getActive() || s_isBackKeyPressed)
		{
			snd_player.playSFX(DATA.SOUND_SFX_MENU_CONFIRM);
			s_game_next_state = gameState.STATE_IGM;
			s_exit_state = true;
		}
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
		{
			if(dis[i].checkOut(SCREEN_WIDTH, 0) || dis[i].isOut())
			{
				if(dis[i].checkOut(SCREEN_WIDTH, 0))
				{
					s_blood -= Define.BLOOD_PER;
					if(s_blood <= 0)
					{
						s_game_next_state = gameState.STATE_RESULT;
						s_exit_state = true;
					}
				}
				else if(dis[i].isOut())
				{
					s_score += s_level;
					if(s_rate >= Define.NUM_STAR3)
					{
						s_score += (int)(s_level/3);
					}
					else if(s_rate >= Define.NUM_STAR2)
					{
						s_score += (int)(s_level/4);
					}
					else if(s_rate >= Define.NUM_STAR1)
					{
						s_score += (int)(s_level/5);
					}
				}
				Random rd = new Random();
				int dy = rd.nextInt(Define.START_Y_DELTA);
				dis[i].setY(Define.BASE_START_Y + dy + Define.START_Y_DELTA*i);
				dis[i].setX(Define.BASE_START_X);
				float da = rd.nextFloat()*Define.ANGLE_DELTA;
				dis[i].setAngle(Define.BASE_ANGLE + da);
				float ds = rd.nextFloat()*Define.SPEED_DELTA;
				dis[i].setSpeed(Define.BASE_SPEED + ds + Define.SPEED_DELTA*i);
				dis[i].reset();
				dis[i].start();
			}
			else if(dis[i].isBurn())
			{
				snd_player.playSFX(DATA.SOUND_SFX_DIS_BURN);
			}
		}
		s_rate = (float)myDis.getScore()/myDis.getCount();
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
		{
			dis[i].update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
		}				
		levelUp();
		balloons[0].update(s_touch.getTouch(), s_touch.getX(), s_touch.getY());
	}
	else if(state == State.PAINT)
	{
		s_canvas.drawColor(Color.BLUE);
		s_gameSprites[DATA.SPR_GROUND].draw(s_canvas);
		s_gameSprites[DATA.SPR_BGR_1 + s_level - 1].draw(s_canvas);
		for(int i = 0; i < Define.NUM_DIS_MAX && i < s_level; i++)
			dis[i].draw(s_canvas);
		balloons[0].draw(s_canvas);
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
	Paint myPaint = new Paint();
	myPaint.setColor(Color.GREEN);
	s_canvas.drawRect(0, 0, SCREEN_WIDTH, Define.HUD_HEIGHT, myPaint);
	Untils.drawString(s_canvas, "HP:", Define.HUD_HP_X, Define.HUD_HP_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
	if(s_blood >= 0)
		drawLoandingBar(s_blood, Define.BLOOD_MAX, Define.HUD_HP_BAR_X, Define.HUD_HP_Y - Define.HUD_HP_H/2, Define.HUD_HP_W, Define.HUD_HP_H, Color.RED, Color.WHITE);
	else
		drawLoandingBar(0, Define.BLOOD_MAX, Define.HUD_HP_BAR_X, Define.HUD_HP_Y - Define.HUD_HP_H/2, Define.HUD_HP_W, Define.HUD_HP_H, Color.RED, Color.WHITE);
	Untils.drawString(s_canvas, "LEVEL: "+s_level+"   SCORE: "+s_score, Define.HUD_LEVEL_X, Define.HUD_LEVEL_Y, Color.BLACK, Define.TEXT_SIZE_MED, Align.LEFT);
}