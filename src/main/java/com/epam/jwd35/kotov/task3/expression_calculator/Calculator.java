/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator;

import com.epam.jwd35.kotov.task3.expression_calculator.exception.IllegalCharacterException;

public interface Calculator {

    public double calculate(String expression) throws IllegalCharacterException;
}