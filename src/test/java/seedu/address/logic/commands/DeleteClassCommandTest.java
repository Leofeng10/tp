package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalClasses.getAddressBookWithTypicalClasses;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;

public class DeleteClassCommandTest {

    private Model model = new ModelManager(getAddressBookWithTypicalClasses(), new UserPrefs());

    @Test
    public void equals() {
        DeleteClassCommand firstCommand = new DeleteClassCommand(List.of(INDEX_FIRST, INDEX_SECOND));
        DeleteClassCommand secondCommand = new DeleteClassCommand(List.of(INDEX_SECOND, INDEX_THIRD));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeleteCommand firstCommandCopy = new DeleteCommand(List.of(INDEX_FIRST));
        assertFalse(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different students -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DeleteClassCommand deleteCommand = new DeleteClassCommand(List.of(INDEX_FIRST));

        TuitionClass classToDelete = model.getFilteredTuitionList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_SUCCESS,
                List.of(classToDelete.getName() + "|" + classToDelete.getTimeslot()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTuition(classToDelete);

        //assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionList().size() + 1);
        DeleteClassCommand deleteCommand = new DeleteClassCommand(List.of(outOfBoundIndex));

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_FAILURE,
                List.of(outOfBoundIndex.getOneBased()));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }
}
