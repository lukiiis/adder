package org.example;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
        CheckBox errorCheckbox2 = new CheckBox();
        CheckBox errorCheckbox3 = new CheckBox();
        CheckBox errorCheckbox4 = new CheckBox();


        // Result display
        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setWrapText(true);

        // Error indicator
        Circle errorIndicator = new Circle(10);


        CheckBox[] checkboxes = {errorCheckbox, errorCheckbox2, errorCheckbox3, errorCheckbox4};

        // Add a common listener to each checkbox
        ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
            if (newValue) {
                for (CheckBox checkbox : checkboxes) {
                    if (checkbox != ((CheckBox) ((BooleanProperty) observable).getBean())) {
                        checkbox.setSelected(false);
                    }
                }
            }
        };

        for (CheckBox checkbox : checkboxes) {
            checkbox.selectedProperty().addListener(listener);
        }


        // Button to perform calculation
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> {
            try {
                int A = Integer.parseInt(inputA.getText());
                int B = Integer.parseInt(inputB.getText());
                int m = Integer.parseInt(inputM.getText());


                boolean setError = errorCheckbox.isSelected();
                boolean setError2 = errorCheckbox2.isSelected();
                boolean setError3 = errorCheckbox3.isSelected();
                boolean setError4 = errorCheckbox4.isSelected();

                StringBuilder results = new StringBuilder();


                assert AdderMethods.add(2, 3) == 5 : "Sum function is incorrect";
                assert AdderMethods.calculateModulo(10, 3) == 1 : "Modulo calculation is incorrect";
                assert AdderMethods.checkError("1010", "1011") : "Error check function is incorrect";

                results.append("Assertions:\n");
                results.append(" - Sum assertion: ").append((AdderMethods.add(2, 3) == 5) ? "Passed" : "Failed").append("\n");
                results.append(" - Modulo assertion: ").append((AdderMethods.calculateModulo(10, 3) == 1) ? "Passed" : "Failed").append("\n");
                results.append(" - Error check assertion: ").append((AdderMethods.checkError("1010", "1011")) ? "Passed" : "Failed").append("\n");

                int sum = AdderMethods.add(A, B);
                results.append("Sum: ").append(sum).append("\n");
                results.append("Sum binary: ").append(AdderMethods.toBinary(sum)).append("\n");

                // Error injection
                if(setError4) sum += 123;

                int remainderAB = AdderMethods.calculateModulo(sum, m);
                results.append("(A + B) mod m: ").append(remainderAB).append("\n");
                results.append("(A + B) mod m binary: ").append(AdderMethods.toBinary(remainderAB)).append("\n");

                int C_A = AdderMethods.calculateModulo(A, m);
                results.append("C(A): ").append(C_A).append("\n");
                results.append("C(A) binary: ").append(AdderMethods.toBinary(C_A)).append("\n");

                int C_B = AdderMethods.calculateModulo(B, m);
                results.append("C(B): ").append(C_B).append("\n");
                results.append("C(B) binary: ").append(AdderMethods.toBinary(C_B)).append("\n");

                int CACBSum = AdderMethods.add(C_A, C_B);
                results.append("C(A) + C(B): ").append(CACBSum).append("\n");
                results.append("C(A) + C(B) binary: ").append(AdderMethods.toBinary(CACBSum)).append("\n");

                // Error injection
                if(setError3) CACBSum += 9;

                int remainderCACB = AdderMethods.calculateModulo(CACBSum, m);
                results.append("C(A) + C(B) mod m: ").append(remainderCACB).append("\n");
                results.append("C(A) + C(B) mod m binary: ").append(AdderMethods.toBinary(remainderCACB)).append("\n");


                // Error injection
                if (setError) remainderAB += 9;
                else if (setError2) remainderCACB += 9;


                // Error message
                if(setError) results.append("Injected error in (A + B) mod m: ").append(remainderAB).append("\n");
                else if (setError2) results.append("Injected error in (C(A) + C(B)) mod m: ").append(remainderCACB).append("\n");
                else if (setError3) results.append("Injected error in the sum of C(A) + C(B): ").append(CACBSum).append("\n");
                else if (setError4) results.append("Injected error in the sum of A + B: ").append(sum).append("\n");



                boolean isError = AdderMethods.checkError(AdderMethods.toBinary(remainderAB), AdderMethods.toBinary(remainderCACB));
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
                new Label("Set error in (A + B) mod m: "), errorCheckbox,
                new Label("Set error in (C(A) + C(B)) mod m: "), errorCheckbox2,
                new Label("Set error in the sum of C(A) + C(B): "), errorCheckbox3,
                new Label("Set error in the sum of A + B: "), errorCheckbox4,
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


}
