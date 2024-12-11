package org.example.laba111111.lab1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IntegralController {

    @FXML
    public TextField nThreads;
    @FXML
    public TextField n;
    @FXML
    public TextArea resultField;

    StringBuilder sb = new StringBuilder();

    private double a = Math.PI/6;
    private double b = Math.PI/4;
    private double totalResult;
    private int finished;



    public synchronized void send(double v) {
        totalResult += v;
        finished++;
        notify();
    }




    public void calculateButton(ActionEvent actionEvent) {
        int numThreads = Integer.parseInt(nThreads.getText());
        int N = Integer.parseInt(n.getText());

        totalResult = 0;
        finished = 0;
        double delta = (b - a) / numThreads;
        int nSteps = N / numThreads;

        long startTime = System.nanoTime();
        for (int i = 0; i < numThreads; i++) {
            RunnableIntegralCalculator calculator = new RunnableIntegralCalculator(  a + i * delta, a + i * delta + delta,
                    nSteps, t -> 1 / Math.pow(Math.sin(2 * t), 2) , this);
            new Thread(calculator).start();
        }
        try {
            synchronized (this) {
                while (finished < numThreads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
        long finishTime = System.nanoTime();


        sb.append("Number of threads: ").append(numThreads).append("\n")
                .append("Number of steps (n): ").append(N).append("\n")
                .append("Result: ").append(totalResult).append("\n")
                .append(String.format("Execution time: %.1f ms\n", (finishTime - startTime) / 1e6)).append("\n");
        resultField.setText(sb.toString());
    }

}