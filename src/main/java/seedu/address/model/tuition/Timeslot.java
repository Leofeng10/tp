package seedu.address.model.tuition;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Represents the time slot that the tuition class occupies in the Timetable.
 */
public class Timeslot {
    public static final String TIME_FORMAT_INCORRECT = "The time format is not correct";

    //used to sorting tuition classes by comparing date
    private static HashMap<String, Integer> days = new HashMap<>() {{
            put("Mon", 1);
            put("Tue", 2);
            put("Wed", 3);
            put("Thu", 4);
            put("Fri", 5);
            put("Sat", 6);
            put("Sun", 7);
        }};

    private Date day;
    private LocalTime start;
    private LocalTime end;

    /**
     * Constructor for timeslot.
     *
     * @param day Day in the week in EEE format
     * @param start Start time of class in HH:mm format
     * @param end End time of class in HH:mm format
     */
    public Timeslot(Date day, LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public String getDayString() {
        return (this.getTime().split(" "))[0];
    }

    /**
     * Returns the start time of the class in LocalTime format.
     *
     * @return LocalTime representation of starting time of the class.
     */
    public LocalTime getStart() {
        return this.start;
    }

    /**
     * Returns the end time of the class in LocalTime format.
     *
     * @return LocalTime representation of ending time of the class.
     */
    public LocalTime getEnd() {
        return this.end;
    }

    /**
     * Returns the day of the class in Date format.
     *
     * @return Date representation of the day of the class.
     */
    public Date getDay() {
        return this.day;
    }

    /**
     * Compare two time slots to detect any overlaps or conflicts.
     *
     * @return Boolean true if conflict exists and false otherwise.
     */
    public boolean checkClassConflict(Timeslot otherSlot) {
        LocalTime otherStart = otherSlot.getStart();
        LocalTime otherEnd = otherSlot.getEnd();
        if (otherSlot.getDay().getDay() != day.getDay()) {
            return false;
        }
        LocalTime max = otherStart.isAfter(start) ? otherStart : start;
        LocalTime min = otherEnd.isBefore(end) ? otherEnd : end;
        return min.isAfter(max);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Timeslot) {
            Timeslot otherSlot = ((Timeslot) other);
            return day == otherSlot.getDay()
                    && !(otherSlot.getEnd().isAfter(end) || otherSlot.getEnd().isBefore(end))
                    && !(otherSlot.getStart().isAfter(start) || otherSlot.getStart().isBefore(start));
        }
        return false;
    }

    /**
     * Returns String representation of this Timeslot in a specified format.
     *
     * @return String that represents the timeslot.
     */
    public String getTime() {
        DateFormat dayFormat = new SimpleDateFormat("EEE");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        return String.format("%s %s-%s", dayFormat.format(day), start.format(timeFormat), end.format(timeFormat));
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getTime();
    }

    /**
     * Parses a time range in the form of string into LocalTime format.
     * @return the starting time and end time in an array.
     */
    public LocalTime[] parseTime() {
        String[] time = this.getTime().split(" ");
        String start = time[1].substring(0, 5);
        String end = time[1].substring(6, 11);
        if (start.substring(0, 2).equals("24")) {
            start = "00" + start.substring(2);
        }
        if (end.substring(0, 2).equals("24")) {
            end = "00" + end.substring(2);
        }
        LocalTime localStart = LocalTime.parse(start);
        LocalTime localEnd = LocalTime.parse(end);
        return new LocalTime[]{localStart, localEnd};
    }

    public static HashMap<String, Integer> getDays() {
        return days;
    }

    /**
     * Returns timeslot given timeslot as String in the correct format.
     * @param slot
     * @return
     */
    public static Timeslot parseString(String slot) {
        String[] arr = slot.trim().split(" ", 2); //Splits day from time
        String[] times = arr.length == 2 ? arr[1].split("-", 2) : null;

        if (arr.length < 2 || times == null || times.length < 2) {
            return null;
        }
        DateFormat dayFormat = new SimpleDateFormat("EEE");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        try {
            Date day = dayFormat.parse(arr[0]);
            LocalTime start = LocalTime.parse(times[0], timeFormat);
            LocalTime end = LocalTime.parse(times[1], timeFormat);

            if (!start.isBefore(end)) {
                return null;
            }
            return new Timeslot(day, start, end);
        } catch (DateTimeException | java.text.ParseException de) {
            return null;
        }
    }

    /**
     * Returns true if any timeslot of existing classes clashes with this timeslot.
     *
     * @param classList The list of existing tuition classes.
     * @return Boolean true if there is a conflict and false otherwise.
     */
    public boolean checkTimetableConflicts(List<TuitionClass> classList) {
        for (TuitionClass tc: classList) {
            if (this.checkClassConflict(tc.getTimeslot())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a negative integer, zero, or a positive integer by comparing the two timeslots.
     *
     * @param timeslot The timeslot to be compared to.
     * @return An integer indicating the relative size of two timeslots compared to each other.
     */
    public int compareTimeOrder(Timeslot timeslot) {
        int otherDay = timeslot.getDay().getDay();
        int compareDay = day.getDay() - otherDay;
        if (compareDay != 0) {
            return compareDay > 0 ? 1 : -1;
        }

        if (start.isBefore(timeslot.getStart())) {
            return -1;
        } else {
            return 1;
        }
    }
}


