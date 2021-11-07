package seedu.address.model.tuiton;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;



public class TuitionClassTest {

    @Test
    public void isSameTuitionClass() {
        TuitionClass tuitionClass = new TuitionClass(new ClassName("CS2103"),
                new ClassLimit(10), Timeslot.parseString("Mon 14:00-16:00"), null, null);
        TuitionClass tuitionClass1 = new TuitionClass(new ClassName("CS2103"),
                new ClassLimit(10), Timeslot.parseString("Tue 14:00-16:00"), null, null);
        TuitionClass tuitionClass2 = new TuitionClass(new ClassName("CS2105"),
                new ClassLimit(10), Timeslot.parseString("Mon 15:00-16:00"), null, null);

        // same object -> returns true
        assertTrue(tuitionClass.isSameTuition(tuitionClass));

        // null -> returns false
        assertFalse(tuitionClass.isSameTuition(null));

        //same name different time slot -> return false
        assertFalse(tuitionClass.isSameTuition(tuitionClass1));

    }

    @Test
    public void matchTheDay() {
        TuitionClass tuitionClass = new TuitionClass(new ClassName("CS2103"),
                new ClassLimit(10), Timeslot.parseString("Mon 14:00-16:00"), null, null);

        // tuitionClass is on Monday
        assertTrue(tuitionClass.matchTheDay(1));

        // tuitionClass is not on Tuesday
        assertFalse(tuitionClass.matchTheDay(2));
    }
}
