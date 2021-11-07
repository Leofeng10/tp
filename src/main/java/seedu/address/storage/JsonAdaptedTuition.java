package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Remark;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * Jackson-friendly version of {@link TuitionClass}.
 */
class JsonAdaptedTuition {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tuition class' %s field is missing!";

    private final String name;
    private final int limit;
    private final String timeslot;

    private final ArrayList<String> students = new ArrayList<>();
    private final String remark;

    private final int id;
    //private final ArrayList<String> student = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedTuition(@JsonProperty("name") String name, @JsonProperty("limit") int limit,
                             @JsonProperty("timeslot") String timeslot,
                             @JsonProperty("students") ArrayList<String> student,
                             @JsonProperty("remark") String remark, @JsonProperty("id") int id) {
        this.name = name;
        this.limit = limit;
        this.timeslot = timeslot;
        this.remark = remark;
        this.id = id;

        if (student != null) {
            this.students.addAll(student);
        }
    }

    /**
     * Converts a given {@code TuitionClass} into this class for Jackson use.
     */
    public JsonAdaptedTuition(TuitionClass source) {
        name = source.getName().getName();
        limit = source.getLimit().getLimit();
        timeslot = source.getTimeslot().getTime();
        students.addAll(source.getStudentList().getStudents());
        remark = source.getRemark().value;
        id = source.getId();
    }

    /**
     * Converts this Jackson-friendly adapted tuition object into the model's {@code TuitionClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public TuitionClass toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final ClassName modelName = new ClassName(name);

        final ClassLimit modelLimit = new ClassLimit(limit);


        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }

        final Timeslot modelTimeslot = Timeslot.parseString(timeslot);

        if (modelTimeslot == null) {
            throw new IllegalValueException(Timeslot.TIME_FORMAT_INCORRECT);
        }

        final StudentList modelStudent = new StudentList(students);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        return new TuitionClass(modelName, modelLimit, modelTimeslot, modelStudent, modelRemark, this.id);
    }
}
