package seedu.address.ui;

import java.util.ArrayList;

/**
 * This class manages the history of prior inputs entered by the user.
 */
public class InputHistory {

    private ArrayList<String> historyList;
    private int indexPointer;

    /**
     * Constructor for an InputHistory object.
     */
    public InputHistory() {
        this.historyList = new ArrayList<>();
        indexPointer = 0;
    }

    /**
     * Add an input to the end of the history list.
     * Does not add input if it is the same String as the last input added.
     * Resets the pointer to the back of the list.
     * @param input String to be added to history
     */
    public void add(String input) {
        assert(input != null);

        int lastItemIndex = historyList.size() - 1;
        if (historyList.isEmpty() || !input.equals(historyList.get(lastItemIndex))) {
            historyList.add(input);
        }
        indexPointer = historyList.size();
    }

    /**
     * Returns the input just before the current pointer position in the list.
     * If history is empty, returns an empty string.
     * If the pointer is already at the start of the list, returns first input saved.
     * @return String of the previous input
     */
    public String getPreviousInput() {
        if (historyList.isEmpty()) {
            return "";
        }

        int indexToRetrieve;
        if (indexPointer == 0) {
            indexToRetrieve = 0;
        } else {
            indexPointer -= 1;
            indexToRetrieve = indexPointer;
        }

        return historyList.get(indexToRetrieve);
    }

    /**
     * Returns the input just after the current pointer position in the list.
     * If the pointer is already at the end of the list, returns an empty String.
     * @return String of the next input
     */
    public String getNextInput() {
        if (indexPointer >= historyList.size() - 1) {
            indexPointer = historyList.size();
            return "";
        } else {
            indexPointer += 1;
            return historyList.get(indexPointer);
        }
    }

}
