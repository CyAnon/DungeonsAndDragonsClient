package com.cyanon.danddclient;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.cyanon.dandd.networking.Packet;

public class UpdateChecker extends Thread {

	private GameClient myParent;
	private ObjectInputStream ois;
	
	public UpdateChecker(GameClient parent, ObjectInputStream oisIn)
	{
		this.ois = oisIn;
		this.myParent = parent;
		System.out.println("Update checker polling...");
	}
	
	public void run()
	{
		while (true)
		{
			try {
				myParent.receivePacket((Packet) ois.readObject());
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
