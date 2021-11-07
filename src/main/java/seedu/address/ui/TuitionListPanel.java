package seedu.address.ui;


import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.TuitionClass;

/**
 * Panel containing the list of tuition classes.
 */
public class TuitionListPanel extends UiPart<Region> {
    private static final String FXML = "TuitionListPanel.fxml";

    private static final String TITLE_DEFAULT = "Classes";
    private static final String TITLE_FILTERED = "Classes (filtered)";

    private final Logger logger = LogsCenter.getLogger(TuitionListPanel.class);

    @FXML
    private ListView<TuitionClass> tuitionListView;

    @FXML
    private Label title;

    /**
     * Creates a {@code TuitionListPanel} with the given {@code ObservableList}.
     */
    public TuitionListPanel(ObservableList<TuitionClass> tuitionList) {
        super(FXML);
        tuitionListView.setItems(tuitionList);
        tuitionListView.setCellFactory(listView -> new TuitionListViewCell());
        title.setText(TITLE_DEFAULT);
    }

    public void setFiltered(boolean bool) {
        if (bool) {
            title.setText(TITLE_FILTERED);
        } else {
            title.setText(TITLE_DEFAULT);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TuitionClass} using a {@code TuitionCard}.
     */
    class TuitionListViewCell extends ListCell<TuitionClass> {
        @Override
        protected void updateItem(TuitionClass tuitionClass, boolean empty) {
            super.updateItem(tuitionClass, empty);

            if (empty || tuitionClass == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TuitionCard(tuitionClass, getIndex() + 1).getRoot());
            }
        }
    }
}
