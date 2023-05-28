module ma.mgs.magasinapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;

    requires com.dlsc.formsfx;

    opens ma.mgs.magasinapplication to javafx.fxml;
    exports ma.mgs.magasinapplication;

    exports ma.mgs.magasinapplication.controller;
    opens ma.mgs.magasinapplication.controller to javafx.fxml;
    exports ma.mgs.magasinapplication.dao.Impl;
    opens ma.mgs.magasinapplication.dao.Impl to javafx.fxml;
    exports ma.mgs.magasinapplication.dao;
    opens ma.mgs.magasinapplication.dao to javafx.fxml;

    exports ma.mgs.magasinapplication.entities;
    opens ma.mgs.magasinapplication.entities to javafx.fxml;
}