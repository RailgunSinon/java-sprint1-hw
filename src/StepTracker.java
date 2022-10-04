import java.util.Random;

public class StepTracker {

  int stepTarget = 10000;
  private static final int daysInMonth = 30;
  private static final int monthsInYear = 12;

  MonthData[] monthToData = new MonthData[monthsInYear];;
  private Converter converter = new Converter();

  public StepTracker() {
    for (int i = 0; i < monthToData.length; i++) {
      monthToData[i] = new MonthData();
    }
  }

  public int getStepsForCurrentDay(int monthNumber, int day){
    return monthToData[monthNumber].monthSteps[day];
  }

  public void setStepsInConcreteDay(int monthNumber,int dayNumber,int steps){
    monthToData[monthNumber].monthSteps[dayNumber] = steps;
  }

  public int countAllMonthSteps(int[] array){
    int sum = 0;
    for(int i=0;i<array.length;i++){
      sum += array[i];
    }
    return sum;
  }

  public int foundMaxStepsInMonth(int[] array){
    int max = array[0];
    for(int i=0;i<array.length;i++){
      if(array[i] > max){
        max = array[i];
      }
    }
    return max;
  }

  public double countMonthlyAverage(int[] array){
    double sum = 0;
    for(int i=0;i<array.length;i++){
      sum += array[i];
    }
    return (sum/array.length);
  }

  public int bestSerieInMonth(int[] array){
    int serie = 0;
    int bestSerie = 0;

    for (int element:array) {
      if(element > stepTarget){
        serie++;
      } else {
        if(serie > bestSerie){
          bestSerie = serie;
        }
        serie = 0;
      }
    }
    //Исправлено. Я не учёл, что серия может закончится только с перебором всех элементов. А потому нужно либо в конце проверять ещё раз, я даже не подумал.
    if (serie != 0 && serie > bestSerie){
      bestSerie = serie;
    }

    return bestSerie;
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
