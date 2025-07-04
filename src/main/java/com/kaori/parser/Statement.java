package com.kaori.parser;

import java.util.ArrayList;
import java.util.List;

import com.kaori.visitor.Visitor;

public abstract class Statement {
    private int line;

    private Statement() {
        this.line = 0;
    }

    public int getLine() {
        return line;
    }

    public Statement setLine(int line) {
        this.line = line;

        return this;
    }

    public abstract <T> void acceptVisitor(Visitor<T> visitor);

    public static class Print extends Statement {
        public final Expression expression;

        public Print(Expression expression) {
            this.expression = expression;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitPrintStatement(this);
        }
    }

    public static class Expr extends Statement {
        public final Expression expression;

        public Expr(Expression expression) {
            this.expression = expression;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitExpressionStatement(this);
        }
    }

    public static class Variable extends Statement {
        public final Expression left;
        public final Expression right;
        public final KaoriType type;

        public Variable(Expression left, Expression right, KaoriType type) {
            this.left = left;
            this.right = right;
            this.type = type;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitVariableStatement(this);
        }

    }

    public static class Block extends Statement {
        public final List<Statement> statements;

        public Block(List<Statement> statements) {
            this.statements = statements;
        }

        public Block() {
            this.statements = new ArrayList<>();
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitBlockStatement(this);
        }
    }

    public static class If extends Statement {
        public final Expression condition;
        public final Statement thenBranch;
        public final Statement elseBranch;

        public If(Expression condition, Statement thenBranch, Statement elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitIfStatement(this);
        }

    }

    public static class WhileLoop extends Statement {
        public final Expression condition;
        public final Statement block;

        public WhileLoop(Expression condition, Statement block) {
            this.condition = condition;
            this.block = block;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitWhileLoopStatement(this);
        }
    }

    public static class ForLoop extends Statement {
        public final Statement variable;
        public final Expression condition;
        public final Statement increment;
        public final Statement block;

        public ForLoop(Statement variable, Expression condition, Statement increment, Statement block) {
            this.variable = variable;
            this.condition = condition;
            this.increment = increment;
            this.block = block;
        }

        @Override
        public <T> void acceptVisitor(Visitor<T> visitor) {
            visitor.visitForLoopStatement(this);
        }
    }
}
