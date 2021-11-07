package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TimetableCommand.NO_CLASS;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.UniqueTuitionList;

class TimetableCommandTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());

    @Test
    void execute_emptyTuitionClass_success() {
        UniqueTuitionList.setMostRecentTuitionClasses();
        UniqueTuitionList.getMostRecentTuitionClasses();
        assertCommandSuccess(new TimetableCommand(), model, new CommandResult(NO_CLASS), model);
    }

    @Test
    void execute_nonEmptyTuitionClass_success() {
        Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
                new UserPrefs());
        assertCommandSuccess(new TimetableCommand(), model, new CommandResult("Timetable shown.",
                CommandResult.UiAction.SHOW_TIMETABLE), model);
    }

    @Test
    void testEquals() {
        TimetableCommand timetableCommand = new TimetableCommand();
        TimetableCommand timetableCommandCopy = new TimetableCommand();
        //same object -> true
        assertTrue(timetableCommand.equals(timetableCommand));
        //same class -> true
        assertTrue(timetableCommand.equals(timetableCommandCopy));
        //null -> false
        assertFalse(timetableCommand.equals(null));
        //different classes -> false
        assertFalse(timetableCommand.equals(11));
    }
}
