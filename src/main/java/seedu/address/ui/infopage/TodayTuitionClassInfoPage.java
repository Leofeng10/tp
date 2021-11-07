package seedu.address.ui.infopage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.ui.TuitionCard;


public class TodayTuitionClassInfoPage extends InfoPage {
    private static final String FXML = "TodayTuitionClassInfoPage.fxml";

    private static final Logger logger = LogsCenter.getLogger(TodayTuitionClassInfoPage.class);

    @FXML
    private ListView<TuitionClass> todayTuitionInfoPageList;

    @FXML
    private Label title;

    private ObservableList<TuitionClass> tuitionClassList;

    /**
     * Constructor fot TodayTuitionClassInfoPage
     * @param tuitionClasses a list that contains today tuition classes
     */
    public TodayTuitionClassInfoPage(ObservableList<TuitionClass> tuitionClasses) {
        super(FXML);
        logger.info("TodayTuitionClassInfoPage " + tuitionClasses.toString());
        this.tuitionClassList = tuitionClasses;

        todayTuitionInfoPageList.setItems(tuitionClassList);
        todayTuitionInfoPageList.setCellFactory(listView -> new TuitionListViewCell());

        title.setText("Today's Tuition Classes");

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
