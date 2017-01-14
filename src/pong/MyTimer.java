package pong;

import java.util.Timer;

import org.newdawn.slick.Graphics;

public class MyTimer extends Timer {

	private int timer;
	private static boolean PAUSED = false;
	private static boolean END = false;
	
	public MyTimer(int timer){
		this.timer = timer;
	}
	
	public MyTimer(){
		timer = 300;
	}
	
	public int getTimer(){
		return timer;
	}
	
	public void reset(){
		this.timer = 300;
	}
	
	public void reset(int timer){
		this.timer = timer;
	}
	
	public boolean isPaused(){
		return MyTimer.PAUSED;
	}
	
	public void setPaused(boolean paused){
		this.PAUSED = paused;
	}
	
	public String toString(){
		// minutes : secondes
		return timer/60 + " : "+ timer%60;
	}
	
	public void display(Graphics g){
		g.drawString(this.toString(), 382, 20);
	}
	
	public boolean end(){
		return timer == 0;
	}
	
	public void decrement(){
		if(!END){
			timer--;
		}
		this.END = timer == 0;
	}
}
