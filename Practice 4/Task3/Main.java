
public class Main {
    public static void main(String[] args){
        System.out.printf("%4s","Таблица Кели");
        System.out.println();

        for (int row = 1; row <= 10; row++) {
            System.out.printf("%4d", row);
            
            for (int col = 1; col <= 10; col++) {
                int value = row * col;
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
    }
}