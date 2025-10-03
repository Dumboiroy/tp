package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * and returns a RemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RemarkCommand parse(String args) throws ParseException {
        return new RemarkCommand();
    }
}
