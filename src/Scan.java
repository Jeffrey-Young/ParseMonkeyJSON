import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Scan {

  int    _ageAtScan;
  double _weightAtScan;
  String _month;
  int    _numberOfPriorExposures;

  public Scan(String dateOfScan, double weightAtScan, String month, String DOB,
      int numberOfPriorExposures) {
    LocalDate DOBDate = LocalDate.parse(DOB);
    LocalDate ScanDate = LocalDate.parse(dateOfScan);
    _ageAtScan = (int) ChronoUnit.DAYS.between(DOBDate, ScanDate);
    _month = month;
    _weightAtScan = weightAtScan;
    _numberOfPriorExposures = numberOfPriorExposures;
  }

  public int getAgeAtScan() {
    return _ageAtScan;
  }

  public double getWeightAtScan() {
    return _weightAtScan;
  }

  public String getMonth() {
    return _month;
  }

  public int getNumberOfPriorExposures() {
    return _numberOfPriorExposures;
  }
}
