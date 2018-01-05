package nju.zs.layout;

import nju.zs.Position;

import java.util.ArrayList;

public class HeyiLayout implements Layout {
	public Queue place(Queue queue) {
		int offsetx = queue.getOffsetx(), offsety = queue.getOffsety();
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++) {
			if(i%2==0)
				queuePositions.get(i).setPosition(i/2*offsetx, i/2*offsety);
			else
				queuePositions.get(i).setPosition(i/2*offsetx, (queuePositions.size()-1-i/2)*offsety);
		}
		return queue;
	}
}
