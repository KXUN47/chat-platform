package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.entity.FileMetadata;
import com.matlasystems.chat.common.entity.Session;
import com.matlasystems.chat.common.protocol.Packet;

/** Convenient entry points for the standard domain validators. */
public final class ValidationUtils {
    private static final UsernameValidator USERNAME = new UsernameValidator();
    private static final PasswordValidator PASSWORD = new PasswordValidator();
    private static final EmailValidator EMAIL = new EmailValidator();
    private static final MessageValidator MESSAGE = new MessageValidator();
    private static final PacketValidator PACKET = new PacketValidator();
    private static final FileValidator FILE = new FileValidator();
    private static final SessionValidator SESSION = new SessionValidator();
    private ValidationUtils() { throw new UnsupportedOperationException("Utility class"); }
    public static ValidationResult validateUsername(String value) { return USERNAME.validate(value); }
    public static ValidationResult validatePassword(String value) { return PASSWORD.validate(value); }
    public static ValidationResult validateEmail(String value) { return EMAIL.validate(value); }
    public static ValidationResult validateMessage(String value) { return MESSAGE.validate(value); }
    public static ValidationResult validatePacket(Packet value) { return PACKET.validate(value); }
    public static ValidationResult validateFile(FileMetadata value) { return FILE.validate(value); }
    public static ValidationResult validateSession(Session value) { return SESSION.validate(value); }
}
