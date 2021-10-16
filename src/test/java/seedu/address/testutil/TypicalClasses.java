package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tuition.TuitionClass;

public class TypicalClasses {
    public static final TuitionClass MATH = new TuitionClassBuilder().withName("Math")
            .withClassLimit(5).withTimeslot("Mon 11:00-14:00").withRemark("Trigonometric")
            .withId(1).build();
    public static final TuitionClass PHYSICS = new TuitionClassBuilder().withName("Physics")
            .withClassLimit(7).withTimeslot("Tue 15:00-16:00").withRemark("General relativity")
            .withId(2).build();
    public static final TuitionClass ENGLISH = new TuitionClassBuilder().withName("English")
            .withClassLimit(4).withTimeslot("Thu 14:00-16:00").withRemark("Collect fees every Friday")
            .withId(3).build();
    public static final TuitionClass GEOGRAPHY = new TuitionClassBuilder().withName("Geography")
            .withClassLimit(8).withTimeslot("Fri 09:00-11:00").withRemark("Recommend textbooks")
            .withId(4).build();


    private TypicalClasses() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tuition classes.
     */
    public static AddressBook getAddressBookWithTypicalClasses() {
        AddressBook ab = new AddressBook();
        for (TuitionClass tuitionClass : getTypicalClasses()) {
            ab.addTuition(tuitionClass);
        }
        return ab;
    }

    public static List<TuitionClass> getTypicalClasses() {
        return new ArrayList<>(Arrays.asList(MATH, PHYSICS, ENGLISH, GEOGRAPHY));
    }
}
