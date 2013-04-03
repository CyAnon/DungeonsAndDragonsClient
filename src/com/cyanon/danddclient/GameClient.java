package com.cyanon.danddclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.cyanon.dandd.attacktype.Attack;
import com.cyanon.dandd.attacktype.element.Element;

public class GameClient {

	private Attack test;
	public Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private String thisPlayersHandle;
	
	public GameClient(Socket socket) {
		this.socket = socket;
		try
		{
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			this.ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Connection and setup of TCP streams successful!");
	}
	
	public void writeToSocket() throws IOException, InterruptedException
	{
		test = new Attack("Frost Wind", 10, 10);
		
		try {
			oos.flush();
			oos.writeObject(this.test);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
