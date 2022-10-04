import java.util.Scanner;

/*
Ивочкин Эмиль
Группа 16_1
Задача - счётчик калорий
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        int userInputConverted = 0;

        StepTracker stepTracker = new StepTracker();

          while(true){ //Возможно, имеллось в виду сделать через метку
                printMenu();
                userInput = "";
                try{
                    userInput = scanner.next();
                    userInputConverted = Integer.parseInt(userInput); //Ловлю конкретное исключение
                    switch (userInputConverted){
                        case 1:
                            try{
                                getStepsForCurrentDayMenu(scanner,stepTracker,userInput);
                            } catch (NumberFormatException exception) {
                                System.out.println("Введённые данные не являются числом! Попробуйте ещё раз!");
                            }
                            break;
                        case 2:
                            try{
                                getDataForChosenMonth(scanner,stepTracker,userInput);
                            } catch (NumberFormatException exception) {
                                System.out.println("Введённые данные не являются числом! Попробуйте ещё раз!");
                            }
                            break;
                        case 3:
                            System.out.println("Введите норму шагов на день:");
                            int inputValue = scanner.nextInt();

                            if(inputValue < 0 || inputValue > Integer.MAX_VALUE) {
                                System.out.println("Введённое число является отрицательным или слишком большим. Операция была завершена без сохранения.");
                            } else {
                                stepTracker.stepTarget = inputValue;
                                System.out.println("Новая норма шагов установлена: " + inputValue);
                            }
                            break;
                        case 4:
                            System.out.println("Выбран пункт выхода. Всего доброго!");
                            return;
                        default:
                            System.out.println("Выбранный пункт не обнаружен в меню. Пожалуйста, выберите другой пункт.");
                            break;
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Введённые данные не являются числом! Попробуйте ещё раз!");
                }

            }

    }

    private static void printMenu(){
        System.out.println("-----Меню------");
        System.out.println("1. Ввести количество шагов за определённый день.");
        System.out.println("2. Напечатать статистику за определённый месяц.");
        System.out.println("3. Изменить цель по количеству шагов в день.");
        System.out.println("4. Выйти из приложения.");
        System.out.println("Выберите пункт меню и введите его ниже: ");
    }

    private static void getStepsForCurrentDayMenu(Scanner scanner, StepTracker stepTracker,String userInput){
        System.out.println("Обратите внимание, нумерация месяцев начинается с 0!");
        System.out.println("Пожалуйста введите номер месяца, за который хотите записать статистику (0-11):"); // Это странно. Я бы просил юзера вводить нормально месяц, а сам бы уже минусовал его под массив. Но ТЗ есть ТЗ
        userInput = scanner.next();
        int curMonth = Integer.parseInt(userInput);
        if (curMonth > 11 || curMonth < 0)
        {
            System.out.println("Число должно быть в диапазоне от 0 до 11!");
            return;
        }
        System.out.println("Обратите внимание, нумерация дней начинается с 0!");
        System.out.println("Пожалуйста введите номер дня, за который хотите записать статистику (0-29):");
        userInput = scanner.next();
        int curDay = Integer.parseInt(userInput);
        if (curDay < 0 || curDay > 29 )
        {
            System.out.println("Число должно быть в диапазоне от 0 до 29!");
            return;
        }
        System.out.println("Пожалуйста введите количество шагов, которые нужно записать:");
        userInput = scanner.next();
        int steps = Integer.parseInt(userInput);
        if (steps < 0 || steps > Integer.MAX_VALUE )
        {
            System.out.println("Число или меньше 0, или слишком большое!");
            return;
        }
        stepTracker.setStepsInConcreteDay(curMonth,curDay,steps);
        System.out.println("Шаги были успешно записаны!");
    }

    private static void getDataForChosenMonth(Scanner scanner, StepTracker stepTracker,String userInput){
        Converter converter = new Converter();
        System.out.println("Обратите внимание, нумерация месяцев начинается с 0!");
        System.out.println("Пожалуйста введите номер месяца, за который хотите получить статистику (0-11):"); // Это странно. Я бы просил юзера вводить нормально месяц, а сам бы уже минусовал его под массив. Но ТЗ есть ТЗ
        userInput = scanner.next();
        int month = Integer.parseInt(userInput);
        if (month > 11 || month < 0)
        {
            System.out.println("Число должно быть в диапазоне от 0 до 11!");
            return;
        }
        System.out.println("Вывожу статистику за данный месяц " + month + ":");
        System.out.println("Статистика по дням:");
        for (int i=0;i<stepTracker.monthToData[month].monthSteps.length;i++){
            System.out.println("День " + i + ": " + stepTracker.getStepsForCurrentDay(month,i));
        }
        System.out.println("Всего за месяц было пройдено: " + stepTracker.countAllMonthSteps(stepTracker.monthToData[month].monthSteps));
        System.out.println("Максимально количество шагов за день в месяце: " + stepTracker.foundMaxStepsInMonth(stepTracker.monthToData[month].monthSteps));
        System.out.println("Среднее количество шагов за день в месяце: " + String.format("%.3f",stepTracker.countMonthlyAverage(stepTracker.monthToData[month].monthSteps)));
        System.out.println("За месяц вы прошли: " + String.format("%.3f", converter.stepToKilometers(stepTracker.countAllMonthSteps(stepTracker.monthToData[month].monthSteps))) + " км!");
        System.out.println("И при этом вы сожгли : " + String.format("%.3f", converter.stepToKiloCalories(stepTracker.countAllMonthSteps(stepTracker.monthToData[month].monthSteps))) + " килокалорий!");
        System.out.println("Лучшая серия дней подряд, в которые выполнена норма шагов " + stepTracker.stepTarget + " составляет: " + stepTracker.bestSerieInMonth(stepTracker.monthToData[month].monthSteps) + " дней!");
        System.out.println("Рассчёт статистики окончен.");
    }
}
