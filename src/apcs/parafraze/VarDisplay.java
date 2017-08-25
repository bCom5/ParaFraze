package apcs.parafraze;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VarDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3323813391929508186L;
	private GUI currentGui;
	private JTextArea showHtml;
	private JTextArea showMin;
	private JTextArea showPerc;

	public VarDisplay(GUI gui) {
		currentGui = gui;
		createPanel();
		reset();
	}

	public void createPanel() {
		JPanel display = new JPanel(new BorderLayout());
		display.setSize(800, 100);

		showHtml = new JTextArea();
		showMin = new JTextArea();
		showPerc = new JTextArea();

		JPanel displayHtml = new JPanel();
		JPanel displayMin = new JPanel();
		JPanel displayPerc = new JPanel();

		JLabel labelHtml = new JLabel("Input mode:");
		JLabel labelMin = new JLabel("Minimum word length:");
		JLabel labelPerc = new JLabel("Percentage changed:");

		showHtml.setEditable(false);
		showMin.setEditable(false);
		showPerc.setEditable(false);

		displayHtml.add(showHtml);
		displayMin.add(showMin);
		displayPerc.add(showPerc);

		displayHtml.setSize(50, 10);
		displayMin.setSize(50, 10);
		displayPerc.setSize(50, 10);

		displayHtml.setVisible(true);
		displayMin.setVisible(true);
		displayPerc.setVisible(true);

		display.add(labelHtml);
		display.add(displayHtml);
		display.add(labelMin);
		display.add(displayMin);
		display.add(labelPerc);
		display.add(displayPerc);

		display.setVisible(true);
	}

	public void reset() {
		String htmlOrText = "null";
		if (currentGui.isHtml()) {
			htmlOrText = "URL";
		} else {
			htmlOrText = "Text";
		}

		showHtml.setText(htmlOrText);
		showMin.setText(currentGui.getMinWordLength() + "");
		showPerc.setText(currentGui.getPercentage() + "");

	}
}
