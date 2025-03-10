package org.example.card24game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloApplication extends Application {

    private HBox cardBox;
    private final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
    private int[] cardValues = new int[4];

    @Override
    public void start(Stage stage) {
        cardBox = new HBox(10);
        cardBox.getStyleClass().add("card-box");

        TextField equationField = new TextField();
        equationField.setPromptText("Enter an equation");
        equationField.getStyleClass().add("text-field");

        TextField solutionField = new TextField();
        solutionField.setPromptText("AI Solution");
        solutionField.setEditable(false);
        solutionField.setId("solutionField");

        Button verifyButton = new Button("Verify");
        verifyButton.getStyleClass().add("button");
        verifyButton.setOnAction(e -> validateEquation(equationField.getText()));

        Button refreshButton = new Button("Refresh");
        refreshButton.getStyleClass().add("button");
        refreshButton.setOnAction(e -> generateCards());

        Button findSolutionButton = new Button("Find Solution");
        findSolutionButton.getStyleClass().add("button");
        findSolutionButton.setOnAction(e -> generateHint(solutionField));

        generateCards();

        VBox topLayout = new VBox(20, findSolutionButton, solutionField);
        topLayout.getStyleClass().add("button-layout");

        HBox buttonLayout = new HBox(10, verifyButton, refreshButton);
        buttonLayout.getStyleClass().add("action-layout");

        VBox layout = new VBox(20, topLayout, cardBox, equationField, buttonLayout);
        layout.getStyleClass().add("layout");

        Scene scene = new Scene(layout, 1080, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Card24Game");
        stage.show();
    }

    private void generateCards() {
        Random random = new Random();
        cardBox.getChildren().clear();

        for (int i = 0; i < 4; i++) {
            int cardValue = random.nextInt(13) + 1;
            cardValues[i] = cardValue;
            String suit = suits[random.nextInt(suits.length)];

            String cardName = switch (cardValue) {
                case 1 -> "ace";
                case 11 -> "jack";
                case 12 -> "queen";
                case 13 -> "king";
                default -> String.valueOf(cardValue);
            };

            String imagePath = "/cards/" + cardName + "_of_" + suit + ".png";
            Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            ImageView cardView = new ImageView(cardImage);

            StackPane cardContainer = new StackPane(cardView);
            cardContainer.getStyleClass().add("card-container");

            cardBox.getChildren().add(cardContainer);
        }
    }

    private void validateEquation(String equation) {
        List<Integer> usedNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(equation);

        while (matcher.find()) {
            usedNumbers.add(Integer.parseInt(matcher.group()));
        }

        Collections.sort(usedNumbers);
        int[] sortedCardValues = Arrays.copyOf(cardValues, cardValues.length);
        Arrays.sort(sortedCardValues);

        if (!areCardValuesValid(usedNumbers, sortedCardValues)) {
            showAlert("Invalid Equation", "You must use all four card values exactly once.");
            return;
        }

        try {
            double result = evaluateExpression(equation);

            if (result == 24) {
                showAlert("Success!", "The equation is valid and evaluates to 24!");
            } else {
                showAlert("Invalid Equation", "The equation does not evaluate to 24. Try again!");
            }
        } catch (Exception e) {
            showAlert("Invalid Equation", "There was an error evaluating the equation.");
        }
    }

    private boolean areCardValuesValid(List<Integer> usedNumbers, int[] sortedCardValues) {
        Collections.sort(usedNumbers);
        return usedNumbers.equals(Arrays.asList(Arrays.stream(sortedCardValues).boxed().toArray(Integer[]::new)));
    }

    private double evaluateExpression(String expression) {
        try {
            expression = expression.replaceAll("\\s+", "");
            if (!expression.matches("[0-9+\\-*/().]*")) {
                throw new IllegalArgumentException("Invalid characters in expression");
            }
            return evaluateBasicExpression(expression);
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + expression);
            return Double.NaN;
        }
    }

    private double evaluateBasicExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (Character.isDigit(current)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                values.push(Double.parseDouble(num.toString()));
                i--;
            } else if (current == '(') {
                operators.push(current);
            } else if (current == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                while (!operators.isEmpty() && precedence(current) <= precedence(operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(current);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new UnsupportedOperationException("Operator not supported");
        }
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            default: return -1;
        }
    }

    private void generateHint(TextField solutionField) {
        try {
            String hint = OpenAIClient.getSolutionFromOpenAI(cardValues);
            solutionField.setText(hint);
        } catch (IOException e) {
            solutionField.setText("Error: Unable to fetch solution. Attempting brute force...");
            e.printStackTrace();

            String bruteForceSolution = findBruteForceSolution(cardValues);
            if (bruteForceSolution != null) {
                solutionField.setText(bruteForceSolution);
            } else {
                solutionField.setText("No valid solution found.");
            }
        }
    }
    private String findBruteForceSolution(int[] cardValues) {
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        List<String> allPermutations = getPermutations(cardValues);

        // Try all permutations of the cards and apply all possible combinations of operators and parentheses
        for (String perm : allPermutations) {
            for (String op1 : operators) {
                for (String op2 : operators) {
                    for (String op3 : operators) {
                        // Generate all parenthetical combinations to handle operator precedence
                        String[] cards = perm.split(" ");

                        // Try each possible arrangement of parentheses
                        String[] expressions = {
                                cards[0] + " " + op1 + " " + cards[1] + " " + op2 + " " + cards[2] + " " + op3 + " " + cards[3],  // ((a op b) op c) op d
                                "(" + cards[0] + " " + op1 + " " + cards[1] + ") " + op2 + " (" + cards[2] + " " + op3 + " " + cards[3] + ")",  // (a op b) op (c op d)
                                "(" + cards[0] + " " + op1 + " (" + cards[1] + " " + op2 + " " + cards[2] + ")) " + op3 + " " + cards[3],  // (a op (b op c)) op d
                                cards[0] + " " + op1 + " (" + cards[1] + " " + op2 + " (" + cards[2] + " " + op3 + " " + cards[3] + "))",  // a op (b op (c op d))
                                "(" + cards[0] + " " + op1 + " " + cards[1] + " " + op2 + " " + cards[2] + ") " + op3 + " " + cards[3]   // (a op b op c) op d
                        };

                        for (String expression : expressions) {
                            try {
                                double result = evaluateExpression(expression);
                                if (result == 24) {
                                    return expression + " = 24";
                                }
                            } catch (Exception e) {
                                // Catch any exceptions and continue with other combinations
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private List<String> getPermutations(int[] cardValues) {
        List<String> permutations = new ArrayList<>();
        // Get all permutations of the 4 cards
        permute(cardValues, 0, permutations);
        return permutations;
    }

    private void permute(int[] cardValues, int index, List<String> permutations) {
        if (index == cardValues.length - 1) {
            // Add the current permutation to the list
            StringBuilder perm = new StringBuilder();
            for (int i = 0; i < cardValues.length; i++) {
                perm.append(cardValues[i]);
                if (i < cardValues.length - 1) {
                    perm.append(" ");
                }
            }
            permutations.add(perm.toString());
        } else {
            for (int i = index; i < cardValues.length; i++) {
                // Swap the elements
                swap(cardValues, index, i);
                permute(cardValues, index + 1, permutations);
                // Swap back
                swap(cardValues, index, i);
            }
        }
    }

    private void swap(int[] cardValues, int i, int j) {
        int temp = cardValues[i];
        cardValues[i] = cardValues[j];
        cardValues[j] = temp;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}