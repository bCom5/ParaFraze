package apcs.parafraze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ExportBox extends ImportBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3697072215641780259L;

	public ExportBox(GUI gui) {
		super(gui);
		nameSubmitButton("Export");
	}

	@Override
	public void addListener() {
		getSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					string_To_Text(getName(), getEntry(), getCurrentGui().getOutput());
				} catch (IOException err) {
					System.out.println("Error - Could not export file " + err.getMessage());
				}
			}
		});
	}

	public static void string_To_Text(String File_Name, String File_Location, String text) throws IOException {
		File file = new File(File_Location + File_Name);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println(text);
		printWriter.close();
	}
}
