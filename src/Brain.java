import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.math.*;


public class Brain
{

    static double getYForX(double x, String func)
    {
        double result = 0;
        switch (func) {
            case "sin" : result = Math.sin(x); break;
            case "cos" : result = Math.cos(x); break;
            case "tg" : result = Math.tan(x); break;
            case "ctg" : result = Math.cos(x)/Math.sin(x); break;
        }
        return result;
    }

    static double getYFromXForEquation(double x, String equation) throws ScriptException
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        equation = equation.replace("sin", "Math.sin").
                replace("cos", "Math.cos").
                replace("tan", "Math.tan").
//                replace("sqrt", "Math.sqrt").
                replace("sqr", "Math.pow").
                replace("abs", "Math.abs").
                replace("e", "Math.E").
                replace("x", String.valueOf(x)).
                replace("log", "Math.log");

        double result = 0;

        if (!equation.isEmpty()) {
                result = (double)engine.eval(equation);
        }

        return result;
    }
}
