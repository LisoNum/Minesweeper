package controller;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Manager manger = new Manager();
		
		JFrame f = new JFrame("Minesweeper");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(manger.getUi());
		f.pack();
		f.setVisible(true);
		
		
		/*
		
		System.out.println("To Know the available font family names");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        System.out.println("Getting the font family names");

        // Array of all the fonts available in AWT
        String fonts[] = ge.getAvailableFontFamilyNames();

        // Getting the font family names
        for (String i : fonts) {
            System.out.println(i + " ");
        }
        */
	}

}
