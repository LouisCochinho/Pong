package pong;


import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends BasicGame{

	private GameContainer gc;
	private Rectangle middle_rect,left_rect_win,right_rect_win;
	private Ball ball;
	private Player player1;
	private Player player2;
	private TimerTask task;
	//private Music music;
	//private static int FINAL_SCORE = 3;
	public static boolean PAUSED = false;
	public static boolean END = false;
	public MyTimer timer;
	public int test;
	
	public Game(String title) {
		super(title);
	}

	//Affichage des �l�ments du jeu
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//g.setColor(Color.white);
		//g.fillRect(0,0, gc.getWidth(),gc.getHeight());
		
		// Afficher les rectangles 
		player1.getRectangle().display(g);
		player2.getRectangle().display(g);
		
		// Afficher la balle
		ball.display(g);
		
		// Afficher les rectangles blancs du milieu du terrain
		for(int i = 0;i<gc.getHeight()/middle_rect.getHeight();i++){
			middle_rect.setPosY(middle_rect.getPosY()+middle_rect.getHeight()+20);
			middle_rect.display(g);
		}
		middle_rect.setPosY(0);
		
		// Afficher les scores
		player1.getScore().display(g);
		player2.getScore().display(g);
		
		// Afficher les inventaires
		player1.getInventory().display(g);
		player2.getInventory().display(g);
		
		// Affichage des bonus du joueur 1 
		for (MyObject b : player1.getInventory().getAvailableObjects()){
			b.display(g);
		}
		
		// Affichage des commandes
		g.drawString("E", 115, 0);
		g.drawString("R", 145, 0);
		g.drawString("T", 175, 0);
		g.drawString("1", 615, 0);
		g.drawString("2", 645, 0);
		g.drawString("3", 675, 0);
		
		// Affichage des bonus du joueur 2
		for (MyObject b : player2.getInventory().getAvailableObjects()){
			b.display(g);
		}
		
		// Affichage du chronom�tre
		timer.display(g);
		
		// Menu de pause
		if (PAUSED) {
			displayPause(gc,g); 	        
		}
		if(END){
			displayEnd(gc,g);
		}
	}


	// Cr�ation des �l�ments du jeu
	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		PAUSED = false;
		END = false;
		// game container
		this.gc = gc;	
		
		// Cr�ation des rectangles
		Rectangle left_rect = new Rectangle(20, 20, 20, 100, Color.red,0.7f);
		middle_rect = new Rectangle(gc.getWidth()/2,0,10,40,Color.white,0);
		//middle_rect = new Rectangle(gc.getWidth()/2,0,10,40,Color.black,0);
		Rectangle right_rect = new Rectangle(gc.getWidth()-40, gc.getHeight()-120, 20, 100, Color.blue,0.7f);
	    //left_rect_win = new Rectangle(10,10,10,gc.getHeight(),Color.white,0);
		//right_rect_win = new Rectangle(gc.getWidth()-10,10,10,gc.getHeight(),Color.white,0);
		
		// Cr�ation et placement initial de la balle
		ball = new Ball(5,Color.white,0.7f);
		ball.center(gc);
		
		// Cr�ation des scores
		Score left_score = new Score(gc.getWidth()/2 - 100, 20);
		Score right_score = new Score(gc.getWidth()/2 + 100, 20);		
		
		// Cr�ation des joueurs
		player1 = new Player(left_rect,left_score,new Inventory(100,15),1);
		player2 = new Player(right_rect,right_score,new Inventory(600,15),2);
		
				
		// Gestion de la musique
		//Music music = new Music("res/music.ogg");
		//music.loop();
		
		// Gestion du timer
		timer = new MyTimer(120);
		
		task = new TimerTask()
		{
			int cpt = 10;
			@Override
			public void run() 
			{
				if(!PAUSED){
					timer.decrement();
					cpt--;
					// A chaque seconde => pour chaque bonus activ� de chaque joueur => d�cr�menter le temps d'activation
					for (MyObject b : player1.getInventory().getActivatedObjects()){
						b.decrement();
						cpt = 10;
					}
					for (MyObject b : player2.getInventory().getActivatedObjects()){
						b.decrement();
						cpt = 10;
					}
					// MARCHE PAS
					if(cpt==0){
						if(!player1.isInventoryFull()){				
							player1.addObject(Util.getRandomObject(player1.getNumber(), player1.getNextAvailablePlaceInInventory()));
						}
						if(!player2.isInventoryFull()){
							player2.addObject(Util.getRandomObject(player2.getNumber(), player2.getNextAvailablePlaceInInventory()));
						}
					}
				}
			}	
		};	
			
		// Effectue la tache toutes les secondes
		timer.scheduleAtFixedRate(task, 0, 1000);		
	}

	// Mise � jour des �l�ments du jeu
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		keyboardListener(gc);
		player1.getRectangle().move(delta, gc);
		player2.getRectangle().move(delta, gc);
		player1.getRectangle().stopMoving();
		player2.getRectangle().stopMoving();
		ball.move(delta,gc,player1.getRectangle(),player2.getRectangle());

		// met à jour le score
		updateScores(gc.getGraphics());
		
		// Gestion de la fin au score	
		//END = player1.getScore().getValue()==FINAL_SCORE || player2.getScore().getValue()==FINAL_SCORE;
		if(timer.end()){
			END = true;
			gc.setPaused(true);			
		}
		// Gestion bonus
		// Pour chaque bonus actif de chaque joueur si le temps est �coul� => D�sactiver le bonus
		
		for (MyObject b : player1.getInventory().getActivatedObjects()){
			if(b.isActivationTimeFinished()){
				if(b instanceof Bonus){
					b.desactivate(player1);					
				}
				if(b instanceof Malus){
					b.desactivate(player2);					
				}
				if(b instanceof Neutral){
					b.desactivate(this);					
				}
				player1.removeObject(b);				
			}
		}
		
		for (MyObject b : player2.getInventory().getActivatedObjects()){
			if(b.isActivationTimeFinished()){
				if(b instanceof Bonus){
					b.desactivate(player2);					
				}
				if(b instanceof Malus){
					b.desactivate(player1);					
				}
				if(b instanceof Neutral){
					b.desactivate(this);					
				}
				player2.removeObject(b);
			}
		}
	}

	public void keyboardListener(GameContainer gc) throws SlickException {

		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			gc.exit();
		}
		if (gc.getInput().isKeyPressed(Input.KEY_P)){
			gc.setPaused(!gc.isPaused());
			PAUSED = !PAUSED;
			timer.setPaused(!timer.isPaused());
		}		
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)){
			player2.getRectangle().setMoving_down(true);
			player2.getRectangle().setMoving_up(false);
		}
		if(gc.getInput().isKeyDown(Input.KEY_UP)){
			player2.getRectangle().setMoving_down(false);
			player2.getRectangle().setMoving_up(true);
		}
		if(gc.getInput().isKeyDown(Input.KEY_Z)){
			player1.getRectangle().setMoving_down(false);
			player1.getRectangle().setMoving_up(true);
		}
		if(gc.getInput().isKeyDown(Input.KEY_S)){
			player1.getRectangle().setMoving_down(true);
			player1.getRectangle().setMoving_up(false);			
		}		
		if((PAUSED||END) && gc.getInput().isKeyDown(Input.KEY_M)){
			// Recommencer depuis la pause
			gc.setPaused(!gc.isPaused());			
			task.cancel();	
			gc.reinit();		
		}
	
		// Bonus 		
		for(MyObject b : player1.getInventory().getAvailableObjects()){
			if(gc.getInput().isKeyDown(b.getInputKey())){
				if(!b.isActivated() && player1.getNumberOfActivatedBonus()<3){
					if(b instanceof Bonus){
						b.activate(player1);					
					}
					if(b instanceof Malus){
						b.activate(player2);					
					}
					if(b instanceof Neutral){
						b.activate(this);					
					}
				}
			}
		}
		
		for(MyObject b : player2.getInventory().getAvailableObjects()){
			if(gc.getInput().isKeyDown(b.getInputKey())){
				if(b instanceof Bonus){
					b.activate(player2);					
				}
				if(b instanceof Malus){
					b.activate(player1);					
				}
				if(b instanceof Neutral){
					b.activate(this);					
				}
			}
		}
		
		// Plein �cran
		if(gc.getInput().isKeyDown(Input.KEY_F)){
			try {
				
				gc.setFullscreen(!gc.isFullscreen());
				// Mise en pause du jeu si le jeu n'�tait pas d�j� en pause
				if(!gc.isPaused()){
					gc.setPaused(!gc.isPaused());
					PAUSED = !PAUSED;
					timer.setPaused(!timer.isPaused());
				}				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	public void displayPause(GameContainer gc, Graphics g){
		Color trans = new Color(0f,0f,0f,0.5f);
		g.drawString("PAUSE", 700, 20);
		g.drawString("(p)Reprendre", 675, 40);
		g.drawString("(m)Recommencer", 675, 60);
		g.setColor(trans);
		g.fillRect(0,0, gc.getWidth(),gc.getHeight());
	}

	public void displayEnd(GameContainer gc, Graphics g){
		Color trans = new Color(0f,0f,0f,0.5f);
		g.drawString("FIN DU JEU", 700, 20);
		g.drawString("(m)Recommencer", 675, 40);
		g.setColor(trans);
		g.fillRect(0,0, gc.getWidth(),gc.getHeight());
		g.setColor(Color.white);
		if(player1.getScore().getValue()>player2.getScore().getValue()){
			g.drawString("WINNER", 100, gc.getHeight()/2);
		}
		else if(player1.getScore().getValue()<player2.getScore().getValue()){
			g.drawString("WINNER", 600, gc.getHeight()/2);
		}
		else{
			g.drawString("EGALITE", gc.getWidth()/2, gc.getHeight()/2);
		}
	}	
	
	public void updateScores(Graphics g){
		if(ball.getPosX() < 1){
			player2.getScore().increment();	
			//left_rect_win.display(g);
		}
		if(ball.getPosX()+ball.getRadius()*2>gc.getWidth()){
			player1.getScore().increment();
			//right_rect_win.display(g);
		}	
	}
	
	// Getters
	
	public Ball getBall(){
		return this.ball;
	}
	
	public Player getPlayer1(){
		return this.player1;
	}
	
	public Player getPlayer2(){
		return this.player2;
	}
	
	// Setters 
	
	public void setBallSize(float radius){
		this.ball.setRadius(radius);
	}
}
