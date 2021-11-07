//package seedu.address.model.tuiton;
//
//import org.junit.jupiter.api.Test;
//import seedu.address.logic.commands.TimetableCommand;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
//import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
//import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;
//
//class TimetableTest {
//    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
//            new UserPrefs());
//
//    @Test
//    void showTimetable() {
//        try {
//            new TimetableCommand().execute(model);
//        } catch (CommandException e) {
//            e.printStackTrace();
//        }
//    }
//}
