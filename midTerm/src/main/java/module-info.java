module com.georgiancollege.test1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.georgiancollege.test1 to javafx.fxml;
    exports com.georgiancollege.test1;
}