package nju.zs.layout;

import nju.zs.Position;

import java.util.ArrayList;

public class ChangsheLayout implements Layout {
	public Queue place(Queue queue) {
		int offsetx = queue.getOffsetx();
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++)
			queuePositions.get(i).setPosition(0, i*offsetx);
		return queue;
	}
}
