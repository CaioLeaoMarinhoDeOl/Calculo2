import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        double acumulador = 0.0;
        int contador = 0;

        System.out.println("Digite a função a ser calculada (sem expoente de X):");
        String funcao = input.nextLine().toLowerCase();

        System.out.print("Digite o expoente de x: ");
        int expoente = input.nextInt();

        System.out.print("Digite o valor de [b]: ");
        int b = input.nextInt();

        System.out.print("Digite o valor de [a]: ");
        int a = input.nextInt();

        if (a < 0) {
            a = a * (-1);
        }

        else if (b < 0) {
            b = b * (-1);
        }

        System.out.print("Digite a quantidade de subintervalos: ");
        int n = input.nextInt();

        int delta = (b - a) / n;

        if (delta < 0) {
            delta = delta * (-1);
        }

        System.out.println("VALOR EM CADA SUBINTERVALO: ");
        for (int i = a; i <= b; i += delta) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            int x = (int) Math.pow(i, expoente);

            String funcaoEmX = funcao.replaceAll("x", String.valueOf(x));

            try {

                Object resultadoObject = engine.eval(funcaoEmX);
                double resultado = Double.parseDouble(resultadoObject.toString());
                if (contador > 0) {
                    acumulador = resultado + acumulador;
                    System.out.println(contador + "º (" + i + ") = " + resultado);
                }

            } catch (ScriptException e) {

                System.out.println("Erro ao calcular a função: " + e.getMessage());

            }
            contador++;
        }
        System.out.println("Area aproximada do gráfico: " + acumulador);
    }
}