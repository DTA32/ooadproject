module OOADProject {

    requires javafx.graphics;
    requires java.sql;
    requires javafx.controls;
    opens main;
    opens model;
    opens controller;
    opens view;

}