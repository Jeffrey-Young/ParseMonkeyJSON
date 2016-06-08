import java.util.ArrayList;

import org.json.JSONArray;

public class Subject {

  int             _id;
  int             _sex;  // 0 male 1 female
  String          _DOB;
  ArrayList<Scan> _scans;

  public Subject(int id, String sex, String DOB, JSONArray scans) {
    _id = id;
    if (sex.equalsIgnoreCase("M")) {
      _sex = 0;
    } else {
      _sex = 1;
    }
    _DOB = DOB;
    _scans = new ArrayList<Scan>();
    JSONArray currentScan = null;
    for (int i = 0; i < scans.length(); i++) {
      currentScan = scans.getJSONArray(i);
      _scans.add(new Scan(currentScan.getString(1), currentScan.getDouble(2),
          currentScan.getString(0), _DOB, i));
    }
  }

  public int getID() {
    return _id;
  }

  public int getSex() {
    return _sex;
  }

  public ArrayList<Scan> getScans() {
    return _scans;
  }
}
