package se.pbt.iths.shapesfx.ui.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InformationTextProvider {
    private static final StringProperty informationTextProperty = new SimpleStringProperty("");

    public static StringProperty getInformationTextProperty() {
        return informationTextProperty;
    }
}
