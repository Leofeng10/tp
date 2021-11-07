package seedu.address.model.tuiton;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;


public class TimeslotTest {
    private final TuitionClass tuitionClass = new TuitionClass(new ClassName("CS2103"),
            new ClassLimit(10), Timeslot.parseString("Mon 14:00-16:00"), null, null);
    private final TuitionClass tuitionClass1 = new TuitionClass(new ClassName("CS2103"),
            new ClassLimit(10), Timeslot.parseString("Tue 14:00-16:00"), null, null);
    private final TuitionClass tuitionClass2 = new TuitionClass(new ClassName("CS2105"),
            new ClassLimit(10), Timeslot.parseString("Mon 17:00-19:00"), null, null);
    private List<TuitionClass> tuitionClasses = new ArrayList<>();

    @Test
    public void parseString() {
        assertTrue(Timeslot.parseString("Mon 14:00-16:00") != null); //correct format
        assertFalse(Timeslot.parseString("mon 14:00-16:00") == null); //M in "mon" is in lower case
        assertFalse(Timeslot.parseString("Mon 2pm-4pm") != null); //use "pm" instead of hh:mm-hh:mm
        assertFalse(Timeslot.parseString("Monday 14:00-16:00") == null); //week should be in shorten format
        assertTrue(Timeslot.parseString("Monday 17:00-16:00") == null); //the sequence of time is incorrect
    }

    @Test
    public void compareTimeOrder() {
        Timeslot timeslot = Timeslot.parseString("Mon 14:00-16:00");
        Timeslot timeslot1 = Timeslot.parseString("Tue 14:00-16:00");
        Timeslot timeslot2 = Timeslot.parseString("Mon 17:00-19:00");
        assertTrue(timeslot1.compareTimeOrder(timeslot) == 1);
        assertTrue(timeslot.compareTimeOrder(timeslot1) == -1);
        assertTrue(timeslot2.compareTimeOrder(timeslot) == 1);
        assertTrue(timeslot.compareTimeOrder(timeslot2) == -1);

    }

    @Test
    public void checkTimetableConflicts() {
        tuitionClasses.add(tuitionClass);
        tuitionClasses.add(tuitionClass1);
        tuitionClasses.add(tuitionClass2);
        Timeslot timeslot = Timeslot.parseString("Mon 14:00-16:00");
        assertTrue(timeslot.checkTimetableConflicts(tuitionClasses));
        assertFalse(Timeslot.parseString("Wed 12:00-13:00").checkTimetableConflicts(tuitionClasses));
        assertFalse(Timeslot.parseString("Mon 19:00-20:00").checkTimetableConflicts(tuitionClasses));

    }

}
