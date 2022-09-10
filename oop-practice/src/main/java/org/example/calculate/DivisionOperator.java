package org.example.calculate;

public class DivisionOperator implements NewArithmeticOperator{
    @Override
    public boolean support(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operend1, PositiveNumber operand2) {

        return operend1.toInt() / operand2.toInt();
    }
}
