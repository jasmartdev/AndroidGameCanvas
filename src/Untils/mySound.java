/**
 * 
 */
package Untils;

import android.media.MediaPlayer;
import android.content.Context;

import Untils.Configs;

public class mySound {

	public static int[] soundID;
	public static MediaPlayer[] player;
	Context content;
	public static int lastID, lastIDSFX;
	public static boolean m_enable, sfx_enable;
	
	public mySound(Context content,int[] soundID)
	{
		this.soundID = soundID;
		player = new MediaPlayer[soundID.length];
		this.content = content;
		lastID = -1;
		lastIDSFX = -1;
		m_enable = true;
		sfx_enable = true;
	}
	public void load(int id)
	{
		player[id] = MediaPlayer.create(content, soundID[id]);
	}
	public void loadAll()
	{
		for(int i = 0; i < soundID.length; i++)
			load(i);
	}
	public void setenableAll(boolean is)
	{
		setM_enable(is);
		setSfx_enable(is);
	}
	public void setM_enable(boolean is)
	{
		m_enable = is;
	}	
	public void setSfx_enable(boolean is)
	{
		sfx_enable = is;
	}
	public void play(int id)
	{
		if(Configs.snd_Check_Isplaying)
		{
			if(id == lastID && player[id].isPlaying())
				return;
		}
		if(Configs.snd_Pause_BeforePlay)
		{
			if(lastID >= 0)
				pause(lastID);
		}
		player[id].start();
		lastID = id;
	}
	public void playM(int id)
	{
		if(!m_enable)
			return;
		play(id);
	}
	public void playSFX(int id)
	{
		if(!sfx_enable)
			return;
		if(Configs.snd_Use_SFX)
		{
			if(Configs.snd_Check_Isplaying)
			{
				if(id == lastIDSFX && player[id].isPlaying())
					return;
			}
			if(Configs.snd_Pause_BeforePlay)
			{
				if(lastIDSFX >= 0)
					pause(lastIDSFX);
			}
			player[id].start();
			lastIDSFX = id;
		}
		else
		{
			play(id);
		}		
	}
	public void pause(int id)
	{
		if(player[id].isPlaying())
		{
			player[id].pause();
			player[id].seekTo(0);
		}
	}
	public void pauseAll()
	{
		for(int i = 0; i < soundID.length; i++)
			pause(i);
	}
	public void stop(int id)
	{
		player[id].stop();
	}
	public void stopAll()
	{
		for(int i = 0; i < soundID.length; i++)
			stop(i);
	}
}
