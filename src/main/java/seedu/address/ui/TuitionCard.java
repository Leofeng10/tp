package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.TuitionClass;

/**
 * An UI component that displays information of a {@code TuitionClass}.
 */
public class TuitionCard extends UiPart<Region> {

    private static final String FXML = "TuitionListCard.fxml";

    private static final Logger logger = LogsCenter.getLogger(TuitionCard.class);

    private static final String CAPACITY_LABEL = "Capacity: ";
    private static final String REMARK_LABEL = "Remark: ";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TuitionClass tuitionClass;

    @FXML
    private HBox tuitionPane;
    @FXML
    private Label id;
    @FXML
    private Label capacity;
    @FXML
    private Label timeSlot;
    @FXML
    private Label name;
    @FXML
    private Label remark;

    /**
     * Creates a {@code TuitionCode} with the given {@code TuitionClass} and index to display.
     */
    public TuitionCard(TuitionClass tuitionClass, int displayedIndex) {
        super(FXML);
        logger.info("TuitionCard" + tuitionClass.toString());

        this.tuitionClass = tuitionClass;
        id.setText(displayedIndex + ". ");
        name.setText(tuitionClass.getName().getName());
        capacity.setText(CAPACITY_LABEL + tuitionClass.getStudentCount() + "/" + tuitionClass.getLimit());
        timeSlot.setText(tuitionClass.getTimeslot().getTime());
        remark.setText(REMARK_LABEL + tuitionClass.getRemark().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionCard)) {
            return false;
        }

        // state check
        TuitionCard card = (TuitionCard) other;
        return id.getText().equals(card.id.getText())
                && tuitionClass.equals(card.tuitionClass);
    }
}
