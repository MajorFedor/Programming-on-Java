import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ArrayList <Double> array = new ArrayList<Double>();

        Random random = new Random();

        System.out.println("Введите количество чисел");
        int amount = scanner.nextInt();
        
        System.out.println("Введите диапазон мин.: ");
        int minDiapazon = scanner.nextInt();

        System.out.println("Введите диапазон макс.: ");
        int maxDiapazon = scanner.nextInt();

        for ( int i = 0; i < amount; i++){
            double randomValue = minDiapazon + (maxDiapazon - minDiapazon) * random.nextDouble();
            array.add(randomValue);
        }

        double arithmetic = 0;
        for (int i = 0; i < array.size(); ++i){
            arithmetic += array.get(i);
        }

        double geometric = 1;
        for (int i = 1; i < array.size(); ++i){
            geometric *= array.get(i);
        }

        double arithmeticFin = arithmetic / array.size();
        
        double geometricFin = Math.pow(geometric, 1.0 / array.size());

        System.out.println("Средняя арифметическая: " + arithmeticFin);
        System.out.println("Средняя геометрическая: " + geometricFin);

        scanner.close();
    }

}
