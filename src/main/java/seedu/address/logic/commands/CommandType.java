package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.consultation.ViewConsultationCommand;
import seedu.address.logic.parser.consultations.AddConsultationCommandParser;
import seedu.address.logic.parser.consultations.ViewConsultationCommandParser;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.ViewContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.ViewContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.prescription.AddPrescriptionCommandParser;

public enum CommandType {
    DEFAULT, CONTACT, MEDICAL, CONSULTATION, PRESCRIPTION, TEST;

    public static final String MESSAGE_CONSTRAINTS = "Command type should be either contact, medical, "
            + "consultation, prescription or test";

    private static String getFirstPrefixType(String text) {
        int index = text.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }
    /**
     * Parses {@code String commandType} into a {@code CommandType}.
     */
    public static CommandType parseCommandType(String commandType) throws ParseException {
        requireNonNull(commandType);
        String trimmedCommandType = getFirstPrefixType(commandType.trim());

        System.out.println("CommandType2: "+trimmedCommandType);

        switch (trimmedCommandType) {
            case "contact":
                System.out.println("trimmedCommandType: "+trimmedCommandType);
                return CONTACT;
            case "medical":
                return MEDICAL;
            case "consultation":
                System.out.println("trimmedCommandType: "+trimmedCommandType);
                return CONSULTATION;
            case "prescription":
                return PRESCRIPTION;
            case "test":
                return TEST;
            default:
                throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns command related to adding information to patients in Medbook.
     *
     * @param commandType user input command type
     * @param arguments user input arguments
     * @return the command based on the user input
     */
    public static Command parseAddCommandType(String commandType, String arguments) throws ParseException {
        requireNonNull(commandType);
        CommandType parsedCommandType = parseCommandType(commandType);

        switch(parsedCommandType) {
            case CONTACT:
                System.out.println(1);
                return new AddContactCommandParser().parse(arguments);
            case MEDICAL:
                throw new ParseException("WIP: Medical type");
            case CONSULTATION:
                System.out.println(2);
                return new AddConsultationCommandParser().parse(arguments);
            case PRESCRIPTION:
                return new AddPrescriptionCommandParser().parse(arguments);
            case TEST:
                throw new ParseException("WIP: Test type");
            default:
                throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns command related to adding information to patients in Medbook.
     *
     * @param commandType user input command type
     * @param arguments user input arguments
     * @return the command based on the user input
     */
    public static Command parseViewCommandType(String commandType, String arguments) throws ParseException {
        requireNonNull(commandType);
        CommandType parsedCommandType = parseCommandType(commandType);

        switch(parsedCommandType) {
            case CONTACT:
                System.out.println(3);
                return new ViewContactCommandParser().parse(arguments);
            case MEDICAL:
                throw new ParseException("WIP: Medical type");
            case CONSULTATION:
                System.out.println(4);
                return new ViewConsultationCommandParser().parse(arguments);
            case PRESCRIPTION:
                throw new ParseException("WIP: Prescription type");
            case TEST:
                throw new ParseException("WIP: Test type");
            default:
                throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }
}
