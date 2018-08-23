import java.util.Scanner;

public class Stage2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1;
        str1 = in.nextLine();
        if(str1.equals("")){
            System.out.println("\n");
        }else if(str1.equals("/exit")){
            System.out.println("Bye!");
        }else{
            String[] temp = str1.split(" ");
            if(temp.length == 1){
                System.out.println(Integer.parseInt(temp[0]));
            }else{
                System.out.println(Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]));
            }
        }
    }

}
