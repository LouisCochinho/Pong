package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FastRectangle extends Bonus{

	// Augmente la vitesse de d�placement du rectangle
	public FastRectangle(int posX, int posY) throws SlickException {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.bonusImage = new Image("res/fastRectangle.png");
	}
	@Override
	public void setImage() throws SlickException {
		// TODO Auto-generated method stub
		this.bonusImage = new Image("res/fastRectangleUsed.png");
	}

	@Override
	public void action(Player player) {
		// TODO Auto-generated method stub
		player.getRectangle().setSpeed(1.3f);
	}

	@Override
	public void revertedAction(Player player) {
		// TODO Auto-generated method stub
		player.getRectangle().setSpeed(0.7f);
	}
}
