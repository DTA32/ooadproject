module OOADProject {
    requires javafx.graphics;
    requires java.sql;
    requires javafx.controls;
    opens main;
    opens model;
    opens controller;
    opens view;
    opens view.auth;
    opens view.Admin.menu;
    opens view.Admin.pc;
    opens view.Admin.transaction;
    opens view.Admin.job;
    opens view.Admin.report;
    opens view.Admin.staff;
    opens view.customer.transaction;
    opens view.customer.pc;
    opens view.operator.menu;
    opens view.operator.pc;
    opens view.computer_technician;
    opens view.customer.report;
}