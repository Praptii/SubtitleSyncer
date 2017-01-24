package subtitleSyncer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SubtitleParser {
  HashMap<String, ArrayList<String>> parseData(String stringOfContents) throws IOException {
    int i = 0;
    ArrayList<String> index = new ArrayList<>();
    ArrayList<String> timeValue = new ArrayList<>();
    ArrayList<String> dialogue = new ArrayList<>();
    HashMap<String, ArrayList<String>> arrayOfContents = new HashMap<>();
    String[] blocks = stringOfContents.split("\\r?\\n\\r?\\n");
    String[] lines = null;
    for (String block : blocks) {
      lines = block.split("\\r?\\n");
      for (String line : lines) {
        if (Arrays.toString(lines).indexOf(line) == 1) {
          index.add(line);
          arrayOfContents.put("index", index);
        } else if (line.matches("(.*)-->(.*)")) {
          timeValue.add(line);
          arrayOfContents.put("timeValue", timeValue);
        } else {
          if (lines.length == 3) {
            dialogue.add(line + "\r\n");
          } else {
            i = lines.length - 1;
            dialogue.add(line + "\r\n" + lines[i] + "\r\n");
            arrayOfContents.put("dialogue", dialogue);
            break;
          }
          arrayOfContents.put("dialogue", dialogue);
        }
      }
    }
    return arrayOfContents;
  }
}
