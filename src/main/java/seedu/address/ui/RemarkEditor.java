package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import seedu.address.model.student.Remark;

/**
 * Controller for the remark editor
 */
public class RemarkEditor {

    @FXML
    private TextArea textArea;

    @FXML
    private Label name;

    /**
     * Sets a remark into the text area.
     * @param name The name of the student or tuition class being modified.
     * @param remark The current remark of the student or tuition class.
     */
    public void setRemark(String name, String remark) {
        requireAllNonNull(name, remark);
        this.name.setText(name);
        textArea.setText(remark);
    }

    /**
     * Returns the text in the text area as a remark.
     * @return The new edited remark.
     */
    public Remark getNewRemark() {
        return new Remark(textArea.getText());
    }
}
