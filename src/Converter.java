public class Converter {
    private double oneStep = 75 / 100000.0; // 75 см, один шаг, 1 см это 1/100 000 км
    private double burnKiloKcalForOneStep = 50 / 1000.0; // 50 калорий сжигается за один шаг, 1 килокалория = 1 000
    // калорий.

    public double getOneStep() {
        return oneStep;
    }

    public double getBurnKiloKcalForOneStep() {
        return burnKiloKcalForOneStep;
    }
}
