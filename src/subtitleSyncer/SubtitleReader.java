package subtitleSyncer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SubtitleReader {

  String filePath;

  SubtitleReader(String filePath) {
    this.filePath = filePath;
  }

  String filePathReader() throws IOException {
    BufferedReader file = new BufferedReader(new FileReader(filePath));
    StringBuilder content = new StringBuilder();
    String str;
    while ((str = file.readLine()) != null) {
      content.append(str + "\n");
    }
    String stringOfContents = content.toString();
    file.close();
    return stringOfContents;
  }

}
