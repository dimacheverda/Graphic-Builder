import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.math.*;


public class Brain
{

    static double getYForX(double x)
    {
//        Double result = (x*x+Math.sqrt(x+3))/20;
//        double result = Math.tan(x);
//        double result = Math.atan(x);
//        double result = Math.cos(x);
        double result = Math.sin(x);
//        double result = Math.log(x);
//        double result = Math.log10(x);
        return result;
    }

    static double getYFromXForEquatation(double x, String equation)
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        equation = equation.replace("sin", "Math.sin").
                replace("cos", "Math.cos").
                replace("tan", "Math.tan").
                replace("sqrt", "Math.sqrt").
                replace("sqr", "Math.pow").
                replace("log", "Math.log");

        double result = 0;

        if (!equation.isEmpty()) {
            try {
                result = (double)engine.eval(equation);
            } catch (ScriptException e) {
                e.printStackTrace();
            } finally {
                System.out.println("calc error");
            }
        }

        return result;
    }
}
