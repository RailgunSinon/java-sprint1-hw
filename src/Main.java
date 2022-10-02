import java.util.Scanner;

/*
Ивочкин Эмиль
Группа 16_1
Задача - счётчик калорий
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean cycleFlag = true;

        StepTracker stepTracker = new StepTracker();

        try{
            while(cycleFlag){
                printMenu();
                userInput = scanner.nextInt();
                switch (userInput){
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        System.out.println("Введите норму шагов на день:");
                        int inputValue = scanner.nextInt();

                        if(inputValue < 0) {
                            System.out.println("Введённое число является отрицательным. Пожалуйста, введите положительно число");
                        } else {
                            stepTracker.stepTarget = inputValue;
                            System.out.println("Новая норма шагов установлена: " + inputValue);
                        }
                        break;
                    case 4:
                        cycleFlag = false;
                        System.out.println("Выбран пункт выхода. Всего доброго!");
                        break;
                    default:
                        System.out.println("Выбранный пункт не обнаружен в меню. Пожалуйста, выберите другой пункт.");
                        break;
                }
            }
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
