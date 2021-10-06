package com.chvey.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static java.util.Objects.nonNull;

@Component
public class AttachmentValidator {
    public boolean save(CommonsMultipartFile[] files) {
        if (nonNull(files)) {
            for (CommonsMultipartFile aFile : files) {
                if (aFile.getContentType() != null && !aFile.getContentType().equals("image/jpeg") &&
                        !aFile.getContentType().equals("image/png") && !aFile.getContentType().equals("application/pdf") &&
                        !aFile.getContentType().equals("application/msword") && !aFile.getContentType().equals("image/pjpeg") &&
                        !aFile.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") &&
                        aFile.getBytes().length > 5 * 1024 * 1024) {
                    return false;
                }
            }
        }
        return true;
    }
}
