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
        boolean cycleFlag = true;

        StepTracker stepTracker = new StepTracker();

        try{
            while(cycleFlag){
                printMenu();
                userInput = scanner.next();
                userInputConverted = Integer.parseInt(userInput); //Ловлю конкретное исключение

                switch (userInputConverted){
                    case 1:
                        System.out.println("Обратите внимание, нумерация месяцев начинается с 0!");
                        System.out.println("Пожалуйста введите номер месяца, за который хотите записать статистику (0-11):"); // Это странно. Я бы просил юзера вводить нормально месяц, а сам бы уже минусовал его под массив. Но ТЗ есть ТЗ
                        userInput = scanner.next();
                        int curMonth = Integer.parseInt(userInput);
                        if (curMonth > 11 || curMonth < 0)
                        {
                            System.out.println("Число должно быть в диапазоне от 0 до 11!");
                            break;
                        }
                        System.out.println("Обратите внимание, нумерация дней начинается с 0!");
                        System.out.println("Пожалуйста введите номер дня, за который хотите записать статистику (0-29):");
                        userInput = scanner.next();
                        int curDay = Integer.parseInt(userInput);
                        if (curDay < 0 || curDay > 29 )
                        {
                            System.out.println("Число должно быть в диапазоне от 0 до 29!");
                            break;
                        }
                        System.out.println("Пожалуйста введите количество шагов, которые нужно записать:");
                        userInput = scanner.next();
                        int steps = Integer.parseInt(userInput);
                        if (steps < 0 || steps > Integer.MAX_VALUE )
                        {
                            System.out.println("Число или меньше 0, или слишком большое!");
                            break;
                        }
                        stepTracker.setStepsInConcreteDay(curMonth,curDay,steps);
                        System.out.println("Шаги были успешно записаны!");
                        break;
                    case 2:
                        System.out.println("Обратите внимание, нумерация месяцев начинается с 0!");
                        System.out.println("Пожалуйста введите номер месяца, за который хотите получить статистику (0-11):"); // Это странно. Я бы просил юзера вводить нормально месяц, а сам бы уже минусовал его под массив. Но ТЗ есть ТЗ
                        userInput = scanner.next();
                        int month = Integer.parseInt(userInput);
                        if (month > 11 || month < 0)
                        {
                            System.out.println("Число должно быть в диапазоне от 0 до 11!");
                            break;
                        }
                        stepTracker.getMonthStatistic(month);
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
                        cycleFlag = false;
                        System.out.println("Выбран пункт выхода. Всего доброго!");
                        break;
                        //Это тестовый пункт меню для отладки и проверки
                   /* case 5:
                        System.out.println("ТЕСТОВАЯ ФУНКЦИЯ");
                        System.out.println("РАНДОМНО ЗАПОЛНЯЮ ШАГАМИ ВСЕ МЕСЯЦЫ");
                        stepTracker.testMethodAddRandomStepsToAll();
                        break;*/
                    default:
                        System.out.println("Выбранный пункт не обнаружен в меню. Пожалуйста, выберите другой пункт.");
                        break;
                }

            }
        } catch (NumberFormatException exception) {
            System.out.println("Введённые данные не являются числом! Программа завершает работу с ошибкой!");
        } catch (Exception exception){
            System.out.println("Обнаружена необработанная ошибка!");
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
}
