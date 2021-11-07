package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;

/**
 * Represent the limit of the participant
 */
public class ClassLimit {
    public static final String MESSAGE_CONSTRAINTS =
            "The limit is invalid. It should be a positive integer and no more than 1000.";
    public final int limit;

    /**
     * Constructor for class limit.
     *
     * @param limit The maximum student size of the class.
     */
    public ClassLimit(int limit) {
        requireNonNull(limit);
        this.limit = limit;
    }

    /**
     * Returns true if a given integer is a valid class limit.
     */
    public static boolean isValidLimit(int limit) {
        return limit <= 1000 && limit > 0;
    }

    @Override
    public String toString() {
        return limit + "";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassLimit // instanceof handles nulls
                && limit == ((ClassLimit) other).limit); // state check
    }


    public int getLimit() {
        return limit;
    }

    /**
     * checks whether the class limit is valid(limit should be larger than 0)
     * @param myLimit the limit to be checked
     * @return true if the limit is valid.
     */
    public static boolean isValid(int myLimit) {
        if (myLimit <= 0) {
            return false;
        }
        return Math.floor(myLimit) == myLimit;
    }
}
