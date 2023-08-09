module se.pbt.iths.shapesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.pbt.iths.shapesfx to javafx.fxml;

    exports se.pbt.iths.shapesfx;
    exports se.pbt.iths.shapesfx.controller;
    opens se.pbt.iths.shapesfx.controller to javafx.fxml;
    exports se.pbt.iths.shapesfx.ui.views.canvas;
    exports se.pbt.iths.shapesfx.models;
    exports se.pbt.iths.shapesfx.exceptions;
    exports se.pbt.iths.shapesfx.factory;
    opens se.pbt.iths.shapesfx.factory to javafx.fxml;
    exports se.pbt.iths.shapesfx.controller.manager;
    opens se.pbt.iths.shapesfx.controller.manager to javafx.fxml;
}