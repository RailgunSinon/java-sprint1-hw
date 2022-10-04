public class Converter {
    private static final double  stepInMeters = 0.75;
    private static final double  stepInСalories = 50.0;
    private static final double  oneKiloCalory = 1000.0;

    public double stepToKilometers(int steps){
        return ((stepInMeters * steps)/1000);
    }

    public double stepToKiloCalories(int steps){
        return ((steps*stepInСalories)/oneKiloCalory);
    }

}
