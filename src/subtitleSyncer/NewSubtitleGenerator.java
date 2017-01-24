package subtitleSyncer;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class NewSubtitleGenerator {
  String newContent;
  String newSubtitlePath;

  NewSubtitleGenerator(String newContent, String newSubtitlePath) {
    this.newContent = newContent;
    this.newSubtitlePath = newSubtitlePath;
  }

  void subtitlegenerator() throws FileNotFoundException {
    PrintStream newSubtitleFile = new PrintStream(newSubtitlePath);
    newSubtitleFile.print(newContent);
    newSubtitleFile.close();
  }
}
