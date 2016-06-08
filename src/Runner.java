import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class Runner {

  public static void main(String[] args) {

    String content = null;
    try {
      // read json
      content = new Scanner(new File("resources/data.json")).useDelimiter("\\Z").next();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // parse json
    JSONObject json = new JSONObject(content);
    ArrayList<JSONObject> subjectArrayJSON = new ArrayList<JSONObject>();
    for (int i = 0; i < json.length() + 2; i++) { // parse out subjects into
                                                  // array
      if (i < 9 && i != 2 && i != 8) { // indexes and
                                       // subjects are
                                       // offset by -1, 3
                                       // and 9 DNE and 15
                                       // and 24 have only
                                       // 1 scan and are
                                       // excluded from
                                       // DTI analysis
        subjectArrayJSON.add(json.getJSONObject("00" + (i + 1)));
        System.out.println("00" + (i + 1));
      } else if (i >= 9 && i != 14 && i != 23) {
        subjectArrayJSON.add(json.getJSONObject("0" + (i + 1)));
        System.out.println("0" + (i + 1));
      }
    }
    ArrayList<Subject> subjectArray = new ArrayList<Subject>();
    for (JSONObject subj : subjectArrayJSON) {
      subjectArray.add(new Subject(subj.getInt("subject"), subj.getString("sex"),
          subj.getString("DOB"), subj.getJSONArray("scans")));
    }

    // write CSV
    FileWriter file;
    try {
      file = new FileWriter(new File("Subject_Parameters_FADTTSter.csv"));
      file.append("Case Name,Sex,Age,Weight,Prior Exposures\n");
      String correctedID = ""; // needed to add leading zeros
      for (Subject subj : subjectArray) {
        if (subj.getID() < 10) {
          correctedID = "00" + subj.getID();
        } else {
          correctedID = "0" + subj.getID();
        }
        for (Scan scan : subj.getScans()) {
          file.append(correctedID + "_" + scan.getMonth() + "_DTI_antsBM.nrrd,"
              + subj.getSex() + "," + scan.getAgeAtScan() + "," + scan.getWeightAtScan()
              + "," + scan.getNumberOfPriorExposures() + "\n");
        }
      }
      file.flush();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
