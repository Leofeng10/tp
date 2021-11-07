package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "tutassistor")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TUITION = "Tuition list contains duplicate tuition(s).";
    private static final Logger logger = LogsCenter.getLogger(JsonSerializableAddressBook.class);

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    private final List<JsonAdaptedTuition> tuitions = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("tuitions") List<JsonAdaptedTuition> tuitions) {
        this.students.addAll(students);
        this.tuitions.addAll(tuitions);
    }

    /*
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("tuition") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }
    */

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream()
                .map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        tuitions.addAll(source.getTuitionList().stream()
                .map(JsonAdaptedTuition::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            logger.info("JSAStudent: " + student.toString());
            addressBook.addStudent(student);
        }

        for (JsonAdaptedTuition jsonAdaptedTuition: tuitions) {
            TuitionClass tuitionClass = jsonAdaptedTuition.toModelType();
            if (addressBook.hasTuition(tuitionClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUITION);
            }
            logger.info("JSATuition: " + tuitionClass.toString());
            addressBook.addTuition(tuitionClass);
        }
        return addressBook;
    }
}
