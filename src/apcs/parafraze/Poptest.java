package apcs.parafraze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Poptest extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1191716074794663474L;
	// instance variables - replace the example below with your own
	private JTextField min;
	private JTextField per;
	private JLabel minLabel;
	private JLabel perLabel;
	private JDialog mainDialog;
	private JPanel percentPanel;
	private JPanel numberWordPanel;
	private JPanel allPanel;
	private JButton submit;
	private GUI currentGui;

	public Poptest(GUI gui) {
		currentGui = gui;
		createDialog();
	}

	public void createDialog() {
		min = new JTextField();
		per = new JTextField();
		minLabel = new JLabel("Minimum word length");
		perLabel = new JLabel("Percentage of words changed");
		mainDialog = new JDialog();
		submit = new JButton("Save Settings");
		allPanel = new JPanel();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		numberWordPanel = new JPanel(new BorderLayout());
		percentPanel = new JPanel(new BorderLayout());
		mainDialog.setSize(300, 150);
		allPanel.setLayout(new FlowLayout());
		numberWordPanel.setPreferredSize(new Dimension(50, 20));
		percentPanel.setPreferredSize(new Dimension(50, 20));

		min.setText(currentGui.getMinWordLength() + "");
		per.setText(currentGui.getPercentage() + "");

		numberWordPanel.add(min);
		percentPanel.add(per);
		// mainPanel.setVisible(true);
		allPanel.add(minLabel);
		allPanel.add(numberWordPanel);
		allPanel.add(perLabel);
		allPanel.add(percentPanel);
		allPanel.add(submit);
		mainDialog.add(allPanel);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitSettings();
				currentGui.getVarDisplay().reset();
				setVisible(false);
				dispose();
				// exit
			}
		});

		mainDialog.setVisible(true);

	}

	public void submitSettings() {

		int minWordL = 0;
		int percentage = 0;
		try {
			String minNum = min.getText();
			String perc = per.getText();
			minWordL = Integer.parseInt(minNum);
			percentage = Integer.parseInt(perc);
			if (percentage > 100) {
				System.out.println("Please enter a valid percentage.");
			}
			if (minWordL == 0) {
				minWordL = 1;
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid numbers.");
		}

		try {
			currentGui.setMinWordLength(minWordL);
			currentGui.setPercentageWords(percentage);
		} catch (NullPointerException e) {
			System.out.println("Please enter valid numbers.");
		}
	}
}
