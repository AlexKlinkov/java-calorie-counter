import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        StepTracker step = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            if (userInput == 1) {
                System.out.println("За какой месяц вы хотите ввести данные?");
                System.out.println("Введите номер соответствующего месяца");
                System.out.print("1-Январь: ");
                System.out.print("2-Февраль: ");
                System.out.println("3-Март");
                System.out.print("4-Апрель: ");
                System.out.print("5-Май: ");
                System.out.println("6-Июнь");
                System.out.print("7-Июль: ");
                System.out.print("8-Август: ");
                System.out.println("9-Сентябрь");
                System.out.print("10-Октябрь: ");
                System.out.print("11-Ноябрь: ");
                System.out.println("12-Декабрь");
                int monthChoose = scanner.nextInt();
                System.out.println("За какой день вы хотите ввести данные?");
                System.out.println("Введите число от 1 до 30");
                int dayChoose = scanner.nextInt();
                System.out.println("Введите количество пройденных шагов");
                int quantityPassedSteps = scanner.nextInt();
                step.saveQuantityStepsForDay(monthChoose, dayChoose, quantityPassedSteps);
                System.out.println("Введенные данные были сохранены");
                printMenu();
                userInput = scanner.nextInt();
            } else if (userInput == 2) {
                System.out.println("За какой месяц вы хотите получить статистику?");
                System.out.println("Введите номер соответствующего месяца");
                System.out.print("1-Январь: ");
                System.out.print("2-Февраль: ");
                System.out.println("3-Март");
                System.out.print("4-Апрель: ");
                System.out.print("5-Май: ");
                System.out.println("6-Июнь");
                System.out.print("7-Июль: ");
                System.out.print("8-Август: ");
                System.out.println("9-Сентябрь");
                System.out.print("10-Октябрь: ");
                System.out.print("11-Ноябрь: ");
                System.out.println("12-Декабрь");
                int monthChoose = scanner.nextInt();
                step.outPutStatistic(monthChoose);
                printMenu();
                userInput = scanner.nextInt();
            } else if (userInput == 3) {
                System.out.println("Сейчас цель по количеству шагов в день равняется: " + step.targetQuantitySteps);
                System.out.println("Введите новую цель по пройденным шагам за день");
                int newTargetSteps = scanner.nextInt();
                step.setNewTargetOfStepsForDay(newTargetSteps); // Устанавливаем новое значение целевой цели
                System.out.println("Новая цель сохранена и равняется: " + step.targetQuantitySteps + " шагов в день");
                printMenu();
                userInput = scanner.nextInt();
            } else if (userInput == 4) {
                System.out.println("Программа завершена");
                return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Ввести количество шагов за определённый день");
        System.out.println("2. Напечатать статистику за определённый месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выйти из приложения");
    }
}

