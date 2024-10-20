import java.io.Console;

public class Sum {
    public static void main(String[] args) {
        Console cons = System.console();
        String num1 = cons.readLine("Number 1: ");
        String num2 = cons.readLine("Number 2: ");
        System.out.println("6" + "6");
        System.out.printf("Total sum = %d\n",Integer.parseInt(num1) + Integer.parseInt(num2));
    }
}
