package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.student.Remark;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

public class TuitionClassBuilder {
    public static final String DEFAULT_NAME = "CHEMISTRY";
    public static final int DEFAULT_LIMIT = 5;
    public static final String DEFAULT_TIMESLOT = "Monday 14:00-15:00";
    public static final String DEFAULT_REMARK = "$40 per hour";
    public static final int DEFAULT_ID = 1;

    private ClassName name;
    private ClassLimit limit;
    private Timeslot timeslot;
    private StudentList studentList;
    private Remark remark;
    private int id;

    /**
     * Creates a {@code TuitionClassBuilder} with the default details.
     */
    public TuitionClassBuilder() {
        name = new ClassName(DEFAULT_NAME);
        limit = new ClassLimit(DEFAULT_LIMIT);
        timeslot = Timeslot.parseString(DEFAULT_TIMESLOT);
        studentList = new StudentList(new ArrayList<>());
        remark = new Remark(DEFAULT_REMARK);
        id = DEFAULT_ID;
    }

    /**
     * Initializes the TuitionClassBuilder with the data of {@code classToCopy}.
     */
    public TuitionClassBuilder(TuitionClass classToCopy) {
        name = classToCopy.getName();
        limit = classToCopy.getLimit();
        timeslot = classToCopy.getTimeslot();
        studentList = getNewStudentList(classToCopy.getStudentList());
        remark = classToCopy.getRemark();
        id = classToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withName(String name) {
        this.name = new ClassName(name);
        return this;
    }

    /**
     * Sets the {@code ClassLimit} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withClassLimit(int limit) {
        this.limit = new ClassLimit(limit);
        return this;
    }

    /**
     * Sets the {@code Timeslot} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withTimeslot(String timeslot) {
        this.timeslot = Timeslot.parseString(timeslot);
        return this;
    }
    /**
     * Sets the {@code StudentList} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withStudentList(ArrayList<String> studentList) {
        this.studentList = new StudentList(studentList);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }
    /**
     * Sets the {@code id} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TuitionClass build() {
        return new TuitionClass(name, limit, timeslot, studentList, remark, id);
    }

    private StudentList getNewStudentList(StudentList studentListToCopy) {
        ArrayList<String> newList = new ArrayList<>();
        newList.addAll(studentListToCopy.getStudents());
        return new StudentList(newList);
    }
}
