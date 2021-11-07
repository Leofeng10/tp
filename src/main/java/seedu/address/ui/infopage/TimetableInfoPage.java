package seedu.address.ui.infopage;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.Timetable;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.ui.ResultDisplay;

/**
 * Timetable class which outputs a timetable according to tuition list.
 */
public class TimetableInfoPage extends InfoPage {
    private static final String FXML = "TimetableInfoPage.fxml";
    private static final String PANE_BORDER = "-fx-border-width:0.3px;-fx-border-color:black";
    private static final String PANE_NO_BORDER = "-fx-border-width:0px;";
    private static final Logger logger = LogsCenter.getLogger(TimetableInfoPage.class);
    private static final String[] days = convertDays();

    private final int numCols = 8;
    private Timetable timetable;

    @FXML
    private GridPane timetableShown;

    /**
     * Displays a timetable on screen to user.
     * @param tuitionClasses tuition classes to be shown.
     * @param resultDisplay the ResultDisplay panel.
     *                      Used if information of certain classes are not shown due to space limit.
     */
    public TimetableInfoPage(ObservableList<TuitionClass> tuitionClasses, ResultDisplay resultDisplay) {
        super(FXML);
        logger.info("Starting construction of timetable.");
        this.timetable = new Timetable(tuitionClasses, resultDisplay, this);
        setTableDay();
        timetable.showTimetable();
        logger.info("Timetable shown.");
    }

    /**
     * Adds a new lesson into the timetable.
     * @param lesson the lesson to be added
     * @param col the column of the timetable the lesson is to be added to.
     * @param row the row of the timetable the lesson is to be added to.
     * @param colSpan the column span of the lesson.
     * @param rows the row span of the lesson.
     */
    public void addLesson(Label lesson, int col, int row, int colSpan, int rows) {
        timetableShown.add(lesson, col, row, colSpan,
                rows);
    }

    private void setTableDay() {
        for (int j = 0; j < numCols; j++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            timetableShown.getColumnConstraints().add(colConst);
        }
    }

    /**
     * Sets vertical size of the table.
     * @param start starting time of timetable.
     * @param end end time of timetable.
     */
    public void setTableTime(int start, int end, int totalRows) {
        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / totalRows);
            timetableShown.getRowConstraints().add(rowConst);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < totalRows; j++) {
                StackPane pane;
                if (j == 0 && i != 0) {
                    pane = getDayPane(i);
                } else if (j % 6 == 0 && i == 0 && j != 0) {
                    pane = getTimePane(start - 1 + j / 6);
                } else {
                    pane = new StackPane();
                }

                if (j % 6 == 0) {
                    pane.setStyle(PANE_BORDER);
                } else {
                    pane.setStyle(PANE_NO_BORDER);
                }
                int rowSpan = j % 6 == 0 ? 6 : i == 0 ? 6 : 1;
                timetableShown.add(pane, i, j, 1, rowSpan);
            }
        }
    }

    private StackPane getDayPane(int i) {
        StackPane pane = new StackPane();
        Label label = new Label(days[i - 1]);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        return pane;
    }

    private StackPane getTimePane(int i) {
        StackPane pane = new StackPane();
        Label label = new Label(i + ":00");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        return pane;
    }

    private static String[] convertDays() {
        String[] dayOfWeek = new String[]{"Mon", "Tue", "Wed",
            "Thu", "Fri", "Sat", "Sun"};
        return dayOfWeek;
    }
}
