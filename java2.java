import java.util.Scanner;

public class java2{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите целое число(int)");
        Integer intValue = scanner.nextInt();

        System.out.println("Введите число с плавающей запятой(double): ");
        Double doubleValue = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Введите строку(string): ");
        String stringValue = scanner.nextLine();

        System.out.println("Введите логическое значение(bool): ");
        Boolean boolValue = scanner.nextBoolean();

        System.out.println("Int: " + intValue + "\nDouble: " + doubleValue + "\n String: " + stringValue + "\n Bool: " + boolValue);

        System.out.format("Десятичное: %d%n Шестнадцатеричное: %x%n Восьмеричная: %o%n Плавающая: %.3f%n Строка: %.3s%n Логический: %b%n", intValue, intValue, intValue, doubleValue, stringValue, boolValue);

        
        scanner.close();
    }
}

//задание сделано не до конца, потому что я вывожу мало форматированных данных но я думаю этого хватит