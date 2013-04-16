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
	
	public DungeonsAndDragonsClient()
	{
	}
	
	public static void main(String[] argv) throws IOException, InterruptedException, ClassNotFoundException
	{
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		
		System.out.println("Welcome to D&D! This is the test client. Also, the name will probably change.");
		System.out.println("Please enter your name, brave warrior:");
		setUpPlayer();
		System.out.println("Thank you " + thisPlayersHandle + ". Please wait for connection.");
		
		try
		{
			s = new Socket("localhost", 54949);
		}
		catch (ConnectException e)
		{
			System.out.println("No server found on this IP!");
			System.exit(-1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		gameClient = new GameClient(s, thisPlayersHandle);
	}
	
	public static void setUpPlayer() throws IOException
	{
		thisPlayersHandle = br.readLine();
	}
}
