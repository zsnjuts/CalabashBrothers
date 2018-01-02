package nju.zs;

import nju.zs.creature.Creature;
import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.badcharacter.SnakeGenie;
import nju.zs.creature.goodcharacter.GoodCharacter;
import nju.zs.creature.goodcharacter.Grandpa;
import nju.zs.layout.ChangsheLayout;
import nju.zs.layout.Layout;
import nju.zs.layout.Queue;
import nju.zs.layout.YanxingLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;

public class Field extends JPanel {
	private static BufferedImage background;
	private static String recordFileName = "game.rcd";
	static{
		try {
			background = ImageIO.read(new File("src/main/resources/background.jpg"));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private Room room = new Room();
	private java.util.Timer timer = new Timer();
	private ExecutorService exec = Executors.newCachedThreadPool();

	public Field(){
		setFocusable(true);
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		initRecord();
		loadRoom();
		beginTimer();
		startThreads();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		showRoom(g);
	}

	public int getBoardWidth() {
		return background.getWidth();
	}

	public int getBoardHeight() {
		return background.getHeight();
	}

	private void initRecord(){
		File record = new File(recordFileName);
		if(record.exists())
			record.delete();
	}

	private void loadRoom(){
		//生成葫芦七兄弟、老爷爷和蝎子精小喽啰、蛇精，并加入二维空间
		room.addQueue(new ChangsheLayout().place(new Queue(GoodCharacter.getBrothers())), 0, 100);
		room.addQueue(new YanxingLayout().place(new Queue(BadCharacter.getGenies())), 400, 200);
		room.addCreature(new Grandpa(new Position(0,0)), 200, background.getHeight());
		room.addCreature(new SnakeGenie(new Position(0,0)), 500, background.getHeight());
	}

	private void beginTimer(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, 50);
	}

	private void startThreads(){
		for(Creature ct:room.getCreatures())
			exec.execute(ct);
	}

	private void showRoom(Graphics g){
		g.drawImage(background, 0, 0, this);
		for(Creature ct:room.getCreatures())
			g.drawImage(ct.getImageIcon().getImage(), ct.x(), ct.y() - ct.getImageIcon().getIconHeight(), this);
	}

	class KAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e);
		}
	}

	class MAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			System.out.println(e);
		}
	}
}
