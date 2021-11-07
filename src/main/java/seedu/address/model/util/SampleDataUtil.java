package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Address;
import seedu.address.model.student.Classes;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final ArrayList<String> SAMPLE_STUDENT = new ArrayList<>();
    public static final StudentList SAMPLE_STUDENTS = new StudentList(SAMPLE_STUDENT);

    public static final ArrayList<Integer> SAMPLE_CLASSES = new ArrayList<>() {
        {
            add(1234567);
            add(2234567);
        }
    };

    public static Student[] getSampleStudents() {
        //sampleClasses.add(sampleTuitionClass);
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("Physics | Mon 10:00-12:00", "Chemistry | Tue 10:00-12:00"), new Classes(SAMPLE_CLASSES)),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Student(new Name("Charlotte"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Student(new Name("David"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Student(new Name("Irfan"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Student(new Name("Roy"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses())
        };
    }

    public static TuitionClass[] getSampleClass() {
        return new TuitionClass[] {
            new TuitionClass(new ClassName("Physics"),
                new ClassLimit(5), Timeslot.parseString("Mon 10:00-12:00"),
                getSampleStudentList(), EMPTY_REMARK, 1234567),
            new TuitionClass(new ClassName("Chemistry"),
                new ClassLimit(10), Timeslot.parseString("Tue 10:00-12:00"),
                getSampleStudentList(), EMPTY_REMARK, 2234567)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);

        }
        for (TuitionClass tuitionClass: getSampleClass()) {
            sampleAb.addTuition(tuitionClass);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    private static Classes getEmptyClasses() {
        return new Classes(new ArrayList<>());
    }

    private static StudentList getSampleStudentList() {
        ArrayList<String> studentToAdd = new ArrayList<>();
        studentToAdd.add("Alex Yeoh");
        StudentList students = new StudentList(studentToAdd);
        return students;
    }

}
