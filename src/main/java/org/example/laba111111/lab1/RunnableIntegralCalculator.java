package org.example.potokilaba.lab1;

import org.example.potokilaba.Function;

import java.util.function.DoubleUnaryOperator;

public class RunnableIntegralCalculator implements Runnable{

    private final IntegralController integralController;
    private final Function function;

    public RunnableIntegralCalculator(double start, double end, int nSteps, DoubleUnaryOperator f, IntegralController integralController){
        function = new Function(start,end, nSteps, f);
        this.integralController = integralController;
    }

    @Override
    public void run() {
        double v = function.calculate();
        integralController.send(v);
    }
}
