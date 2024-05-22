package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Adder extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Binary Adder and Error Checker");

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Input fields
        TextField inputA = new TextField();
        inputA.setPromptText("Enter A");

        TextField inputB = new TextField();
        inputB.setPromptText("Enter B");

        TextField inputM = new TextField();
        inputM.setPromptText("Enter m");

        //create error checkbox
        CheckBox errorCheckbox = new CheckBox();


        // Result display
        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setWrapText(true);

        // Error indicator
        Circle errorIndicator = new Circle(10);

        // Button to perform calculation
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> {
            try {
                int A = Integer.parseInt(inputA.getText());
                int B = Integer.parseInt(inputB.getText());
                int m = Integer.parseInt(inputM.getText());
                boolean setError = errorCheckbox.isSelected();

                StringBuilder results = new StringBuilder();

                int sum = add(A, B);
                results.append("Sum: ").append(sum).append("\n");
                results.append("Sum binary: ").append(toBinary(sum)).append("\n");

                int remainderAB = calculateModulo(sum, m);
                results.append("(A + B) mod m: ").append(remainderAB).append("\n");
                results.append("(A + B) mod m binary: ").append(toBinary(remainderAB)).append("\n");

                int C_A = calculateModulo(A, m);
                results.append("C(A): ").append(C_A).append("\n");
                results.append("C(A) binary: ").append(toBinary(C_A)).append("\n");

                int C_B = calculateModulo(B, m);
                results.append("C(B): ").append(C_B).append("\n");
                results.append("C(B) binary: ").append(toBinary(C_B)).append("\n");

                int CACBSum = add(C_A, C_B);
                results.append("C(A) + C(B): ").append(CACBSum).append("\n");
                results.append("C(A) + C(B) binary: ").append(toBinary(CACBSum)).append("\n");

                int remainderCACB = calculateModulo(CACBSum, m);
                results.append("C(A) + C(B) mod m: ").append(remainderCACB).append("\n");
                results.append("C(A) + C(B) mod m binary: ").append(toBinary(remainderCACB)).append("\n");

                //error injection (to change)
                if(setError){
                    remainderCACB = 9;
                }

                boolean isError = checkError(toBinary(remainderAB), toBinary(remainderCACB));
                results.append("Is error? : ").append(isError).append("\n");

                // Update the results area
                resultsArea.setText(results.toString());

                // Update the error indicator color
                if (isError) {
                    errorIndicator.setFill(Color.RED);
                } else {
                    errorIndicator.setFill(Color.GREEN);
                }

            } catch (NumberFormatException ex) {
                resultsArea.setText("Invalid input! Please enter valid integers.");
                errorIndicator.setFill(Color.RED);
            }
        });

        // Adding components to the layout
        root.getChildren().addAll(
                new Label("A:"), inputA,
                new Label("B:"), inputB,
                new Label("m:"), inputM,
                new Label("Set error: "), errorCheckbox,
                calculateButton,
                resultsArea,
                new Label("Error Indicator:"),
                errorIndicator
        );

        // Set the scene and show the stage
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int calculateModulo(int value, int modulo) {
        return value % modulo;
    }

    private static boolean checkError(String r1, String r2) {
        if (r1.length() != r2.length()) {
            return true;
        }

        for (int i = 0; i < r1.length(); i++) {
            if (r1.charAt(i) != r2.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    private static String toBinary(int n) {
        return Integer.toBinaryString(n);
    }
}
