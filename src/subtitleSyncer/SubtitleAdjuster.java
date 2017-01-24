package subtitleSyncer;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SubtitleAdjuster {
  HashMap<String, ArrayList<String>> fileContent;
  int                                adjuster;

  SubtitleAdjuster(HashMap<String, ArrayList<String>> fileContent, int adjuster) {
    this.fileContent = fileContent;
    this.adjuster = adjuster;
  }

  HashMap<String, ArrayList<String>> adjuster() throws ParseException {
    ArrayList<String> arrayOfTimeValues = new ArrayList<>();
    ArrayList<String> adjustedTime = new ArrayList<>();
    ArrayList<String> adjustedTimePair = new ArrayList<>();
    arrayOfTimeValues = fileContent.get("timeValue");
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SS");
    Calendar cal = Calendar.getInstance();
    for (String timeValuePair : arrayOfTimeValues) {
      for (String timeValue : timeValuePair.split(" --> ")) {
        Date parsedTimeValue = sdf.parse(timeValue);
        cal.setTime(parsedTimeValue);
        cal.add(Calendar.SECOND, adjuster);
        adjustedTime.add(sdf.format(cal.getTime()));
      }
    }
    for (int i = 0; i < adjustedTime.size(); i++) {
      String adjustedStartTime = null;
      String adjustedEndTime = null;
      adjustedStartTime = adjustedTime.get(i);
      adjustedEndTime = adjustedTime.get(i + 1);
      adjustedTimePair.add(String.join(" --> ", adjustedStartTime, adjustedEndTime));
      i++;
    }
    fileContent.put("timeValue", adjustedTimePair);
    return fileContent;
  }

  String newContentGenerator() throws ParseException {
    StringBuilder newContent = new StringBuilder();
    HashMap<String, ArrayList<String>> oldContent = adjuster();
    for (int i = 0; i < oldContent.get("index").size(); i++) {
      String index = oldContent.get("index").get(i);
      String timeValue = oldContent.get("timeValue").get(i);
      String dialogue = oldContent.get("dialogue").get(i);
      newContent.append(String.join("\r\n", index, timeValue, dialogue + "\n"));
    }
    return newContent.toString();
  }
}
