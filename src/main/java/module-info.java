module org.example.laba111111 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.laba111111 to javafx.fxml;
    exports org.example.laba111111;


    opens org.example.laba111111.lab1 to javafx.fxml;
    exports org.example.laba111111.lab1;
}