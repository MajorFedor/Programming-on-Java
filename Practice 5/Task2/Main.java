import java.lang.Math;
import java.util.Scanner;
//импортирование нужных библиотек

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);

        System.out.println("Введите значение для a: ");
        Double a = scanner.nextDouble();

        System.out.println("Введите значение для a: ");
        Double b = scanner.nextDouble();

        System.out.println("Введите значение для x: ");
        Double x = scanner.nextDouble();
        //получаем значение для каждого 

        if (x >= 0 && x < 1) {
            double result = Math.sin(x+1);
            System.out.println("Результат :" + result);
        } else if (x >= 1 && x < 7) {
            double result = a * Math.pow(x, 2) + 2*b -4;
            System.out.println("Результат :" + result);
        } else if (x == 7) {
            double result = Math.pow(a * x + b, -1);
            System.out.println("Результат :" + result);
        } else {
            System.out.println("x не попадает ни в один из интервалов");
        }
        //создаем структуру if-else для проверки х если в диапазоне выполняется математическая функция

        scanner.close();
    }
    
}
