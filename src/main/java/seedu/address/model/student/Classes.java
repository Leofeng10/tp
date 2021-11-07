package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

/**
 * Represents the tuition classes that this student takes
 */
public class Classes {

    private ArrayList<Integer> classes;

    /**
     * Constructor for classes.
     *
     * @param classes The list of all class ids.
     */
    public Classes(ArrayList<Integer> classes) {
        requireNonNull(classes);
        this.classes = classes;
    }

    public int getNumofClass() {
        return classes.size();
    }

    public void updateClasses(ArrayList<Integer> classes) {
        this.classes = classes;
    }

    public ArrayList<Integer> getClasses() {
        return this.classes;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Integer t : classes) {
            builder.append(t.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return classes.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Classes // instanceof handles nulls
                && equalClasses(((Classes) other).classes)); // state check
    }

    /**
     * Returns true if classes are equal.
     *
     * @param otherClasses List of classes to compare to.
     * @return True if the classes are identical, false otherwise.
     */
    public boolean equalClasses(ArrayList<Integer> otherClasses) {
        if (otherClasses.size() != this.classes.size()) {
            return false;
        }
        for (int i = 0; i < classes.size(); i++) {
            if (!otherClasses.get(i).equals(this.classes.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a class into the student's class list.
     * @param tuitionClass the tuition class to be added.
     * @return the updated Classes object.
     */
    public Classes addClass(Integer tuitionClass) {
        this.getClasses().add(tuitionClass);
        return this;
    }

    /**
     * Returns a copy of the classes after removing a particular tuition class.
     *
     * @param tuitionClass The tuition class to be removed.
     * @return Copy of classes.
     */
    public Classes removeClass(Integer tuitionClass) {
        this.classes.remove(tuitionClass);
        ArrayList<Integer> copyOfClass = new ArrayList<>();
        copyOfClass.addAll(classes);
        Classes classesCopy = new Classes(copyOfClass);
        return classesCopy;
    }
}
