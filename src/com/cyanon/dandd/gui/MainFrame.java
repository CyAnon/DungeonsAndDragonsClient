package com.cyanon.dandd.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cyanon.dandd.monsters.*;

public class MainFrame {

	private static Monster selectedMonster;
	
	static JButton attack0;
	static JButton attack1;
	static JButton attack2;
	static JButton attack3;
	
	private static void createAndShowGUI()
	{
		JFrame jframe = new JFrame("Dungeons And Dragons Client");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(600, 600, 400, 400);
		jframe.setLayout(new BorderLayout());
		
		JButton attack0 = new JButton(selectedMonster.getAttackArray().get(0).getAttackName());
		JButton attack1 = new JButton(selectedMonster.getAttackArray().get(1).getAttackName());
		JButton attack2 = new JButton(selectedMonster.getAttackArray().get(2).getAttackName());
//		JButton attack3 = new JButton(Hydra.getAttackArray().get(3).getAttackName());
		
//		attack0.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Penis!");
//			}
//		});
		
		jframe.getContentPane().add(attack0, BorderLayout.EAST);
		jframe.getContentPane().add(attack1, BorderLayout.EAST);
		jframe.getContentPane().add(attack2, BorderLayout.WEST);
//		jframe.getContentPane().add(attack3);
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	public MainFrame(Monster myMonster)
	{
		this.selectedMonster = myMonster;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}