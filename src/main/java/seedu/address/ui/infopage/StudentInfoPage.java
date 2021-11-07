package seedu.address.ui.infopage;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * A container to contain student details.
 */
public class StudentInfoPage extends InfoPage {

    private static final String FXML = "StudentInfoPage.fxml";

    private static final Logger logger = LogsCenter.getLogger(StudentInfoPage.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Student student;

    @FXML
    private VBox studentPagePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;

    /**
     * Constructor for a StudentInfoPage
     * @param student Student to display information of.
     */
    public StudentInfoPage(Student student) {
        super(FXML);
        logger.info("StudentInfoPage " + student);

        this.student = student;
        this.name.setText(student.getName().fullName);
        this.phone.setText(student.getPhone().value);
        this.remark.setText(student.getRemark().value);
        this.address.setText(student.getAddress().value);
        this.email.setText(student.getEmail().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionClassInfoPage)) {
            return false;
        }

        // state check
        StudentInfoPage otherInfoPage = (StudentInfoPage) other;
        return this.student.equals(otherInfoPage.student);
    }

}
