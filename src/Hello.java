import java.io.Console;

public class Hello{
    public static void main(String[] args) {
        Console cons = System.console();
        String name = cons.readLine("Name: ");

        System.out.printf("Hello \n%s\n",name);
    }
}