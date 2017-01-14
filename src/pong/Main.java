package pong;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	static private AppGameContainer app;
	
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		app = new AppGameContainer(new Game("pong"));
		app.setDisplayMode( 800, 600, false );
		app.setShowFPS(false);
		app.start();
	}
}
