package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



class SortCommandTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());

    @Test
    public void execute_timeOrder_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sort(SortCommandParser.Order.TIME);
        String expectedMessage = String.format(SortCommand.MESSAGE_SORTED, "time");
        assertCommandSuccess(new SortCommand(SortCommandParser.Order.TIME), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ascendingOrder_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sort(SortCommandParser.Order.ASCENDING);
        String expectedMessage = String.format(SortCommand.MESSAGE_SORTED, "ascending");
        assertCommandSuccess(new SortCommand(SortCommandParser.Order.ASCENDING), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_descendingOrder_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sort(SortCommandParser.Order.ASCENDING);
        String expectedMessage = String.format(SortCommand.MESSAGE_SORTED, "descending");
        assertCommandSuccess(new SortCommand(SortCommandParser.Order.DESCENDING),
                model, expectedMessage, expectedModel);
    }

    @Test
    void testEquals() {
        SortCommand sortTime = new SortCommand(SortCommandParser.Order.TIME);
        SortCommand sortTimeCopy = new SortCommand(SortCommandParser.Order.TIME);
        SortCommand sortAscending = new SortCommand(SortCommandParser.Order.ASCENDING);
        //same object -> true
        assertTrue(sortAscending.equals(sortAscending));
        //same order -> true
        assertTrue(sortTime.equals(sortTimeCopy));
        //different order -> false
        assertFalse(sortAscending.equals(sortTime));
        //null -> false
        assertFalse(sortTime.equals(null));
        //different classes -> false
        assertFalse(sortAscending.equals(11));
    }
}
