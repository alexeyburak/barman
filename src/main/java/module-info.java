module com.burak.barman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.burak.barman to javafx.fxml;
    exports com.burak.barman;
    exports com.burak.barman.controllers;
    opens com.burak.barman.controllers to javafx.fxml;
}