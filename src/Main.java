import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        while (!str1.equals("/exit")) {
            switch (str1){
                case "":
                    System.out.println();
                    break;
                case "/help":
                    help();
                    break;
                default:
                    System.out.println(calculate(str1));
            }
            str1 = in.nextLine();
        }
        System.out.println("Bye!");
    }

    private static void help(){
        System.out.println("The program calculate your expression");
        System.out.println("It know how to addition and subtraction");
    }

    private static int calculate(String str) throws Exception{
        while (str.contains("--") || str.contains("++") || str.contains("  ") || str.contains("+-") || str.contains("-+") ){
            str = str.replace("--", "+");
            str = str.replace("++", "+");
            str = str.replace("  ", " ");
            str = str.replace("+-","-");
            str = str.replace("-+", "-");
        }
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        Object result = engine.eval(str);
        return (int) result;
    }
}
