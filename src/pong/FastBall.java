package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class FastBall extends Neutral {

	// Augmente la vitesse de la balle
	public FastBall(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/fastball.png");
	}

	@Override
	public void setImage() throws SlickException {
		this.bonusImage = new Image("res/fastballUsed.png");
	}

	@Override
	public void action(Game game) {
		// TODO Auto-generated method stub
		game.getBall().setSpeed(1.3f);
	}

	@Override
	public void revertedAction(Game game) {
		// TODO Auto-generated method stub
		game.getBall().setSpeed(0.7f);
	}
}
