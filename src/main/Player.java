package main;
import javax.swing.*;

public class Player {
	int health = 0;
	int remainingDeck = 24;
	int[] deck = new int[12];
	Card[] hand = new Card[3];
	Card[] picks = new Card[2];
	String name = "";
	JButton card1 = new JButton();
	JButton card2 = new JButton();
	JButton card3 = new JButton();
}
