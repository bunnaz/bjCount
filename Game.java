package bJCountAlpha;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logic.Deck;
import logic.Tracking;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;

public class Game extends JFrame {
	

		
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		//---
		final Tracking newGameTracker = new Tracking();
		newGameTracker.playerUnits = (double) WelcomeMenu.unitSpinner.getValue();
		newGameTracker.numberOfCards = ((int) WelcomeMenu.shoeSizeSpinner.getValue() * 52);
		System.out.println(newGameTracker.playerUnits + " " + newGameTracker.numberOfCards);
		final Deck playingDeck = new Deck();
		final Deck discardDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.Shuffle();

		final Deck playerDeck = new Deck();
		final Deck dealerDeck = new Deck();
		//--
		setTitle("Game On");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//--
		final JLabel playerLabel = new JLabel("Player: 0");
		playerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		playerLabel.setBounds(432, 566, 143, 20);
		contentPane.add(playerLabel);
		
		final JLabel dealerLabel = new JLabel("Dealer: 0");
		dealerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dealerLabel.setBounds(432, 10, 216, 20);
		contentPane.add(dealerLabel);
		//--
		
		//--
		final JLabel Card4 = new JLabel("");
		Card4.setBounds(400, 40, 100, 145);
		contentPane.add(Card4);
		
		final JLabel Card3 = new JLabel("");
		Card3.setBounds(500, 40, 100, 145);
		contentPane.add(Card3);
		
		final JLabel Card2 = new JLabel("");
		Card2.setBounds(400, 350, 100, 145);
		contentPane.add(Card2);
		
		final JLabel Card1 = new JLabel("");
		Card1.setBounds(450, 400, 100, 145);
		contentPane.add(Card1);
		//--
		final JButton hitButton = new JButton("Hit");
		hitButton.setBounds(695, 461, 89, 25);
		contentPane.add(hitButton);
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				playerDeck.draw(playingDeck);
				newGameTracker.cardsUsed++;
				playerLabel.setText("Player: " + playerDeck.cardsValue());
				JLabel drawnCard = new JLabel("");
				drawnCard.setBounds(400-(50*(newGameTracker.cardsUsed - 4)), 350-(50*(newGameTracker.cardsUsed - 4)), 100, 145);
				contentPane.add(drawnCard);
				drawnCard.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + playerDeck.getCard(newGameTracker.cardsUsed - 3).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
				contentPane.setComponentZOrder(drawnCard, 1);
				drawnCard.setVisible(true);
				if (playerDeck.cardsValue() > 21) {
					hitButton.setVisible(false);
				}
			}
		});
		
		final JButton newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newGameButton.setBounds(411, 537, 100, 30);
		newGameButton.setVisible(false);
		contentPane.add(newGameButton);
		
		
		final JButton standButton = new JButton("Stand");
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				dealerLabel.setText("Dealer: " + dealerDeck.cardsValue());
				Card4.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + dealerDeck.getCard(1).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
				while (dealerDeck.cardsValue()< 17 && playerDeck.cardsValue() < 22) {
				dealerDeck.draw(playingDeck);
				newGameTracker.cardsUsed++;
				JLabel drawnCard = new JLabel("");
				drawnCard.setBounds(300-(100*i), 40, 100, 145);
				drawnCard.setVisible(true);
				contentPane.add(drawnCard);
				drawnCard.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + dealerDeck.getCard(i + 2).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
				contentPane.setComponentZOrder(drawnCard, 1);
				if (dealerDeck.cardsValue() < 17) {
				}
				else {
					}
					i++;
				}//finished with the dealer shit
				dealerLabel.setText("Dealer: " + dealerDeck.cardsValue());
				System.out.println("Dealer: " + dealerDeck.cardsValue() + ". Player: " + playerDeck.cardsValue());
				if (playerDeck.cardsValue() > 21) {
					System.out.println("Dealer Win");
				}
				else if (dealerDeck.cardsValue() > 21 && playerDeck.cardsValue() < 22 ) {
					System.out.println("Player Win");
				}
				else if (dealerDeck.cardsValue() == playerDeck.cardsValue() && playerDeck.cardsValue() < 22) {
					System.out.println("Push");
				}
				else if (dealerDeck.cardsValue() > playerDeck.cardsValue() && dealerDeck.cardsValue() < 22) {
					System.out.println("Dealer Win");

				}				
				else if (playerDeck.cardsValue() > dealerDeck.cardsValue() && playerDeck.cardsValue() < 22) {
					System.out.println("Player Win");
				}
				else {
					System.out.println("ERROR IN COUNTING. WTF?");
				}
				newGameButton.setVisible(true);
			}
			
		//end end
			});
		standButton.setBounds(794, 461, 100, 25);
		contentPane.add(standButton);
		
		final JButton splitButton = new JButton("Split");
		splitButton.setBounds(640, 497, 100, 20);
		contentPane.add(splitButton);
		
		final JButton surrenderButton = new JButton("Surrender");
		surrenderButton.setBounds(840, 497, 100, 20);
		contentPane.add(surrenderButton);
		
		final JButton ddButton = new JButton("Double Down");
		ddButton.setBounds(740, 497, 100, 20);
		contentPane.add(ddButton);
		

		//--
		playerDeck.draw(playingDeck);
		playerDeck.draw(playingDeck);
		dealerDeck.draw(playingDeck);
		dealerDeck.draw(playingDeck);
		String dealerUpCard = (dealerDeck.getCard(0).toString()).substring(0,3);
		dealerLabel.setText("Dealer: " + dealerDeck.cardsValue());
		newGameTracker.cardsUsed = 4;
			try {
			Card1.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + playerDeck.getCard(0).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
			Card2.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + playerDeck.getCard(1).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			Card3.setIcon(new ImageIcon(new ImageIcon("src/cardImages/" + dealerDeck.getCard(0).toString().toLowerCase() + ".png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
			Card4.setIcon(new ImageIcon(new ImageIcon("src/cardImages/blank.png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
			}catch (Exception z) {
			}
			playerLabel.setText("Player: " + playerDeck.cardsValue());
			dealerLabel.setText("Dealer ");
			boolean isItAce = false;
			if (dealerUpCard.equalsIgnoreCase("ace")) {
				isItAce = true;
				if (playerDeck.cardsValue() == 21) {
					System.out.println("Even Money?");
					//input for yes
					
					//input for no
				}
				else {
					System.out.println("Insurance?");
					//input for yes
					if (dealerDeck.cardsValue() == 21) {
						//win insurance, lose bet
					}
					else{
						//lose insurance
					}
					//input for no
					if (dealerDeck.cardsValue() == 21) {
						//lose bet
					}
					else {
						//continue game
					}
				}

			}//end ace
			if (dealerDeck.cardsValue() == 21 && isItAce == false) {
				if (playerDeck.cardsValue() == 21) {
					System.out.println("Push");
				}
				else {
					System.out.println("DEALER BJ");
				}
			}
			else if (playerDeck.cardsValue() == 21) {
				if (dealerDeck.cardsValue() == 21) {
					System.out.println("PUSH");
				}
				else {
					System.out.println("DEALER BJ");
				}
			}		
			newGameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newGameButton.setVisible(false);
					Card1.setIcon(new ImageIcon(new ImageIcon("src/cardImages/blank.png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
					Card2.setIcon(new ImageIcon(new ImageIcon("src/cardImages/blank.png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
					Card3.setIcon(new ImageIcon(new ImageIcon("src/cardImages/blank.png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
					Card4.setIcon(new ImageIcon(new ImageIcon("src/cardImages/blank.png").getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
					hitButton.setVisible(true);
					dealerLabel.setText("Dealer: 0");
					playerLabel.setText("Player: 0");
					
					contentPane.removeAll();
					
					contentPane.add(playerLabel);
					contentPane.add(dealerLabel);
					
					contentPane.add(Card1);
					contentPane.add(Card2);
					contentPane.add(Card3);
					contentPane.add(Card4);

					contentPane.add(hitButton);
					contentPane.add(standButton);
					contentPane.add(splitButton);
					contentPane.add(ddButton);
					contentPane.add(surrenderButton);

					
					contentPane.repaint();
					
					playerDeck.moveAllToDeck(discardDeck);
					dealerDeck.moveAllToDeck(discardDeck);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			

				}
			});
	}
}
