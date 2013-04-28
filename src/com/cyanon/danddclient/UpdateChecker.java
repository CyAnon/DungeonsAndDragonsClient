package com.cyanon.danddclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

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
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("/n/n/nThe server has been shut down, and you have been disconnected.");
				System.exit(-1);
			}
		}
	}
}
