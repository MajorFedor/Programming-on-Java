import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите целое число");
        int Value = scanner.nextInt();
        int reversValue = 0;

        while(Value != 0){
            int lastDigit = Value % 10;
            reversValue = reversValue * 10 + lastDigit;
            Value /= 10;
        }

        System.out.printf("Реверс числа: %010d", reversValue);

        scanner.close();
    }
}