package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;

/**
 * Represents the name of the tuition class.
 */
public class ClassName {

    public final String name;

    /**
     * Constructor for Class Name.
     *
     * @param name The name of the class.
     */
    public ClassName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ClassName) {
            return name.equals(((ClassName) other).name);
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
