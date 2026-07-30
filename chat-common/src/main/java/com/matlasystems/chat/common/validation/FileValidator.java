package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.common.entity.FileMetadata;
import com.matlasystems.chat.common.util.StringUtils;

/** Validates file metadata before a file transfer begins. */
public final class FileValidator implements Validator<FileMetadata> {
    @Override public ValidationResult validate(FileMetadata file) {
        ValidationResult result = ValidationResult.valid();
        if (file == null) { return result.addError("file", "required", "File metadata is required"); }
        String name = StringUtils.defaultIfBlank(file.getOriginalFileName(), file.getFileName());
        if (!com.matlasystems.chat.common.util.ValidationUtils.isValidFileName(name)) {
            result.addError("fileName", "format", "File name is invalid");
        }
        if (file.getFileSize() < 0 || file.getFileSize() > ValidationConstants.MAX_FILE_SIZE_BYTES) {
            result.addError("fileSize", "range", "File size is outside the allowed range");
        }
        if (!com.matlasystems.chat.common.util.ValidationUtils.isValidContentType(file.getContentType())) {
            result.addError("contentType", "format", "Content type is invalid");
        }
        if (file.getSenderId() == null) { result.addError("senderId", "required", "File sender is required"); }
        return result;
    }
}
