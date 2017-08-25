package apcs.parafraze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportListener implements ActionListener {
	GUI currentGui;

	public ImportListener(GUI gui) {
		currentGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		new ImportBox(currentGui);
	}
}