package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewContactCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.CONTACT;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TYPE + "contact "
            + ": Lists all contacts whose names contain any of "
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: OWNER NRIC\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TYPE + "contact " + PREFIX_NRIC + "S1234567L";

    private final Nric ownerNric;

    /**
     * Creates an ViewContactCommand to view the specified {@code Patient}
     */
    public ViewContactCommand(Nric ownerNric) {
        requireNonNull(ownerNric);
        this.ownerNric = ownerNric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredContactList(new ContactWithNricPredicate(ownerNric));

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                        model.getFilteredContactList().size(), ownerNric),
                COMMAND_TYPE);
    }
}

