module com.marcos.jplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;
    requires javafx.media;


    opens com.marcos.jplayer to javafx.fxml;
    exports com.marcos.jplayer;
}