//@@author philos22
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.stream.Stream;

import com.google.api.client.util.DateTime;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CalendarAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CalendarAddCommandParser implements Parser<CalendarAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CalendarAddCommand
     * and returns the message of whether execution was successful or not.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalendarAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_LOCATION, PREFIX_START, PREFIX_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT, PREFIX_LOCATION, PREFIX_START, PREFIX_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }


        try {
            String eventName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT)).get().toString();
            String eventLocation = ParserUtil.parseName(argMultimap.getValue(PREFIX_LOCATION)).get().toString();
            DateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START).toString());
            DateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END).toString());
            return new CalendarAddCommand(eventName, eventLocation, startDateTime, endDateTime);

        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}