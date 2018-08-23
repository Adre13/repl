import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1;
        str1 = in.nextLine();
        while (true) {
            if (str1.equals("")) {
                System.out.println("\n");
            } else if (str1.equals("/exit")) {
                System.out.println("Bye!");
                break;
            } else if (str1.equals("/help")) {
                System.out.println("The program calculate the sum of number");
            } else {
                String[] temp = str1.split(" ");
                int result = 0;
                for (int i = 0; i < temp.length; i++) {
                    result += Integer.parseInt(temp[i]);
                }
                System.out.println(result);
            }
            str1 = in.nextLine();
        }
    }

}
