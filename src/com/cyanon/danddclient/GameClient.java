package com.cyanon.danddclient;

import java.io.*;
import java.net.*;

import com.cyanon.dandd.attacktype.Attack;
import com.cyanon.dandd.gui.MainFrame;
import com.cyanon.dandd.monsters.Monster;
import com.cyanon.dandd.networking.*;

public class GameClient {
	
	public Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private static InputStreamReader isr;
	private static BufferedReader br;
	
	private UpdateChecker uc;
	
	private String parserDelimiter ="[ ]+";

	private ServerInfoPacket serverDetails; //Migrate to server details class?
	
	private Monster myMonster;
	
	public GameClient(Socket socket, String thisPlayersHandle, Monster monster) throws ClassNotFoundException, IOException 
	{
		this.socket = socket;
		this.myMonster = monster;
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
		oos.writeObject(new ClientInfoPacket(thisPlayersHandle, myMonster)); 
		oos.flush();
		
		uc = new UpdateChecker(this, ois);
		uc.start();
		
		System.out.println("Connected to the game " + this.serverDetails.getServerName() + " successfully!");
		MainFrame mainFrame = new MainFrame(myMonster);
		this.start();
	}

	public void receivePacket(Packet packetIn) throws IOException, NullPointerException, ClassNotFoundException
	{
		if (packetIn instanceof ServerToClientMessagePacket)
		{
			System.out.println(packetIn.getPayload());
		}
	}
	
	private void processCommand(String string) throws IOException
	{
		//Chop the string up into pieces, and deliver an angry message if this isn't possible, or the command is wrong
		String[] parsedCommand = string.split(parserDelimiter);
		
		switch (parsedCommand[0])
		{
		case "/quit":
			System.exit(0);
			break;
		case "/message":
			oos.writeObject(new StringPacket(this.processMessage(parsedCommand)));
			break;
		case "/attack":
			oos.writeObject(new AttackPacket(myMonster.getAttack(Integer.parseInt(parsedCommand[1]))));
			break;
		default:
			System.out.println("Error!");
		}
		oos.flush();
	}
	
	private void start() throws IOException
	{
		System.out.println("Welcome to the client interface! Enter your commands:");
		while (true)
		{
			this.processCommand(br.readLine());
			System.out.print("> ");
		}
	}
	
	private String processMessage(String[] stringArray)
	{
		String fullMessage = " ";
		for (int i = 1; i < stringArray.length; i++)
		{
			fullMessage += (stringArray[i] + " ");
		}
		return fullMessage;
	}
	
	public void printMessage(String string)
	{
		System.out.println(string);
	}
}
