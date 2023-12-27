module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

    opens com.example.demo.Models to  javafx.fxml;
    exports com.example.demo.Models;
}