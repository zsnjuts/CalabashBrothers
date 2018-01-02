package nju.zs;

import javax.swing.*;

public final class Main extends JFrame {

	public Main(){
		initUI();
	}

	public void initUI(){
		Field field = new Field();
		add(field);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(field.getBoardWidth(), field.getBoardHeight());
		setLocationRelativeTo(null);
		setTitle("葫芦兄弟");
	}

	public static void main(String[] args){
		Main game = new Main();
		game.setVisible(true);
	}
}
