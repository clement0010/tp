package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.testresult.TestResult;
import seedu.address.model.testresult.TestResultWithNricPredicate;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;

public class AddTestResultCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the results of a test taken for a patient in the MedBook. "
            + "Parameters: "
            + PREFIX_NRIC + "OWNER_NRIC "
            + PREFIX_TESTDATE + "TEST_DATE"
            + PREFIX_MEDICALTEST + "MEDICAL_TEST"
            + PREFIX_RESULT + "TEST_RESULT";

    public static final String MESSAGE_SUCCESS = "New test result added: %1$s";
    public static final String MESSAGE_DUPLICATE_TESTRESULT =
            "This test result already exists in patient test results list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in MedBook";

    // Identifier
    private final Nric ownerNric;

    private final TestResult toAdd;

    /**
     * Creates an AddTestResultCommand to add the specified {@code TestResult}
     */
    public AddTestResultCommand(Nric ownerNric, TestResult testResult) {
        requireNonNull(ownerNric);
        requireNonNull(testResult);
        toAdd = testResult;
        this.ownerNric = ownerNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasTestResult(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TESTRESULT);
        }

        model.addTestResult(toAdd);
        model.updateFilteredTestResultList(new TestResultWithNricPredicate(ownerNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}
