package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SmallRectangle extends Malus{

	public SmallRectangle(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/smallRectangle.png");
	}

	@Override
	public void setImage() throws SlickException {
		// TODO Auto-generated method stub
		this.bonusImage = new Image("res/smallRectangleUsed.png");
	}

	@Override
	public void action(Player player) {
		// TODO Auto-generated method stub
		player.setRectangleSize(50);
	}

	@Override
	public void revertedAction(Player player) {
		// TODO Auto-generated method stub
		player.setRectangleSize(100);
	}
}
