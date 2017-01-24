package subtitleSyncer;
import java.io.IOException;
import java.text.ParseException;

public class Base {
	public static void main (String args[]) throws IOException, ParseException{
		SubtitleReader sr = new SubtitleReader("adjusted A Very Long Engagement.srt");
		String stringOfContents = sr.filePathReader();
		SubtitleParser pr = new SubtitleParser();
		SubtitleAdjuster sa = new SubtitleAdjuster(pr.parseData(stringOfContents), -5);
		NewSubtitleGenerator ns = new NewSubtitleGenerator(sa.newContentGenerator(), "adjusted subtitle.srt");
		ns.subtitlegenerator();
	}
}
