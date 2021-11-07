package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

class SortCommandParserTest {
    private static SortCommandParser parser = new SortCommandParser();
    private static final String TIME_KEYWORD = "time";
    private static final String ASCENDING_KEYWORD = "asc";
    private static final String DESCENDING_KEYWORD = "desc";
    private static final String RANDOM_STRING_INPUT = "Hello";


    @Test
    public void parse_sort_success() {
        //sort by time using time keyword
        SortCommand sortTimeCommand = new SortCommand(SortCommandParser.Order.TIME);
        assertParseSuccess(parser, " " + PREFIX_SORT_ORDER + TIME_KEYWORD, sortTimeCommand);

        //sort without keyword
        assertParseSuccess(parser, "", sortTimeCommand);

        //sort by alphabetically ascending order
        SortCommand sortAscCommand = new SortCommand(SortCommandParser.Order.ASCENDING);
        assertParseSuccess(parser, " " + PREFIX_SORT_ORDER + ASCENDING_KEYWORD, sortAscCommand);

        //sort by alphabetically descending order
        SortCommand sortDescCommand = new SortCommand(SortCommandParser.Order.DESCENDING);
        assertParseSuccess(parser, " " + PREFIX_SORT_ORDER + DESCENDING_KEYWORD, sortDescCommand);

        //random input before order prefix
        assertParseSuccess(parser, " " + RANDOM_STRING_INPUT + " " + PREFIX_SORT_ORDER + TIME_KEYWORD,
                sortTimeCommand);

    }

    @Test
    public void parse_missingFields_failure() {
        //invalid prefix used
        assertParseFailure(parser, " " + PREFIX_TUITION_CLASS, SortCommand.MESSAGE_USAGE);

        //missing keyword after prefix
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " " + PREFIX_SORT_ORDER, expectedMessage);

        //wrong keyword after prefix
        assertParseFailure(parser, " " + PREFIX_SORT_ORDER + RANDOM_STRING_INPUT, expectedMessage);

        //random input
        assertParseFailure(parser, " " + RANDOM_STRING_INPUT, SortCommand.MESSAGE_USAGE);

        //random input after order prefix
        assertParseFailure(parser, " " + PREFIX_SORT_ORDER + TIME_KEYWORD + " " + RANDOM_STRING_INPUT,
                expectedMessage);
    }
}
