package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.ui.InputHistory;

public class InputHistoryTest {

    private static final String TEST_STRING_A = "test string a";
    private static final String TEST_STRING_B = "test string b";

    @Test
    public void emptyHistory_getPrevious_returnsEmptyString() {
        InputHistory history = new InputHistory();
        assertEquals("", history.getPreviousInput());
    }

    @Test
    public void emptyHistory_getLater_returnsEmptyString() {
        InputHistory history = new InputHistory();
        assertEquals("", history.getNextInput());
    }

    @Test
    public void getPrevious_returnsCorrectString() {
        InputHistory history = new InputHistory();
        history.add(TEST_STRING_A);
        assertEquals(TEST_STRING_A, history.getPreviousInput());

    }

    @Test
    public void sameInputRepeatedlyInARow_onlyOneAdded() {
        InputHistory history = new InputHistory();
        history.add(TEST_STRING_A);
        history.add(TEST_STRING_B);
        history.add(TEST_STRING_A);
        history.add(TEST_STRING_A);
        history.getPreviousInput();
        assertEquals(TEST_STRING_B, history.getPreviousInput());
    }

    @Test
    public void startOfList_getPrevious_returnsFirst() {
        InputHistory history = new InputHistory();
        history.add(TEST_STRING_A);
        history.getPreviousInput();
        assertEquals(TEST_STRING_A, history.getPreviousInput());
    }

    @Test
    public void endOfList_getLater_returnsEmptyString() {
        InputHistory history = new InputHistory();
        history.add(TEST_STRING_A);
        history.getPreviousInput();
        assertEquals("", history.getNextInput());
    }


}
