package com.allitov.webcontactsmanager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoSuchRecordException extends RuntimeException {

    private final String message;
}
