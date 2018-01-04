package nju.zs;

import nju.zs.creature.Creature;
import nju.zs.creature.badcharacter.BadCharacter;
import nju.zs.creature.badcharacter.SnakeGenie;
import nju.zs.creature.goodcharacter.GoodCharacter;
import nju.zs.creature.goodcharacter.Grandpa;
import nju.zs.layout.*;
import nju.zs.replay.Recorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Field extends JPanel {
	private static BufferedImage background;
	static{
		try {
			background = ImageIO.read(new File("src/main/resources/background.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private boolean running = false;
	private boolean showing = false;
	private Room room = new Room();
	private Timer timer;
	private ExecutorService exec;
	private Recorder recorder = new Recorder(room);

	public Field(){
		setFocusable(true);
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		loadRoom();
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
		return background.getHeight()+35;
	}

	class KAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(showing)
				return;

			int key = e.getKeyCode();
			if(key==KeyEvent.VK_SPACE){
				if(!running) {
					running = true;
					beginGame();
				} else {
					running = false;
					stopGame();
				}
			} else if (key==KeyEvent.VK_L && !running){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				fileChooser.setFileFilter(new FileNameExtensionFilter("Record Files", "rcd"));
				int ret = fileChooser.showOpenDialog(getParent());
				if(ret==JFileChooser.APPROVE_OPTION) {
					System.out.println("open " + fileChooser.getSelectedFile().getName());
					Player player = new Player(fileChooser.getSelectedFile().getName());
					new Thread(player).start(); //由于repaint只能在子线程调用有效，所以需要额外开一个子线程
				}
			}
		}
	}

	class MAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			if(showing)
				return;
			System.out.println(e);
		}
	}

	private void loadRoom(){
		//生成葫芦七兄弟、老爷爷和蝎子精小喽啰、蛇精，并加入二维空间
		room.addQueue(new YanxingLayout().place(new Queue(GoodCharacter.getBrothers())), 30, 400);
		room.addQueue(new ChangsheLayout().place(new Queue(BadCharacter.getGenies())), 500, 400);
		room.addCreature(new Grandpa(new Position(0,0)), 200, background.getHeight());
		room.addCreature(new SnakeGenie(new Position(0,0)), 500, background.getHeight());
	}

	private void beginGame(){
		recorder.beginRecord();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				repaint();
				recorder.record();
			}
		}, 0, 1000);
		exec = Executors.newCachedThreadPool();
		for(Creature ct:room.getCreatures())
			exec.execute(ct);
	}

	private void stopGame(){
		recorder.stopRecord();
		timer.cancel();
		exec.shutdownNow();
	}

	private void showRoom(Graphics g){
		g.drawImage(background, 0, 0, this);
		synchronized (room) {
			for (Thing2D ct : room.getThings())
				g.drawImage(ct.getImageIcon().getImage(), ct.x(), ct.y() - ct.getImageIcon().getIconHeight(), this);
		}
	}

	/* 用于重现游戏 */
	class Player implements Runnable{
		private String recordFileName;
		public Player(String recordFileName){
			this.recordFileName = recordFileName;
		}
		public void run(){
			showing = true;
			File recordFile = new File(recordFileName);
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(recordFile));
				long begin = System.currentTimeMillis();
				while(true){
					String recordInfoLine = bufferedReader.readLine();
					if(recordInfoLine==null)
						break;

					String[] recordInfo = recordInfoLine.split(" ");
					long timeOffset = Long.valueOf(recordInfo[0]); //时间
					int size = Integer.valueOf(recordInfo[1]); //记录的Thing2D数
					for(int i=0;i<size;i++) {
						String[] thingInfo = bufferedReader.readLine().split(" ");
						int x = Integer.valueOf(thingInfo[1]); //坐标x
						int y = Integer.valueOf(thingInfo[2]); //坐标y
						for (Thing2D t : room.getThings())
							if (thingInfo[0].equals(t.toString())) {
								t.setPosition(x, y);
								break;
							}
					}

					while(System.currentTimeMillis()-begin<timeOffset)
						;
					repaint();
					System.out.println("repainted");
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			showing = false;
		}
	}
}
