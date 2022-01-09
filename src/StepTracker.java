import java.util.HashMap;

public class StepTracker {
    int targetQuantitySteps = 10_000;
    Converter converter = new Converter(); // Объект, необходимый для получения значений полей класса

    StepTracker saveSteps;

    void saveQuantityStepsForDay(int monthChoose, int dayChoose, int quantityPassedSteps) {
        if (saveSteps == null) {
            // Если объект пуст, создаем новый, в который будем заносить данные о пройденных шагах
            StepTracker stepTracker = new StepTracker();
            MonthData monthData =
                    stepTracker.monthToData.get(monthChoose - 1); // Получил таблицу с днями и значениями за выбранный месяц
            monthData.monthDate.put(dayChoose, quantityPassedSteps); // Вставил новое значения о пройденных шагах
            stepTracker.monthToData.put(monthChoose - 1, monthData); // Объединили месяц, день и шаги
            saveSteps = stepTracker; // Сохранили новое значение в новый объект
        } else if (saveSteps != null) { // Усли объект не пуст, добавляем новые значения в уже созданный объект
            MonthData monthData =
                    saveSteps.monthToData.get(monthChoose - 1); // Получил таблицу с днями и значениями за выбранный месяц
            monthData.monthDate.put(dayChoose, quantityPassedSteps); // Втавил новое значения о пройденных шагах
            saveSteps.monthToData.put(monthChoose - 1, monthData); // Объединили месяц, день и шаги
        }
    }

    void outPutStatistic(int monthChoose) {
        int sumAllStepForMonth = 0; // Подсчитываем кол-во шагов за месяц
        int maxQuantityStepsForMonth = 0; // Максимальное количество шагов в день за месяц
        int countMaxDaysAchieveTarget = 0; // Мксимальное количество дней подряд перевыполнения цели по пройденным шагам в день
        if (saveSteps != null) { // Проверяем есть ли уже внесенные значения
            MonthData daySteps =
                    saveSteps.monthToData.get(monthChoose - 1); // Получаем информацию за месяц в виде дней и шагов
            for (int day : daySteps.monthDate.keySet()) {
                if (daySteps.monthDate.get(day) == null) {
                    // Если значения нет, прописываем 0, так как тип Intenger возвращает null
                    System.out.print(day + " день: " + 0 + ", ");
                } else {
                    System.out.print(day + " день: " + daySteps.monthDate.get(day) + ", ");
                }
            }
            for (int sum : daySteps.monthDate.values()) {
                sumAllStepForMonth += sum; // Суммируем все значения шагов за месяц
                if (maxQuantityStepsForMonth < sum) { // Определяем максимальное количество шагов за месяц
                    maxQuantityStepsForMonth = sum;
                }
            }

            // Подсчитываем максимальное кол-во дней подряд перевыполнения плана по пройденным шагам
            int count = 0;
            int intermediateValue = 0; // Сохраняем промежуточный результат
            while (count <= 30) {
                if (daySteps.monthDate.get(count) == null) {
                    daySteps.monthDate.put(count, 0); // Преобразовываем значение null в ноль
                }
                int getValue = daySteps.monthDate.get(count);
                if (getValue > targetQuantitySteps) { // Сравниваем значение за день с целевым показателем
                    countMaxDaysAchieveTarget = countMaxDaysAchieveTarget + 1; // Счетчик
                } else {
                    if (countMaxDaysAchieveTarget > intermediateValue) {
                        intermediateValue =
                                countMaxDaysAchieveTarget; // Записываем промежуточный результат,
                        // Вдруг дальше будет последовательность длинее
                        countMaxDaysAchieveTarget = 0; // Обнуляем, чтоб учесть длинну следующей последовательности
                    }
                }
                count = count + 1;
            }
            if (intermediateValue > countMaxDaysAchieveTarget) {
                countMaxDaysAchieveTarget =
                        intermediateValue; // Если промежуточный результат оказался самым большим,
                // Его значение и присваиваем нашему счетчику
            }

            System.out.println(); // Для переноса общего кол-во шагов на следующую строчку
            System.out.println("Общее количество шагов за месяц - " + sumAllStepForMonth);
            System.out.println("Максимальное количество шагов за месяц - " + maxQuantityStepsForMonth);
            System.out.println("Среднее количество шагов за месяц - " + (sumAllStepForMonth / 30));
            System.out.println("Пройденная дистанция за месяц " + (sumAllStepForMonth * converter.getOneStep()) + " - км.");
            System.out.println("За месяц вы сожгли " +
                    (sumAllStepForMonth * converter.getBurnKiloKcalForOneStep()) + " - килокалорий.");
            System.out.println("Максимальное количество подряд идущих дней, в течение которых " +
                    "количество шагов за день было выше целевого равно: " + countMaxDaysAchieveTarget);
        } else if (saveSteps == null) { // Если значения о шагах еще не вносились, создаем новый объект с нулями по умолчанию.
            StepTracker stepTracker = new StepTracker();
            MonthData daySteps = stepTracker.monthToData.get(monthChoose - 1);
            for (int day : daySteps.monthDate.keySet()) {
                System.out.print(day + " день: " + 0 + ", ");
            }
            System.out.println(); // Для переноса общего кол-во шагов на следующую строчку
            System.out.println("Общее количество шагов за месяц - " + sumAllStepForMonth);
            System.out.println("Максимальное количество шагов за месяц - " + maxQuantityStepsForMonth);
            System.out.println("Среднее количество шагов за месяц - " + (sumAllStepForMonth / 30));
            System.out.println("Пройденная дистанция за месяц " + (sumAllStepForMonth * converter.getOneStep()) + " - км.");
            System.out.println("За месяц вы сожгли " +
                    (sumAllStepForMonth * converter.getBurnKiloKcalForOneStep()) + " - килокалорий.");
            System.out.println("Максимальное количество подряд идущих дней, в течение которых " +
                    "количество шагов за день было выше целевого равно: " + countMaxDaysAchieveTarget);
        }
    }

    // Устанавливаем новое значение целивой цели
    public void setNewTargetOfStepsForDay(int targetQuantitySteps) {
        if (targetQuantitySteps >= 0) {
            this.targetQuantitySteps = targetQuantitySteps;
        }
    }

    HashMap<Integer, MonthData> monthToData = new HashMap<Integer, MonthData>();

    public StepTracker() {
        for (int i = 0; i < 12; i++) {
            monthToData.put(i, new MonthData());
        }
    }

    static class MonthData {
        // Заполните класс самостоятельно
        HashMap<Integer, Integer> monthDate = new HashMap<>();

        public MonthData() {
            for (int j = 1; j <= 30; j++) {
                monthDate.put(j, 0);
            }
        }
    }
}
