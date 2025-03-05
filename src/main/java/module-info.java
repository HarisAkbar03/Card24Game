module org.example.card24game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.card24game to javafx.fxml;
    exports org.example.card24game;
}