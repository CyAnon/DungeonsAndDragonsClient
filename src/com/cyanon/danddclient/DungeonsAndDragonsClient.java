package com.cyanon.danddclient;

import java.io.*;
import java.net.*;

import com.cyanon.dandd.monsters.*;

public class DungeonsAndDragonsClient {
	
	private static GameClient gameClient;
	
	protected static Socket s = null;
	protected static OutputStream os;
	protected static ObjectOutputStream oos;
	
	private static InputStreamReader isr;
	private static BufferedReader br;
	
	protected static String thisPlayersHandle;
	private static String ipAddr;
	private static int    ipPort;
	
	private static float currentVersionNumber = 0.01f;
	
	public DungeonsAndDragonsClient()
	{
	}
	
	public static void main(String[] argv) throws IOException, InterruptedException, ClassNotFoundException
	{
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		
		System.out.println("Welcome to Packet Monsters! This is the test client (version " + currentVersionNumber + ").");
		System.out.println("Fully open source at github.com/cyanon for your perusal. Released under CC.");
		setUpPlayer();
				
		try
		{
			s = new Socket(ipAddr, ipPort);
		}
		catch (ConnectException e)
		{
			System.out.println("Unable to connect to a server with these details! Check and try again.");
			System.exit(-1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Hydra hydra = new Hydra(100);
		gameClient = new GameClient(s, thisPlayersHandle, hydra);
	}
	
	public static void setUpPlayer() throws IOException
	{
		System.out.println("Please enter your name, brave warrior:");
		thisPlayersHandle = br.readLine();
		System.out.println("Thank you " + thisPlayersHandle + ". What's the server address:");
		ipAddr = br.readLine();	
		System.out.println("Lastly, what port do you want to connect to:");
		ipPort = Integer.parseInt(br.readLine());
		System.out.println("Thanks again. Waiting to connect you...");
	}
}
