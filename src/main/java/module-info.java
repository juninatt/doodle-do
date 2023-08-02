module se.pbt.iths.shapesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.pbt.iths.shapesfx to javafx.fxml;

    exports se.pbt.iths.shapesfx;
    exports se.pbt.iths.shapesfx.controller;
    opens se.pbt.iths.shapesfx.controller to javafx.fxml;
    exports se.pbt.iths.shapesfx.view.canvas;
    exports se.pbt.iths.shapesfx.models;
    exports se.pbt.iths.shapesfx.exceptions;
}