package se.pbt.iths.shapesfx.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import se.pbt.iths.shapesfx.commands.*;
import se.pbt.iths.shapesfx.controller.manager.CanvasManager;
import se.pbt.iths.shapesfx.enums.CommandType;
import se.pbt.iths.shapesfx.interfaces.CanvasCommand;
import se.pbt.iths.shapesfx.modelsmanagement.DrawnShapeStorage;
import se.pbt.iths.shapesfx.operations.ShapeSaver;
import se.pbt.iths.shapesfx.ui.config.AvailableShapesMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.DrawShapeMenuConfigurator;
import se.pbt.iths.shapesfx.ui.config.SelectMenuConfigurator;
import se.pbt.iths.shapesfx.ui.resources.AppMessages;
import se.pbt.iths.shapesfx.ui.utils.CommandTypeProvider;
import se.pbt.iths.shapesfx.ui.utils.InformationTextProvider;
import se.pbt.iths.shapesfx.ui.views.canvas.CanvasView;
import se.pbt.iths.shapesfx.utils.ShapeRotator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Supplier;


/**
 * Controller for the main window of the Shapes application.
 * Manages user interactions, shape operations, and command execution,
 * including undo/redo functionality and menu configuration.
 */
public class MainWindowController {

    // Final instance fields
    private final Stack<CanvasCommand> redoStack;
    private final Stack<CanvasCommand> undoStack;
    private final Map<CommandType, Supplier<CanvasCommand>> commands;
    private final ShapeSaver shapeSaver;
    private final ShapeRotator shapeRotator;

    // Non-final private instance fields
    private CanvasManager canvasManager;

    // @FXML generated fields
    @FXML
    private CanvasView canvasView;
    @FXML
    private Menu drawNewShapeMenu;
    @FXML
    private Menu drawnShapesMenu;
    @FXML
    private Menu selectMenu;
    @FXML
    private Label informationText;

    /**
     * Constructor that initializes shape manipulation and management utilities.
     * It prepares essential components such as rotation, saving, and undo/redo stacks,
     * enabling full control over shape interactions in the application's main window.
     */
    public MainWindowController() {
        shapeRotator = new ShapeRotator();
        redoStack = new Stack<>();
        commands = new HashMap<>();
        undoStack = new Stack<>();
        shapeSaver = new ShapeSaver();

    }


    /**
     * Initializes the ShapesController with its default values and required instances.
     * Binds the menu items and information text property, sets action events for items
     * and initializes command mapping.
     */
    public void initialize() {
        InitializeCanvas();
        initializeCommands();
        InitializeMenuBar();
        InitializeLabel();
    }

    /**
     * Initializes the canvas settings for the application.
     */
    private void InitializeCanvas() {
        VBox.setMargin(canvasView, new Insets(10, 10, 10, 10));
        canvasManager = new CanvasManager(canvasView);
    }

    /**
     * Initializes the command mapping for various shape operations.
     * The command type is subsequently set to empty as a default state.
     */
    private void initializeCommands() {
        commands.put(CommandType.DRAW, () -> new DrawShapeCommand(canvasManager));
        commands.put(CommandType.ROTATE, () -> new RotateShapeCommand(canvasManager, shapeRotator, 45.0));
        commands.put(CommandType.EDIT, () -> new EditShapeCommand(canvasManager));
        commands.put(CommandType.REMOVE, () -> new RemoveShapeCommand(canvasManager));
        commands.put(CommandType.SAVE, () -> new SaveShapeCommand(canvasManager, shapeSaver));
        CommandTypeProvider.setCommandType(CommandType.EMPTY);
    }

    /**
     * Initializes the menu bar, configuring selection, drawing, and available shapes menus.
     * Utilizes configurators to set up menu behavior and bind them to corresponding shape actions.
     */
    private void InitializeMenuBar() {
        new SelectMenuConfigurator(selectMenu).configure();
        new DrawShapeMenuConfigurator(drawNewShapeMenu).configure();
        new AvailableShapesMenuConfigurator(drawnShapesMenu, DrawnShapeStorage.getInstance()).configure();
    }

    /**
     * Set a welcome message to the label and bind it to {@link InformationTextProvider}.
     */
    private void InitializeLabel() {
        informationText.textProperty().bind(InformationTextProvider.getMessage());
        setInformationText(AppMessages.WELCOME);
    }


    /**
     * Handles the click event on the canvas. This method uses the current {@link CommandType}
     * to create a corresponding command object {@link CanvasCommand},
     * then delegates the handling of the click event to that command.
     * If a command is executed, it's pushed onto the undo stack, and the redo stack is cleared.
     *
     * @param event The MouseEvent representing the canvas click event.
     */
    @FXML
    private void handleCanvasClick(MouseEvent event) {
        CommandType actionType = CommandTypeProvider.getCommandType();
        CanvasCommand command = createCommand(actionType);
        if (command != null) {
            command.execute(event);
            undoStack.push(command);
            redoStack.clear();
        } else {
            setInformationText(AppMessages.DEFAULT_INSTRUCTION_MSG);
        }
    }

    /**
     * Creates and returns a {@link CanvasCommand} based on the specified {@link CommandType}.
     * Utilizes a pre-set mapping of command types to corresponding command suppliers.
     *
     * @param commandType The type of command to create.
     * @return The created command corresponding to the given type.
     * @throws IllegalArgumentException if the specified command type is not recognized.
     */
    private CanvasCommand createCommand(CommandType commandType) {
        var commandSupplier = commands.get(commandType);
        if (commandSupplier == null) {
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
        return commandSupplier.get();
    }


    // Utility and Miscellaneous Methods

    /**
     * Sets the text of the information text label.
     *
     * @param message The text message to set as information text
     */
    private void setInformationText(String message) {
        InformationTextProvider.setMessage(message);
    }

    /**
     * Reverts the last executed command if possible. If the undo stack is empty, an appropriate
     * message is displayed to the user.
     */
    public void undo() {
        if (undoStack.isEmpty())
            setInformationText(AppMessages.UNABLE_TO_UNDO_MSG);
        else {
            CanvasCommand lastCommand = undoStack.pop();
            lastCommand.undo();
            redoStack.push(lastCommand);
        }
    }

    /**
     * Reverts last undo action if possible.
     * If redo stack is empty, an appropriate message will be displayed to the user
     */
    public void redo() {
        if (redoStack.isEmpty())
            setInformationText(AppMessages.UNABLE_TO_REDO_MSG);
        else {
            CanvasCommand lastUndoneCommand = redoStack.pop();
            lastUndoneCommand.redo();
            undoStack.push(lastUndoneCommand);
        }
    }
}
