package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tuition.Timeslot;

public class JsonAdaptedTuitionTest {

    private static final String INVALID_TIME = "Mon 2pm-4pm";


    private static final String VALID_NAME = "CS2100";
    private static final int VALID_LIMIT = 10;
    private static final ArrayList<String> VALID_STUDENT = new ArrayList<>();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final int VALID_ID = 0;
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTuition tuition =
                new JsonAdaptedTuition(VALID_NAME, VALID_LIMIT,
                        INVALID_TIME, VALID_STUDENT, VALID_REMARK, VALID_ID);
        String expectedMessage = Timeslot.TIME_FORMAT_INCORRECT;
        assertThrows(IllegalValueException.class, expectedMessage, tuition::toModelType);
    }
}
