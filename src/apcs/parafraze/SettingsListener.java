package apcs.parafraze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsListener implements ActionListener {
	GUI currentGui;

	public SettingsListener(GUI gui) {
		currentGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		new Poptest(currentGui);
	}
}
