package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TimetableCommand;



class TimetableParserTest {
    private static final TimetableParser parser = new TimetableParser();

    @Test
    void parse_success() {
        assertParseSuccess(parser, "", new TimetableCommand());
    }

    @Test
    void parse_invalidInput_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableCommand.MESSAGE_USAGE);
        //invalid prefix
        assertParseFailure(parser, PREFIX_STUDENT.getPrefix(), expectedMessage);

        //random string
        assertParseFailure(parser, "this is a string", expectedMessage);

    }
}
