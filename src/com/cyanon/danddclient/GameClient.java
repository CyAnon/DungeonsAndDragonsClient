package com.cyanon.danddclient;

import java.io.*;
import java.net.*;

import com.cyanon.dandd.attacktype.*;
import com.cyanon.dandd.networking.ClientInfoPacket;
import com.cyanon.dandd.networking.Packet;
import com.cyanon.dandd.networking.ServerInfoPacket;

public class GameClient {
	
	public Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private ServerInfoPacket serverDetails; //Migrate to server details class?
	
	public GameClient(Socket socket, String thisPlayersHandle) throws ClassNotFoundException, IOException {
		this.socket = socket;
		
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
		
		this.serverDetails = (ServerInfoPacket)ois.readObject(); //Migrate this to conditional check
		oos.flush();
		oos.writeObject(new ClientInfoPacket(thisPlayersHandle)); 
		oos.flush();
		
		System.out.println("Connected to the game " + this.serverDetails.getServerName() + " successfully!");
		this.start();
	}
	
	public void sendPacket() throws IOException, InterruptedException
	{
		//Where a packet is created based on action
	}
	
	public void receivePacket(Packet packetIn) throws IOException, NullPointerException, ClassNotFoundException
	{
		//Where a packet is broken down based on type
	}
	
	private void start()
	{

	}
}
