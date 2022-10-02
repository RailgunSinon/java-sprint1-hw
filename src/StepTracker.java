public class StepTracker {

  int stepTarget = 10000;
  private int daysInMonth = 30;

  MonthData[] monthToData;

  public StepTracker() {
    monthToData = new MonthData[12];
    for (int i = 0; i < monthToData.length; i++) {
      monthToData[i] = new MonthData();
    }
  }

  class MonthData {
    // Заполните класс самостоятельно
    public int[] monthSteps = new int[daysInMonth];

    public MonthData(){
      for(int i=0;i<monthSteps.length;i++){
        monthSteps[i] = 0;
      }
    }
  }
}
