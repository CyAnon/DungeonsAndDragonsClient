package com.cyanon.danddclient;

import java.io.*;
import java.net.*;

import com.cyanon.dandd.monsters.*;

public class DungeonsAndDragonsClient {
	
	private static GameClient gameClient;
	
	protected static Socket s = null;
	protected static OutputStream os;
	protected static ObjectOutputStream oos;
	
	public DungeonsAndDragonsClient()
	{
	}
	
	public static void main(String[] argv) throws IOException, InterruptedException
	{
		try
		{
			s = new Socket("127.0.0.1", 54949);
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
		
		gameClient = new GameClient(s);
		gameClient.writeToSocket();
	}
}
