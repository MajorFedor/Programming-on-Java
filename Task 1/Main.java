import java.lang.Math;

public class Main {
    public static final double a = 9.6;
    public static final double b = 8.2;
    public static final double c = 2;
    public static final double k = 0.7;
    //константы для чисел

    public static void main(String[] args){
        double x = Math.log(a + Math.pow(c, 2)) + Math.pow(Math.sin(c / b), 2);
        double higher = c + Math.sqrt(c + a);
        double below =  c - Math.sqrt(Math.abs(c - b));
        double y = Math.exp(-k * c) * (higher / below);

        System.out.println("Значение x: " + x);
        System.out.println("Значение y: " + y);

    }
    
}


// у меня вылазит ошибку Couldnt find or load main class main не знаю как пофиксить впервые такое