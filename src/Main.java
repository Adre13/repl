import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static HashMap<String, String> var = new HashMap<>();


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while (!str.equals("/exit")) {
            if (str.equals("")) {
                System.out.println();
            } else if (str.startsWith("/")) {
                help(str);
            } else {
                if (var.containsKey(str.trim())) {
                    System.out.println(var.get(str));
                } else {
                    analyzing(str);
                }
            }
            str = in.nextLine();
        }
        System.out.println("Bye!");
    }

    private static void help(String str) {
        if (str.equals("/help")) {
            System.out.println("The program calculate your expression");
            System.out.println("It know how to addition and subtraction");
        } else {
            System.out.println("Unknown command");
        }
    }

    private static void variables(String[] inputMass) {
        if (inputMass[0].trim().contains(" ") || inputMass[1].trim().contains(" ")) { // have the left or right part " "
            System.out.println("Unknown variable");
        } else {
            var.put(inputMass[0].trim(), inputMass[1].trim());
        }

    }

    private static int calculate(String str) throws Exception {

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        Object result = engine.eval(str);
        return (int) result;
    }

    private static void analyzing(String input) {
        if (input.contains("=") && input.indexOf("=") == input.lastIndexOf("=")) {  // input includes only one "="
            String[] temp = input.split("=");
            temp[0] = temp[0].trim();                // remove " "
            temp[1] = temp[1].trim();
            if (!temp[0].matches("[a-zA-Z]+")) { //  is number in left part
                System.out.println("Invalid identifier");
            } else if (!temp[1].matches("-?\\d+(\\.\\d+)?")) { // is letters in right part
                if(var.containsKey(temp[1])) {
                    var.put(temp[0], var.get(temp[1]));
                }else if (temp[1].matches("[a-zA-Z]+") && !var.containsKey(temp[1])) { // the right part has letters and var does not contain this key
                    System.out.println("Unknown variable");
                }else {
                System.out.println("Invalid value");
                }
            } else {
                    variables(temp);
            }
        } else {
            if (input.indexOf("=") != input.lastIndexOf("=")) {
                System.out.println("Invalid assignment");
            } else {                                            // input does not include "="
                if (input.trim().matches("[a-zA-Z]+") && !var.containsKey(input.trim())){
                    System.out.println("Unknown variables");
                }else {
                    input = adaptation(input);
                    input = letterToNumber(input);
                    try {
                        System.out.println(calculate(input));
                    } catch (Exception e) {
                        System.out.println("Invalid expression");
                    }
                }
            }

        }
    }
    private static String adaptation(String input){
        while ((input.contains("--")) || input.contains("++") || input.contains("  ") || input.contains("+-") || input.contains("-+")){
            input = input.replace("--", "+");
            input = input.replace("++", "+");
            input = input.replace("  ", " ");
            input = input.replace("+-","-");
            input = input.replace("-+", "-");
        }
        return input;

    }
    private static String letterToNumber(String input){
        for (Map.Entry<String, String> entry : var.entrySet()){
            if (input.contains(entry.getKey())){
                input = input.replaceAll(entry.getKey(), entry.getValue());
            }
        }
        return input;
    }

}

