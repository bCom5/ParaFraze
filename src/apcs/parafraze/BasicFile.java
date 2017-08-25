package apcs.parafraze;

import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.util.Elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public abstract class BasicFile {
	private ArrayList<String> wordList;

	public void setWordList(ArrayList<String> list) {
		wordList = list;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	private void parseLastChar(String word, ArrayList<String> oldList) {
		for (int i = oldList.size() - 1; i >= 0; i--) {
			if (!oldList.get(i).isEmpty()) {
				oldList.add(i + 1, word.substring(word.length() - 1, word.length()));
				int j = oldList.size() - 1;
				while (oldList.get(j).equals("")) {
					oldList.remove(j);
					j--;
				}
				return;
			}
		}
	}

	public static String replaceWord(String word) throws IOException {
		String addressPart1 = "http://www.thesaurus.com/browse/";
		String addressPart2 = "?s=t";
		String thesUrl = addressPart1 + word + addressPart2;
		String newWord = word;
		try {
			Document thes = Jsoup.connect(thesUrl).get();
			String desiredWordClass = "common-word";
			Elements words = (Elements) thes.getElementsByClass(desiredWordClass);
			newWord = ((Element) words).select(".text").first().html();
		} catch (IOException e) {
			return word;
		}

		if (Character.isUpperCase(word.charAt(0))) {
			newWord = newWord.substring(0, 1).toUpperCase() + newWord.substring(1);
		}
		return newWord;
	}

	public ArrayList<String> getWordList(String str) {
		ArrayList<String> wordlist = new ArrayList<>();
		int totalWords = 3 * numberSpaces(str) + numberPunctuation(str);
		for (int i = 0; i < totalWords; i++) {
			wordlist.add("");
		}
		int index = 0;
		int i = 0;
		while (i < str.length() - 1 && index < wordlist.size() + 1) {
			// wordlist.add(index, "");
			if (!str.substring(i, i + 1).matches("\\p{L}")) {
				index++;
				wordlist.set(index, str.substring(i, i + 1));
				index++;
			}
			// if (str.substring(i, i + 1).matches("\\p{L}")){
			else {
				wordlist.set(index, wordlist.get(index) + str.substring(i, i + 1));
			}
			i++;
		}
		parseLastChar(str, wordlist);
		return wordlist;
	}

	public int numberSpaces(String text) {
		int spaces = 0;

		for (int i = 0; i < text.length() - 1; i++) {
			if (text.substring(i, i + 1).equals(" ")) {
				spaces++;
			}
		}

		return spaces;
	}

	public int numberPunctuation(String text) {
		int punctuation = 0;

		for (int i = 0; i < text.length() - 1; i++) {
			if ((!text.substring(i, i + 1).matches("\\p{L}")) && (!text.substring(i, i + 1).equals(" "))) {
				punctuation++;
			}
		}

		return punctuation;
	}

	public String cleanStr(String s) {
		// removes spaces from end
		while ((s.indexOf(" ") != -1) && s.substring(s.length() - 1, s.length()).equals(" ")) {
			s = s.substring(0, s.length() - 1);
		}
		// removes spaces from beginning
		while ((s.indexOf(" ") != -1) && s.substring(0, 1).equals(" ")) {
			s = s.substring(1, s.length());
		}
		return s;
	}
}
