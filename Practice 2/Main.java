import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("байт размер: " + Byte.SIZE + " минимум:" + Byte.MIN_VALUE + " максимум: " + Byte.MAX_VALUE);
        System.out.println("шорт размер: " + Short.SIZE + " минимум:" + Short.MIN_VALUE + " максимум: " + Short.MAX_VALUE);
        System.out.println("инт размер: " + Integer.SIZE + " минимум:" + Integer.MIN_VALUE + " максимум: " + Integer.MAX_VALUE);
        System.out.println("лонг размер: " + Long.SIZE + " минимум:" + Long.MIN_VALUE + " максимум: " + Long.MAX_VALUE);
        System.out.println("флоат размер: " + Float.SIZE + " минимум:" + Float.MIN_VALUE + " максимум: " + Float.MAX_VALUE);
        System.out.println("дабл размер: " + Double.SIZE + " минимум:" + Double.MIN_VALUE + " максимум: " + Double.MAX_VALUE);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение для byte: ");
        String byteInput = scanner.nextLine();
        byte byteValue = Byte.parseByte(byteInput);
        System.out.println("Ввееденное значение " + byteValue);

        System.out.println("Введите значение для шорта: ");
        String shortInput = scanner.nextLine();
        short shortValue = Short.parseShort(shortInput);
        System.out.println("Введенное значение шорта: " + shortValue);

        System.out.println("Введите значение для int: ");
        String intInput = scanner.nextLine();
        int intValue = Integer.parseInt(intInput);
        System.out.println("Введенное значение int'a: " + intValue);

        System.out.println("Введите значение для лонга: ");
        String longInput = scanner.nextLine();
        long longValue = Long.parseLong(longInput);
        System.out.println("Введенное значение лонга: " + longValue);

        System.out.println("Введите значение для float'a: ");
        String floatInput = scanner.nextLine();
        float floatValue = Float.parseFloat(floatInput);
        System.out.println("Введенное значение float'a: " + floatValue);

        System.out.println("Введите значение для double'a : ");
        String doubleInput = scanner.nextLine();
        double doubleValue = Double.parseDouble(doubleInput);
        System.out.println("Введенное значение для double'a: " + doubleValue);


    }
}
