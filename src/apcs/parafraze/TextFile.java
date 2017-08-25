package apcs.parafraze;

public class TextFile extends BasicFile {
	public TextFile(String text) {
		setWordList(getWordList(text));
	}
}
