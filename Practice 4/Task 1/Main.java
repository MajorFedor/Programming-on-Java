import java.util.Scanner;
import java.lang.Math;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение х'a: ");
        Float x = scanner.nextFloat();

        Float a = 2.4f;
        Float b = 8.2f;

        if (x >= 1.3 && x < 3) {
            Float result = 9 / (a * x);
            System.out.printf("Результат: %.2f%n", result);
        } else if (x == 3); {
            double result = Math.sqrt(a * x * x + x + b);
            System.out.printf("Результат: %.2f%n", result);
        }
        
        scanner.close();
    }

}