package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;

/**
 * Lists all consultation in the address book to the user.
 */
public class DeleteConsultationCommand extends Command {
    public static final String COMMAND_WORD = "delete ";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the consultation on the filtered consultation list that "
            + "matches the filtered list index.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_INDEX + "1 ";
    public static final String MESSAGE_CONSULTATION_DELETE_OVERVIEW =
            "Delete consultation successful: index %1$s \n"
            + "Remaining consultations in filtered list shown below.";
    public static final String MESSAGE_INVALID_CONSULTATION_INDEX =
            "The consultation index provided is invalid";

    private final Index targetIndex;


    /**
     * Creates an ViewConsultationCommand to view the specified {@code Patient}
     */
    public DeleteConsultationCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            return new CommandResult(
                    String.format(
                            MESSAGE_CONSULTATION_DELETE_OVERVIEW, targetIndex),
                    COMMAND_TYPE);
        }

        Consultation consultationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteConsultation(consultationToDelete);
        if (lastShownList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_CONSULTATION_DELETE_OVERVIEW, targetIndex.getOneBased()),
                    CommandType.DEFAULT);
        }
        return new CommandResult(String.format(MESSAGE_CONSULTATION_DELETE_OVERVIEW, targetIndex.getOneBased()),
                COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteConsultationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteConsultationCommand) other).targetIndex)); // state check
    }
}
