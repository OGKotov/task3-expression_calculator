/*
 * Java Web Development
 * JWD 35
 * Oleg Kotov
 * Task3
 * Information Handling
 * From 06-11-2021 to 11-11-2021
 */
package com.epam.jwd35.kotov.task3.expression_calculator;

import com.epam.jwd35.kotov.task3.expression_calculator.entity.CalculatorType;
import java.util.HashMap;
import java.util.Map;

public class CalculatorFactory {
    public static final CalculatorFactory instance = new CalculatorFactory();
    private Map<CalculatorType, Calculator> calculatorMap = new HashMap<>();

    private CalculatorFactory() {
        calculatorMap.put(CalculatorType.NATURAL_BASIC_OPERATION_CALCULATOR,
                new NaturalBasicOperationCalculator());
    }

    public Calculator getCalculator(CalculatorType calculatorType) {
        Calculator calculator;

        calculator = calculatorMap.get(calculatorType);
        return calculator;
    }
}
