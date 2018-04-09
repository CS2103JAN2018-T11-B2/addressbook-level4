package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Undo the previous {@code UndoableCommand}.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String COMMAND_ALIAS = "u";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute() throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (!undoRedoStack.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        UndoableCommand command = undoRedoStack.popUndo();
        if (command instanceof AddCellCommand) {
            String cellAddress = ((AddCellCommand) command).getCellAddress();
            Person prisoner = ((AddCellCommand) command).getPrisonerToAdd();
            model.deletePrisonerFromCellFromUndo(prisoner, cellAddress);
        }
        if (command instanceof DeleteCommand) {
            Person prisoner = ((DeleteCommand) command).getPersonToDelete();
            if (prisoner.getIsInCell()) {
                String cellAddress = prisoner.getCellAddress().toString();
                model.addPrisonerToCellFromUndo(prisoner, cellAddress);
            }
        }
        command.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public void setData(Model model, CommandHistory commandHistory, UndoRedoStack undoRedoStack) {
        this.model = model;
        this.undoRedoStack = undoRedoStack;
    }
}
