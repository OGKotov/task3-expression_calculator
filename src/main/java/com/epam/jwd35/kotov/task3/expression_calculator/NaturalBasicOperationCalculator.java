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
import com.epam.jwd35.kotov.task3.expression_calculator.analyzer.LexicalAnalyzer;
import com.epam.jwd35.kotov.task3.expression_calculator.analyzer.NaturalBasicOperationAnalyzer;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.Lexeme;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeBuffer;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeType;
import java.util.ArrayDeque;
import java.util.Deque;

public class NaturalBasicOperationCalculator implements Calculator {
    private static Deque<Boolean> inBracketFlag = new ArrayDeque<>();

    @Override
    public double calculate(String expression) throws IllegalCharacterException {
        LexemeBuffer lexemeBuffer;
        double expressionRezult;
        LexicalAnalyzer analyzer;

        analyzer = new NaturalBasicOperationAnalyzer();
        lexemeBuffer = analyzer.convertExpression(expression);
        expressionRezult = calculateExpression(lexemeBuffer);
        return expressionRezult;
    }

    private double calculateExpression(LexemeBuffer lexemeBuffer) throws IllegalCharacterException {
        Lexeme lexeme;

        lexeme = lexemeBuffer.nextLexeme();
        if (lexeme.getType() == LexemeType.EOF) {
            return 0;
        } else {
            lexemeBuffer.backPosition();
            return calculatePlusAndMinus(lexemeBuffer);
        }
    }

    private double calculatePlusAndMinus(LexemeBuffer lexemeBuffer) throws IllegalCharacterException {
        double value;

        value = calculateMultiplyAndDivide(lexemeBuffer);
        while (true) {
            Lexeme lexeme = lexemeBuffer.nextLexeme();
            switch (lexeme.getType()) {
                case OP_PLUS:
                    value += calculateMultiplyAndDivide(lexemeBuffer);
                    break;
                case OP_MINUS:
                    value -= calculateMultiplyAndDivide(lexemeBuffer);
                    break;
                case EOF:
                    lexemeBuffer.backPosition();
                    return value;
                case RIGHT_BRACKET:
                    if (!inBracketFlag.isEmpty()) {
                        lexemeBuffer.backPosition();
                        return value;
                    } else {
                        throw new IllegalCharacterException("Unexpected token: " + lexeme.getValue()
                                + " at position: " + lexemeBuffer.getPosition());
                    }
                default:
                    throw new IllegalCharacterException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemeBuffer.getPosition());
            }
        }
    }

    private double calculateMultiplyAndDivide(LexemeBuffer lexemeBuffer) throws IllegalCharacterException {
        double value;

        value = defineFactor(lexemeBuffer);
        while (true) {
            Lexeme lexeme = lexemeBuffer.nextLexeme();
            switch (lexeme.getType()) {
                case OP_MULTIPLY:
                    value *= defineFactor(lexemeBuffer);
                    break;
                case OP_DIVIDE:
                    value /= defineFactor(lexemeBuffer);
                    break;
                case RIGHT_BRACKET:
                    if (!inBracketFlag.isEmpty()) {
                        lexemeBuffer.backPosition();
                        return value;
                    } else {
                        throw new IllegalCharacterException("Unexpected token: " + lexeme.getValue()
                                + " at position: " + lexemeBuffer.getPosition());
                    }
                case EOF:
                case OP_PLUS:
                case OP_MINUS:
                    lexemeBuffer.backPosition();
                    return value;
                default:
                    throw new IllegalCharacterException("Unexpected character: " + lexeme.getValue()
                            + " at position: " + lexemeBuffer.getPosition());
            }
        }
    }

    private double defineFactor(LexemeBuffer lexemeBuffer) throws IllegalCharacterException {
        Lexeme lexeme;

        lexeme = lexemeBuffer.nextLexeme();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                inBracketFlag.add(Boolean.TRUE);
                double value = calculatePlusAndMinus(lexemeBuffer);
                lexeme = lexemeBuffer.nextLexeme();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalCharacterException("Unexpected character: " + lexeme.getValue()
                            + " at position: " + lexemeBuffer.getPosition());
                } else {
                    inBracketFlag.pollLast();
                }
                return value;
            default:
                throw new IllegalCharacterException("Unexpected character: " + lexeme.getValue()
                        + " at position: " + lexemeBuffer.getPosition());
        }
    }
}