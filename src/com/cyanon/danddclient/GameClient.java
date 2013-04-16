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
	
	private static InputStreamReader isr;
	private static BufferedReader br;
	
	private String parserDelimiter ="[ ]+";

	private ServerInfoPacket serverDetails; //Migrate to server details class?
	
	public GameClient(Socket socket, String thisPlayersHandle) throws ClassNotFoundException, IOException 
	{
		this.socket = socket;
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		
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
	
	private void processCommand(String string)
	{
		//Chop the string up into pieces, and deliver an angry message if this isn't possible, or the command is wrong
		String[] parsedCommand = string.split(parserDelimiter);
		
		switch (parsedCommand[0])
		{
		case "/quit":
			System.exit(0);
			break;
		default:
			System.out.println("Error!");
		}
		
		
	}
	
	private void start() throws IOException
	{
		System.out.println("Welcome to the client interface! Enter your commands:");
		while (true)
		{
			System.out.print("> ");
			this.processCommand(br.readLine());
		}
	}
}
