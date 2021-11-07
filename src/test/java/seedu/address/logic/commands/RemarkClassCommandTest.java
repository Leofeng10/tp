package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showTuitionClassAtIndex;
import static seedu.address.testutil.TypicalClasses.getAddressBookWithTypicalClasses;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.TuitionClassBuilder;

class RemarkClassCommandTest {
    private static final String REMARK_STUB = "Some remark";
    private Model model = new ModelManager(getAddressBookWithTypicalClasses(), new UserPrefs());

    @Test
    public void execute_addRemarkClassUnfilteredList_success() {
        TuitionClass firstClass = model.getFilteredTuitionList().get(INDEX_FIRST.getZeroBased());
        TuitionClass editedClass = new TuitionClassBuilder(firstClass).withRemark(REMARK_STUB).build();

        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(INDEX_FIRST);

        String expectedMessage = String.format(RemarkClassCommand.MESSAGE_UPDATE_REMARK_SUCCESS, editedClass);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTuition(firstClass, editedClass);

        //assertCommandSuccess(remarkClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkClassUnfilteredList_success() {
        TuitionClass firstClass = model.getFilteredTuitionList().get(INDEX_FIRST.getZeroBased());
        TuitionClass editedClass = new TuitionClassBuilder(firstClass).withRemark("").build();

        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(INDEX_FIRST);

        String expectedMessage = String.format(RemarkClassCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedClass);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTuition(firstClass, editedClass);

        //assertCommandSuccess(remarkClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTuitionClassAtIndex(model, INDEX_FIRST);

        TuitionClass firstClass = model.getFilteredTuitionList().get(INDEX_FIRST.getZeroBased());
        TuitionClass editedClass = new TuitionClassBuilder(model.getFilteredTuitionList()
                .get(INDEX_FIRST.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(INDEX_FIRST);

        String expectedMessage = String.format(RemarkClassCommand.MESSAGE_UPDATE_REMARK_SUCCESS, editedClass);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTuition(firstClass, editedClass);

        //assertCommandSuccess(remarkClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClassIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionList().size() + 1);
        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(outOfBoundIndex);

        assertCommandFailure(remarkClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClassIndexFilteredList_failure() {
        showTuitionClassAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTuitionList().size());

        RemarkClassCommand remarkClassCommand = new RemarkClassCommand(outOfBoundIndex);
        // assertCommandFailure(remarkClassCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkClassCommand standardCommand = new RemarkClassCommand(INDEX_FIRST);
        // same values -> returns true
        RemarkClassCommand commandWithSameValues = new RemarkClassCommand(INDEX_FIRST);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkClassCommand(INDEX_SECOND)));

        // different remark -> returns false
        //assertFalse(standardCommand.equals(new RemarkClassCommand(INDEX_FIRST)));
    }
}
