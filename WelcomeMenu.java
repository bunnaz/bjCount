package bJCountAlpha;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

import logic.Tracking;

public class WelcomeMenu {

	private JFrame frame;
	private JButton StartButton;
	static JSpinner shoeSizeSpinner;
	static JSpinner unitSpinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeMenu window = new WelcomeMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 538, 77);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 5, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Units");
		frame.getContentPane().add(lblNewLabel);
		
		unitSpinner = new JSpinner();
		unitSpinner.setModel(new SpinnerNumberModel(new Double(1000), new Double(0), null, new Double(1)));
		frame.getContentPane().add(unitSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Shoe Size");
		frame.getContentPane().add(lblNewLabel_1);
		
		shoeSizeSpinner = new JSpinner();
		shoeSizeSpinner.setModel(new SpinnerNumberModel(8, 1, 12, 1));
		frame.getContentPane().add(shoeSizeSpinner);
		
		StartButton = new JButton("Start");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				Game newGameWindow = new Game();
				newGameWindow.setVisible(true);

			}
		});
		frame.getContentPane().add(StartButton);
	}

}
