package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     * Does not throw ParseException as there are no restrictions for remark input.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String limit} into an {@code ClassLimit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     *
     */
    public static ClassLimit parseLimit(String limit) throws ParseException {
        requireNonNull(limit);
        String trimmedLimit = limit.trim();
        try {
            int myLimit = Integer.parseInt(trimmedLimit);
            if (!ClassLimit.isValidLimit(myLimit)) {
                throw new ParseException(ClassLimit.MESSAGE_CONSTRAINTS);
            }
            return new ClassLimit(myLimit);
        } catch (NumberFormatException e) {
            throw new ParseException(ClassLimit.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String order} into a trimmed string.
     * @param order the intended sorting order input by user.
     * @return an Order object.
     */
    public static SortCommandParser.Order parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (trimmedOrder.equals(SortCommandParser.Order.TIME.toString())) {
            return SortCommandParser.Order.TIME;
        } else if (trimmedOrder.equals(SortCommandParser.Order.ASCENDING.toString())) {
            return SortCommandParser.Order.ASCENDING;
        } else if (trimmedOrder.equals(SortCommandParser.Order.DESCENDING.toString())) {
            return SortCommandParser.Order.DESCENDING;
        } else {
            throw new ParseException(SortCommandParser.Order.ORDER_CONSTRAINT);
        }
    }

    /**
     * Parses a {@code String className} into a {@code ClassName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ClassName parseClassName(String className) throws ParseException {
        requireNonNull(className);
        String trimmedName = className.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ClassName(trimmedName);
    }
    /**
     * Parses a {@code List students} into a {@code Student}.
     * @param students a list of students, each of which is a string
     * @return a single student object containing an arraylist
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static StudentList parseStudent(List students) throws ParseException {
        requireNonNull(students);
        String trimmedStudents = (String) students.get(0);
        String[] studentNames = trimmedStudents.split(",");
        ArrayList<String> studentList = new ArrayList<>();
        for (String s: studentNames) {
            if (!Name.isValidName(s)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS_ADD_STUDENT_TO_CLASS);
            }
            studentList.add(s);
        }
        return new StudentList(studentList);
    }

    /**
     * Parses a {@code List studentIndexes} into a List of indexes.
     *
     * @param studentIndexes List of indexes, each representing a student.
     * @return List of student indexes sorted in descending order.
     * @throws ParseException If any index is invalid.
     */
    public static List<Index> parseStudentIndexes(List studentIndexes) throws ParseException {
        List<Index> args = new ArrayList<Index>();
        String[] students = ((String) studentIndexes.get(0)).split(" ");
        for (String student : students) {
            Index i = parseIndex(student);
            if (!args.contains(i)) {
                args.add(i);
            }
        }
        Collections.sort(args, (index1, index2) -> {
            if (index1.getOneBased() < index2.getOneBased()) {
                return 1;
            } else if (index1.getOneBased() > index2.getOneBased()) {
                return -1;
            } else {
                return 0;
            }
        });
        return args;
    }

    /**
     * Parses a {@code String timeslot} into a {@code Timeslot}.
     * Uses EEE HH:mm-HH:mm format.
     *
     * @param timeslot The String that represents the day and timings.
     * @return The timeslot.
     * @throws ParseException If there are parsing errors or the timings are invalid.
     */
    public static Timeslot parseTimeslot(String timeslot) throws ParseException {
        requireNonNull(timeslot);
        //Splits day from time
        String[] arr = timeslot.trim().split(" ", 2);
        String[] times = arr.length == 2 ? arr[1].split("-", 2) : null;

        if (arr.length < 2 || times == null || times.length < 2) {
            throw new ParseException(Messages.MESSAGE_TIMESLOT_FORMAT);
        }
        DateFormat dayFormat = new SimpleDateFormat("EEE");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        try {
            Date day = dayFormat.parse(arr[0]);
            LocalTime start = LocalTime.parse(times[0], timeFormat);
            LocalTime end = LocalTime.parse(times[1], timeFormat);

            if (!start.isBefore(end)) {
                throw new ParseException(Messages.MESSAGE_TIMESLOT_FORMAT);
            }
            return new Timeslot(day, start, end);
        } catch (DateTimeException | java.text.ParseException de) {
            throw new ParseException(Messages.MESSAGE_TIMESLOT_FORMAT);
        }
    }
}

