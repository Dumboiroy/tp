package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);

    static {
        Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);
        System.out.println("Logger name: " + logger.getName());
        System.out.println("Parent logger: " + logger.getParent().getName());
        System.out.println("Handlers:");
        for (java.util.logging.Handler h : logger.getHandlers()) {
            System.out.println("Handler: " + h.getClass().getName() + ", Level: " + h.getLevel());
        }
        for (java.util.logging.Handler h : logger.getParent().getHandlers()) {
            System.out.println("Parent Handler: " + h.getClass().getName() + ", Level: " + h.getLevel());
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            requireNonNull(args);
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME);

            String nameString =
                    argMultimap.getValue(PREFIX_NAME).orElseThrow(() -> new ParseException(Name.MESSAGE_CONSTRAINTS));
            logger.info("Parsed name string: " + nameString);
            checkNameStringEmpty(nameString);
            Name name = ParserUtil.parseName(nameString);
            return new DeleteCommand(name);
        } catch (ParseException ps) {
            throw new ParseException(ps.getMessage());
        }

    }

    private void checkNameStringEmpty(String nameString) throws ParseException {
        if (nameString.isEmpty()) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
    }
}
