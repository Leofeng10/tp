package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    public enum UiAction {
        SHOW_HELP,
        SHOW_TUITION_PAGE,
        SHOW_STUDENT_PAGE,
        SHOW_TODAY_TUITIONS_PAGE,
        SHOW_TIMETABLE,
        SET_TUITIONS_DEFAULT,
        SET_STUDENTS_DEFAULT,
        SET_TUITIONS_FILTERED,
        SET_STUDENTS_FILTERED,
        EXIT,
        NONE
    }

    /** Action to be taken by UI component*/
    private final UiAction uiAction;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.uiAction = requireNonNull(uiAction);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, UiAction.NONE);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public UiAction getUiAction() {
        return this.uiAction;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && this.uiAction == otherCommandResult.uiAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction);
    }

}
