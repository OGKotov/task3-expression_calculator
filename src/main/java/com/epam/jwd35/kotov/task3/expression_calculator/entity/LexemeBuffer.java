/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator.entity;

import java.util.List;

public class LexemeBuffer {
    private int position;
    private List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.position = -1;
        this.lexemes = lexemes;
    }

    public Lexeme backLexeme() {
        return lexemes.get(--position);
    }

    public Lexeme nextLexeme() {
        return lexemes.get(++position);
    }

    public int backPosition() {
        position--;
        return position;
    }

    public int nextPosition() {
        position++;
        return position;
    }

    public int getPosition() {
        return position;
    }
}