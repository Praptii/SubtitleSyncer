package subtitleSyncer;

import java.io.IOException;
import java.text.ParseException;

public class Base {
  public void process(String filePath, int adjustmentValue) throws IOException, ParseException {
    SubtitleReader sr = new SubtitleReader(filePath);
    String stringOfContents = sr.filePathReader();
    SubtitleParser pr = new SubtitleParser();
    SubtitleAdjuster sa = new SubtitleAdjuster(pr.parseData(stringOfContents), adjustmentValue);
    NewSubtitleGenerator ns = new NewSubtitleGenerator(sa.newContentGenerator(),filePath + "adjusted");
    ns.subtitlegenerator();
  }
}
