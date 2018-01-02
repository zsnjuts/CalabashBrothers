package nju.zs.layout;

import nju.zs.Position;

import java.util.ArrayList;

public class YanxingLayout implements Layout {
	public Queue place(Queue queue) {
		int offsetx = queue.getOffsetx(), offsety = queue.getOffsety();
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++)
			queuePositions.get(i).setPosition((queuePositions.size()-1-i)*offsetx,i*offsety);
		return queue;
	}
}
