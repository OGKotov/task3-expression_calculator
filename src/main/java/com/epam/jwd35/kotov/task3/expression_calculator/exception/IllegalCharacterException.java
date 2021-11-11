/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator.exception;

public class IllegalCharacterException extends Exception {

    public IllegalCharacterException() {
    }

    public IllegalCharacterException(String message) {
        super(message);
    }

    public IllegalCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCharacterException(Throwable cause) {
        super(cause);
    }

    public IllegalCharacterException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}