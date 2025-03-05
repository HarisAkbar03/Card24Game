package org.example.card24game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class HelloApplication extends Application {

    private HBox cardBox;
    private final String[] suits = {"clubs", "diamonds", "hearts", "spades"}; // Possible suits


    @Override
    public void start(Stage stage) throws IOException {
        cardBox = new HBox(10);
        cardBox.setStyle("-fx-alignment: center;");

        generatecards();

        VBox layout = new VBox(20,cardBox);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        stage.setScene(new Scene(layout,400,200));
        stage.setTitle("Card24Game");
        stage.show();
    }
    private void generatecards()
    {
        Random random = new Random();
        cardBox.getChildren().clear();

        for (int i =0; i <4; i++)
        {
            int cardValue = random.nextInt(13)+1;
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
            cardView.setFitWidth(80);
            cardView.setFitHeight(120);

            cardBox.getChildren().add(cardView);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

