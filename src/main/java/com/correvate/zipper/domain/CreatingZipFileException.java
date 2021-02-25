package com.correvate.zipper.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "There was a problem with creating zip file")
public class CreatingZipFileException extends RuntimeException {
    public CreatingZipFileException(final String message, Exception e) {
        super(message, e);
    }
}