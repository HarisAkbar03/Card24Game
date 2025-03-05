package org.example.card24game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

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

        Button verifyButton = new Button("Verify");
        verifyButton.setOnAction(e->validateEquation(equationField.getText()));
        generateCards();


        VBox layout = new VBox(20, cardBox,equationField,verifyButton);
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

            // Create a StackPane to wrap the image and apply CSS class for styling
            StackPane cardContainer = new StackPane(cardView);
            cardContainer.getStyleClass().add("card-container"); // Apply 'card-container' class

            cardBox.getChildren().add(cardContainer);
        }
    }
    private void validateEquation(String equation)
    {
        String[] cardStrings = new String[cardValues.length];
        for (int i =0; i <cardValues.length; i++)
        {
            cardStrings[i] = String.valueOf(cardValues[i]);
        }

    }
    public static void main(String[] args) {
        launch();
    }
}
