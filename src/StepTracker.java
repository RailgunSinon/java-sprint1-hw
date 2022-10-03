import java.util.Random;

public class StepTracker {

  int stepTarget = 10000;
  private int daysInMonth = 30;

  private MonthData[] monthToData;
  private Converter converter = new Converter();

  public StepTracker() {
    monthToData = new MonthData[12];
    for (int i = 0; i < monthToData.length; i++) {
      monthToData[i] = new MonthData();
    }
  }

  public void getMonthStatistic(int monthNumber){
    MonthData monthData = monthToData[monthNumber];
    System.out.println("Вывожу статистику за данный месяц " + monthNumber + ":");
    System.out.println("Статистика по дням:");
    for (int i=0;i<monthData.monthSteps.length;i++){
      System.out.println("День " + i + ": " + monthData.monthSteps[i]);
    }
    System.out.println("Всего за месяц было пройдено: " + countAllMonthSteps(monthData.monthSteps));
    System.out.println("Максимально количество шагов за день в месяце: " + foundMaxStepsInMonth(monthData.monthSteps));
    System.out.println("Среднее количество шагов за день в месяце: " + String.format("%.3f",countMonthlyAverage(monthData.monthSteps)));
    System.out.println("За месяц вы прошли: " + String.format("%.3f", converter.sterToKilometers(countAllMonthSteps(monthData.monthSteps))) + " км!");
    System.out.println("И при этом вы сожгли : " + String.format("%.3f", converter.stepToKiloCalories(countAllMonthSteps(monthData.monthSteps))) + " килокалорий!");
    System.out.println("Лучшая серия дней подряд, в которые выполнена норма шагов " + stepTarget + " составляет: " + bestSerieInMonth(monthData.monthSteps) + " дней!");
    System.out.println("Рассчёт статистики окончен.");
  }

  public void setStepsInConcreteDay(int monthNumber,int dayNumber,int steps){
    monthToData[monthNumber].monthSteps[dayNumber] = steps;
  }

  private int countAllMonthSteps(int[] array){
    int sum = 0;
    for(int i=0;i<array.length;i++){
      sum += array[i];
    }
    return sum;
  }

  private int foundMaxStepsInMonth(int[] array){
    int max = array[0];
    for(int i=0;i<array.length;i++){
      if(array[i] > max){
        max = array[i];
      }
    }
    return max;
  }

  private double countMonthlyAverage(int[] array){
    double sum = 0;
    for(int i=0;i<array.length;i++){
      sum += array[i];
    }
    return (sum/array.length);
  }

  private int bestSerieInMonth(int[] array){
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

  public void testMethodAddRandomStepsToAll(){
    Random random = new Random();
    for (MonthData element:monthToData
    ) {
      for(int i=0; i < element.monthSteps.length;i++){
          element.monthSteps[i] = random.nextInt(25000);
      }
    }
  }

}
