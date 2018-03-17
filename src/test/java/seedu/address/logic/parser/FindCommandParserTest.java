package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"),
                        Arrays.asList("Tag1","Tag2")));

        assertParseSuccess(parser, "find n/Alice Bob t/Tag1 Tag2", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find \n n/Alice \n \t n/Bob  \t t/Tag1 \n t/Tag2", expectedFindCommand);
    }



}
