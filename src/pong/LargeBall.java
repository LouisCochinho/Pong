package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LargeBall extends Neutral{

	public LargeBall(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/fastball.png");
	}

	@Override
	public void action(Game game) {
		// TODO Auto-generated method stub
		game.setBallSize(8);
	}

	@Override
	public void revertedAction(Game game) {
		// TODO Auto-generated method stub
		game.setBallSize(5);
	}

	@Override
	public void setImage() throws SlickException {
		// TODO Auto-generated method stub
		this.bonusImage = new Image("res/fastballUsed.png");
	}
}
