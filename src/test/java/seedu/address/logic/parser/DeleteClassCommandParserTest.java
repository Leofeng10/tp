package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteClassCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteClassCommandParserTest {

    private DeleteClassCommandParser parser = new DeleteClassCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2",
                new DeleteClassCommand(List.<Index>of(INDEX_FIRST, INDEX_SECOND)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteClassCommand.MESSAGE_USAGE));
    }
}
