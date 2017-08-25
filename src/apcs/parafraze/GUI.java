package apcs.parafraze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//import java.awt.GridBagLayout;
public class GUI extends JFrame {// implements ActionListener
	/**
	 * 
	 */
	private static final long serialVersionUID = 275097507772483778L;
	private JFrame mainFrame;
	private JPanel textPanelEntry;
	private JPanel textPanelResult;
	private JTextArea inputText;
	private JTextArea outputText;
	private JButton submitText;
	private boolean isHtml;
	private int minWordLength;
	private int percentageWords;

	private VarDisplay varDisplay;

	public GUI() throws IOException {
		isHtml = false;
		minWordLength = 5;
		percentageWords = 50;
		varDisplay = new VarDisplay(this);
		createGUI();

	}

	public boolean isHtml() {
		return isHtml;
	}

	public int getMinWordLength() {
		return minWordLength;
	}

	public int getPercentage() {
		return percentageWords;
	}

	public VarDisplay getVarDisplay() {
		return varDisplay;
	}

	public String getOutput() {
		return outputText.getText();
	}

	public void setInput(String in) {
		inputText.setText(in);
	}

	public void setHtmlOrText(boolean html) {
		isHtml = html;
	}

	public void setMinWordLength(int minL) {
		minWordLength = minL;
	}

	public void setPercentageWords(int percent) {
		percentageWords = percent;
	}

	public void createGUI() throws IOException {
		int width = 800;

		mainFrame = new JFrame("ParaFraze");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// textPanelEntry = new JPanel(new GridLayout(1,4,4,4));
		// textPanelResult = new JPanel(new GridLayout(1,4,4,4));

		textPanelEntry = new JPanel(new BorderLayout());
		textPanelResult = new JPanel(new BorderLayout());

		textPanelEntry.setPreferredSize(new Dimension(400, 150));
		textPanelResult.setPreferredSize(new Dimension(400, 150));

		inputText = new JTextArea(400, 150);
		outputText = new JTextArea(400, 150);
		submitText = new JButton("submit");
		JLabel enterHere = new JLabel("Input");
		JLabel readHere = new JLabel("Output");
		JScrollPane scrollPaneEntry = new JScrollPane(inputText);
		JScrollPane scrollPaneResult = new JScrollPane(outputText);

		outputText.setColumns(width / 20);
		inputText.setColumns(width / 20);
		inputText.setLineWrap(true);
		outputText.setLineWrap(true);

		inputText.setWrapStyleWord(true);
		outputText.setWrapStyleWord(true);
		// submitText.setSize(2,5);
		// outputText.setSize(width/2, length/2);

		mainFrame.setSize(800, 600);
		mainFrame.setLayout(new GridLayout(4, 2, 2, 2));
		// textPanelEntry.setSize( (int) (width * .75),(int) (length * .75));
		// textPanelEntry.setLayout(new GridLayout(4,2,2,2));

		// textPanelResult.setSize( (int) (width * .75),(int) (length * .75));
		// textPanelResult.setLayout(new GridLayout(4,2,2,2));

		// text panel code
		textPanelEntry.add(enterHere, BorderLayout.NORTH);
		// textPanelEntry.add(inputText);
		textPanelResult.add(readHere, BorderLayout.NORTH);
		// textPanelResult.add(outputText);

		// Scroll Pane Code
		textPanelEntry.add(scrollPaneEntry, BorderLayout.CENTER);
		textPanelResult.add(scrollPaneResult, BorderLayout.CENTER);

		mainFrame.getContentPane().add(textPanelEntry);

		mainFrame.getContentPane().add(textPanelResult);
		mainFrame.getContentPane().add(submitText);

		// invisible...
		varDisplay.setVisible(true);
		mainFrame.getContentPane().add(varDisplay);

		// Scroll Pane Code
		// scrollPaneEntry.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// scrollPaneResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// scrollPaneEntry.setPreferredSize(new Dimension(400, 150));
		// scrollPaneResult.setPreferredSize(new Dimension(250, 150));

		textPanelEntry.setVisible(true);
		textPanelResult.setVisible(true);
		mainFrame.setVisible(true);
		// scrollPaneEntry.setVisible(true);

		// menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem settingsAction = new JMenuItem("Settings");
		JMenuItem importAction = new JMenuItem("Import");
		JMenuItem exportAction = new JMenuItem("Export");
		ButtonGroup htmlOrText = new ButtonGroup();
		JCheckBoxMenuItem radioHtml = new JCheckBoxMenuItem("Parse URL");
		JCheckBoxMenuItem radioText = new JCheckBoxMenuItem("Parse Text");

		radioText.setSelected(true);

		mainFrame.setJMenuBar(menuBar);
		htmlOrText.add(radioHtml);
		htmlOrText.add(radioText);

		menuBar.add(fileMenu);

		fileMenu.add(settingsAction);
		fileMenu.add(importAction);
		fileMenu.add(exportAction);
		fileMenu.addSeparator();
		fileMenu.add(radioHtml);
		fileMenu.add(radioText);

		// String lines = yourTextArea.getText().split("\\n");

		// testing

		submitText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputText.setText("");
				String textFromInput = inputText.getText();
				Manipulator manipulator = null;
				try {
					manipulator = new Manipulator(textFromInput, minWordLength, percentageWords, isHtml);
				} catch (IOException err) {
					System.out.println("ERROR creating Manipulator object" + err.getMessage());
				}
				String textToAppend = "nullTextToAppend";
				try {
					textToAppend = manipulator.revision();
				} catch (IOException err) {
					System.out.println("ERROR retrieving revision" + err.getMessage());
				}
				outputText.append(textToAppend);
			}
		});

		settingsAction.addActionListener(new SettingsListener(this));
		/*
		 * settingsAction.addActionListener(new ActionListener(){ public void
		 * actionPerformed (ActionEvent e){ Poptest settings = new Poptest(this); } });
		 */
		importAction.addActionListener(new ImportListener(this));
		exportAction.addActionListener(new ExportListener(this));

		radioHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isHtml = true;
			}
		});

		radioText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isHtml = false;
			}
		});

	}

	/*
	 * public void actionPerformed(ActionEvent e){ if(e.getSource() == submitText){
	 * textPanelResult.append(new Manipulator(textPanelEntry.getText(),
	 * minWordLength, percentageOfWords, isHTML).revision()); } else
	 * if(e.getSource() == importAction){
	 * 
	 * } else if(e.getSource() == exportAction){
	 * 
	 * } else if(e.getSource() == radioHtml){
	 * 
	 * } else if(e.getSource() == radioText){
	 * 
	 * } }
	 * 
	 * outputText.setText(""); outputText.append(new
	 * Manipulator(inputText.getText(), minWordLength, percentageWords,
	 * isHtml).revision());
	 */
}
// ComponentOrientation.LEFT_TO_RIGHT), width/100, length/100