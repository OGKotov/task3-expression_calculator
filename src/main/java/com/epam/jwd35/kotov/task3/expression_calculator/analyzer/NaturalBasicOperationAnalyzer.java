/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator.analyzer;

import com.epam.jwd35.kotov.task3.expression_calculator.entity.Lexeme;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeBuffer;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeCharacter;
import com.epam.jwd35.kotov.task3.expression_calculator.entity.LexemeType;
import com.epam.jwd35.kotov.task3.expression_calculator.exception.IllegalCharacterException;

import java.util.ArrayList;
import java.util.List;

public class NaturalBasicOperationAnalyzer implements LexicalAnalyzer {

    @Override
    public LexemeBuffer convertExpression(String expression) throws IllegalCharacterException {
        LexemeBuffer lexemeBuffer;
        List<Lexeme> lexemes;

        lexemes = analyzeExpression(expression);
        lexemeBuffer = new LexemeBuffer(lexemes);
        return lexemeBuffer;
    }

    private List<Lexeme> analyzeExpression(String expression) throws IllegalCharacterException {
        List<Lexeme> lexemes = new ArrayList<>();
        int position;

        position = 0;
        while (position < expression.length()) {
            char c = expression.charAt(position);
            switch (c) {
                case LexemeCharacter.LEFT_BRACKET_CHAR:
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    position++;
                    continue;
                case LexemeCharacter.RIGHT_BRACKET_CHAR:
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    position++;
                    continue;
                case LexemeCharacter.PLUS_CHAR:
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    position++;
                    continue;
                case LexemeCharacter.MINUS_CHAR:
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    position++;
                    continue;
                case LexemeCharacter.MULTIPLY_CHAR:
                    lexemes.add(new Lexeme(LexemeType.OP_MULTIPLY, c));
                    position++;
                    continue;
                case LexemeCharacter.DIVIDE_CHAR:
                    lexemes.add(new Lexeme(LexemeType.OP_DIVIDE, c));
                    position++;
                    continue;
                default:
                    if (c <= LexemeCharacter.MAX_DIGIT_CHAR && c >= LexemeCharacter.MIN_DIGIT_CHAR) {
                        StringBuilder number = new StringBuilder();
                        do {
                            number.append(c);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(position);
                        } while (c <= LexemeCharacter.MAX_DIGIT_CHAR && c >= LexemeCharacter.MIN_DIGIT_CHAR);
                        lexemes.add(new Lexeme(LexemeType.NUMBER, number.toString()));
                    } else {
                        if (c != LexemeCharacter.SPACE_CHAR) {
                            throw new IllegalCharacterException("Unexpected character: " + c
                                    + " at position: " + position);
                        }
                        position++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, LexemeCharacter.EOF_CHAR));
        return lexemes;
    }
}