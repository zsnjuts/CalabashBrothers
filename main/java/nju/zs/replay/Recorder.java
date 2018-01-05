package nju.zs.replay;

import nju.zs.Room;
import nju.zs.creature.Creature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Recorder {

	public Recorder(Room room){
		this.room = room;
		if(file.exists())
			file.delete();
	}

	public void beginRecord(){
		recording = true;
		begin = System.currentTimeMillis();
	}

	/* 每一次记录头格式：记录时间 本次记录的Thing2D数 */
	public void record(){
		try {
			if(!recording)
				return;

			long timeOffset = System.currentTimeMillis() - begin;
			if (!file.exists())
				file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write(timeOffset+" "+room.getThings().size()+"\n");
			for(Creature t:room.getCreatures())
				bufferedWriter.write(t.toString()+" "+t.x()+" "+t.y()+" "+(t.getStatus()!=Creature.Status.DEAD)+"\n");
			bufferedWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void stopRecord(){
		recording = false;
	}

	private File file = new File("game.rcd");
	private Room room;
	private long begin;
	private boolean recording;
}
