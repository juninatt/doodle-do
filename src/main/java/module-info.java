module se.pbt.iths.shapesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.pbt.iths.doodledo to javafx.fxml;

    exports se.pbt.iths.doodledo;
    exports se.pbt.iths.doodledo.controller;
    opens se.pbt.iths.doodledo.controller to javafx.fxml;
    exports se.pbt.iths.doodledo.ui.views.canvas;
    exports se.pbt.iths.doodledo.models.shapes.nonpolygonal;
    exports se.pbt.iths.doodledo.models.shapes.polygonal;
    exports se.pbt.iths.doodledo.exceptions;
    exports se.pbt.iths.doodledo.factory;
    opens se.pbt.iths.doodledo.factory to javafx.fxml;
    exports se.pbt.iths.doodledo.controller.manager;
    opens se.pbt.iths.doodledo.controller.manager to javafx.fxml;
    exports se.pbt.iths.doodledo.models.shapes;
    exports se.pbt.iths.doodledo.commands;
    opens se.pbt.iths.doodledo.commands to javafx.fxml;
    exports se.pbt.iths.doodledo.interfaces;
    exports se.pbt.iths.doodledo.enums;
}