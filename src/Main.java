import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        while (!str1.equals("/exit")) {
            if(str1.equals("")){
                System.out.println();
            }else if(str1.startsWith("/")){
                help(str1);
            }else {
                try {
                    System.out.println(calculate(str1));
                }catch (Exception e){
                    System.out.println("Invalid expression");
                }
            }
            str1 = in.nextLine();
        }
        System.out.println("Bye!");
    }

    private static void help(String str){
        if(str.equals("/help")) {
            System.out.println("The program calculate your expression");
            System.out.println("It know how to addition and subtraction");
        }else{
            System.out.println("Unknown command");
        }
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
