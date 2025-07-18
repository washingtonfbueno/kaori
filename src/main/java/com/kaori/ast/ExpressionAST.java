package com.kaori.ast;

import java.util.List;

public interface ExpressionAST {
    public static enum BinaryOperator implements ExpressionAST {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        MODULO("%"),
        AND("&&"),
        OR("||"),
        NOT_EQUAL("!="),
        EQUAL("=="),
        GREATER(">"),
        GREATER_EQUAL(">="),
        LESS("<"),
        LESS_EQUAL("<=");

        public final String label;

        private BinaryOperator(String label) {
            this.label = label;
        }

        public String toString() {
            return this.label;
        }
    }

    public static enum UnaryOperator implements ExpressionAST {
        MINUS("-"),
        NOT("!");

        public final String label;

        private UnaryOperator(String label) {
            this.label = label;
        }

        public String toString() {
            return this.label;
        }
    }

    record BinaryExpression(ExpressionAST left, ExpressionAST right, BinaryOperator operator) implements ExpressionAST {
    }

    record UnaryExpression(ExpressionAST left, UnaryOperator operator) implements ExpressionAST {
    }

    record Assign(ExpressionAST.Identifier left, ExpressionAST right) implements ExpressionAST {
    }

    record Literal(TypeAST type, Object value) implements ExpressionAST {
    }

    record FunctionCall(ExpressionAST callee, List<ExpressionAST> arguments) implements ExpressionAST {
    }

    public class Identifier implements ExpressionAST {
        private final String name;
        private int distance;

        public Identifier(String name) {
            this.name = name;
        }

        public String name() {
            return this.name;
        }

        public int distance() {
            return this.distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
