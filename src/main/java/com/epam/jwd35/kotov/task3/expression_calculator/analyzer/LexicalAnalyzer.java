/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator.analyzer;

import com.epam.jwd35.kotov.task3.expression_calculator.exception.IllegalCharacterException;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeBuffer;

public interface LexicalAnalyzer {
    LexemeBuffer convertExpression(String textExpression) throws IllegalCharacterException;
}