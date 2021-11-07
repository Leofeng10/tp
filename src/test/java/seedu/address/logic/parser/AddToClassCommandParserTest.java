package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToClassCommand;
import seedu.address.model.student.Name;
import seedu.address.model.tuition.StudentList;


class AddToClassCommandParserTest {
    private static final String INVALID_PREFIX = "x/";
    private static final String SPACE = " ";
    private static final String ZERO_INDEX = "0";
    private static final String NEGATIVE_INDEX = "-1";
    private static final String INDEXES_SEPARATED_BY_COMMA = "2,3,4,5";
    private static final String INDEXES = "2 3 4";
    private static final String NAMES_SEPARATED_BY_SPACE_AND_COMMA = "John, James";
    private static final String VALID_INDEX_ONE = "1";
    private AddToClassCommandParser parser = new AddToClassCommandParser();

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToClassCommand.MESSAGE_USAGE);
        //only student name is present, missing tuition class index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT + VALID_NAME_BOB, expectedMessage);

        //only tuition class index is present, missing student name or student index
        assertParseFailure(parser, SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, expectedMessage);

        //all fields missing
        assertParseFailure(parser, "", expectedMessage);

        //student name prefix present with no value
        assertParseFailure(parser, SPACE + PREFIX_STUDENT
                + SPACE + PREFIX_TUITION_CLASS + "1", Name.MESSAGE_CONSTRAINTS_ADD_STUDENT_TO_CLASS);

        //tuition class prefix present with no value
        assertParseFailure(parser, SPACE + PREFIX_STUDENT
                + SPACE + VALID_NAME_BOB + PREFIX_TUITION_CLASS, expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToClassCommand.MESSAGE_USAGE);
        //random string before prefix
        assertParseFailure(parser, SPACE + "this is a random string " + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, expectedMessage);

        //random integer before prefix
        assertParseFailure(parser, SPACE + VALID_INDEX_ONE + SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, expectedMessage);

        //missing empty space between keyword 'addtoclass' and prefix
        assertParseFailure(parser, PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, expectedMessage);

        //invalid prefix
        assertParseFailure(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE
                + SPACE + INVALID_PREFIX, MESSAGE_INVALID_INDEX);


        //valid but non-required prefix
        assertParseFailure(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE
                + SPACE + PREFIX_REMARK, MESSAGE_INVALID_INDEX);

        //multiple tuition class indexes
        assertParseFailure(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + INDEXES,
                MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        //zero student index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + ZERO_INDEX + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, MESSAGE_INVALID_INDEX);

        //negative student index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + NEGATIVE_INDEX + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, MESSAGE_INVALID_INDEX);

        //zero tuition class index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + VALID_INDEX_ONE + SPACE + PREFIX_TUITION_CLASS + ZERO_INDEX, MESSAGE_INVALID_INDEX);

        //negative tuition class index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + VALID_INDEX_ONE + SPACE + PREFIX_TUITION_CLASS + NEGATIVE_INDEX, MESSAGE_INVALID_INDEX);

        //student index separated by comma
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + INDEXES_SEPARATED_BY_COMMA + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, MESSAGE_INVALID_INDEX);

        //name separated by space around comma
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX
                + NAMES_SEPARATED_BY_SPACE_AND_COMMA + SPACE + PREFIX_TUITION_CLASS
                + VALID_INDEX_ONE, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_addWithStudentIndex_success() {
        //add one index
        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST);
        AddToClassCommand addOneIndex = getCommand(indexList, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT_INDEX
                + VALID_INDEX_ONE + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, addOneIndex);

        //add multiple indexes
        List<Index> multipleIndexes = new ArrayList<>();
        multipleIndexes.add(INDEX_FOURTH);
        multipleIndexes.add(INDEX_THIRD);
        multipleIndexes.add(INDEX_SECOND);
        AddToClassCommand addMultipleIndexes = getCommand(multipleIndexes, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT_INDEX
                + INDEXES + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, addMultipleIndexes);

        //add repetitive indexes
        List<Index> repetitiveIndexes = new ArrayList<>();
        repetitiveIndexes.add(INDEX_FIRST);
        AddToClassCommand addRepetitiveIndexes = getCommand(repetitiveIndexes, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT_INDEX
                + "1 1 1" + SPACE + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, addRepetitiveIndexes);
    }

    @Test
    public void parse_addWithStudentNames_success() {
        //add one name
        ArrayList<String> studentName = new ArrayList<>();
        studentName.add(VALID_NAME_BOB);
        AddToClassCommand addToClassCommand = getCommand(studentName, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + SPACE + PREFIX_TUITION_CLASS + "1", addToClassCommand);

        //add multiple names
        ArrayList<String> multipleNames = new ArrayList<>();
        multipleNames.add(VALID_NAME_BOB);
        multipleNames.add(VALID_NAME_AMY);
        AddToClassCommand addMultipleNames = getCommand(multipleNames, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + "," + VALID_NAME_AMY + SPACE
                + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, addMultipleNames);

        //add repetitive names
        ArrayList<String> repetitiveNames = new ArrayList<>();
        repetitiveNames.add(VALID_NAME_BOB);
        repetitiveNames.add(VALID_NAME_BOB);
        AddToClassCommand addRepetitiveNames = getCommand(repetitiveNames, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT
                + VALID_NAME_BOB + "," + VALID_NAME_BOB + SPACE
                + PREFIX_TUITION_CLASS + VALID_INDEX_ONE, addRepetitiveNames);
    }



    private AddToClassCommand getCommand(ArrayList<String> students, Index tuitionClass) {
        return new AddToClassCommand(new StudentList(students), tuitionClass);
    }

    private AddToClassCommand getCommand(List<Index> students, Index tuitionClass) {
        return new AddToClassCommand(students, tuitionClass);
    }

}
