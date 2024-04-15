import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> test1 = FileReader.readFile("Test1.txt");
        System.out.println(Runner.singleCarRun(test1));

        ArrayList<String> test2 = FileReader.readFile("Test2.txt");
        System.out.println(Runner.multiCarRun(test2));
    }
}