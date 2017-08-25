package apcs.parafraze;

import java.io.IOException;
import java.util.ArrayList;

public class Manipulator {
	private BasicFile bFile;
	private int wordLength;
	private int percentage;

	public Manipulator(String entry, int wordL, int perc, boolean isHTML) throws IOException {
		if (isHTML) {
			// bFile = new HTMLFile(entry);
		} else {
			bFile = new TextFile(entry);
		}

		wordLength = wordL;
		percentage = perc;
	}

	public String revision() throws IOException {
		String newStr = "";
		
		@SuppressWarnings("unchecked")
		ArrayList<String> newList = (ArrayList<String>) bFile.getWordList().clone();
		for (String item : newList) {
			int chance = (int) (Math.random() * 100);
			if (isWord(item) && item.length() > wordLength && chance <= percentage) {
				try {
					item = BasicFile.replaceWord(item);
				} catch (NullPointerException e) {
				}
			}
			newStr += item;
		}
		return newStr;
	}

	public static boolean isWord(String word) {
		for (int i = 0; i < word.length() - 1; i++) {
			if (!word.substring(i, i + 1).matches("\\p{L}")) {
				return false;
			}
		}
		return true;
	}
}
