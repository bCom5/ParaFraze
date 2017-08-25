package apcs.parafraze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImportBox extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1669304596368650964L;
	// instance variables - replace the example below with your own
	private JTextField entry;
	private JTextField name;
	private JLabel entryLabel;
	private JLabel nameLabel;
	private JDialog mainDialog;
	private JPanel entryPanel;
	private JPanel namePanel;
	private JPanel allPanel;
	private JButton submit;
	private GUI currentGui;
	private Scanner scanner;

	public ImportBox(GUI gui) {
		currentGui = gui;
		createDialog();
		addListener();
	}

	public JButton getSubmit() {
		return submit;
	}

	public String getName() {
		return name.getText();
	}

	public String getEntry() {
		return entry.getText();
	}

	public GUI getCurrentGui() {
		return currentGui;
	}

	public void nameSubmitButton(String n) {
		submit = new JButton(n);
	}

	public void createDialog() {
		entry = new JTextField();
		name = new JTextField();
		entryLabel = new JLabel("File Location");
		nameLabel = new JLabel("File Name");
		mainDialog = new JDialog();
		submit = new JButton("Import");
		allPanel = new JPanel();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		entryPanel = new JPanel(new BorderLayout());
		namePanel = new JPanel(new BorderLayout());
		mainDialog.setSize(300, 150);
		allPanel.setLayout(new FlowLayout());
		entryPanel.setPreferredSize(new Dimension(150, 20));
		namePanel.setPreferredSize(new Dimension(150, 20));
		entry.setText("C:\\");
		name.setText("file.txt");

		entryPanel.add(entry);
		namePanel.add(name);

		// mainPanel.setVisible(true);
		allPanel.add(nameLabel);
		allPanel.add(namePanel);
		allPanel.add(entryLabel);
		allPanel.add(entryPanel);
		mainDialog.add(allPanel);
		allPanel.add(submit);

		mainDialog.setVisible(true);

	}

	public void addListener() {
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String docName = name.getText();
				String locName = entry.getText();

				try {

					currentGui.setInput(text_To_String("/" + docName, locName));
				} catch (IOException err) {
					System.out.println("Error - Could not import file " + err.getMessage());
				}
			}
		});
	}

	public String text_To_String(String File_Name, String File_Location) throws IOException {
		scanner = new Scanner(new File(File_Location + File_Name));
		return scanner.useDelimiter("\\A").next();
	}
}
