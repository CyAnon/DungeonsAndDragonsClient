package com.cyanon.danddclient;

import java.io.*;
import java.net.*;

import com.cyanon.dandd.attacktype.*;

public class GameClient {

	private Attack test;
	
	public Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private InputStreamReader isr;
	private BufferedReader br;
	
	private String thisPlayersHandle;
	private String thisGameName;
	
	public GameClient(Socket socket) throws ClassNotFoundException, IOException {
		this.socket = socket;
		this.isr = new InputStreamReader(System.in);
		this.br = new BufferedReader(isr);
		
		try
		{
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			oos.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		this.thisGameName = (String)ois.readObject();
		oos.flush();
		System.out.println("Connected to the game " + this.thisGameName + " successfully!");
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
	
	public void setUpPlayer() throws IOException
	{
		this.thisPlayersHandle = br.readLine();
		oos.writeObject(new String(this.thisPlayersHandle));
	}
}
