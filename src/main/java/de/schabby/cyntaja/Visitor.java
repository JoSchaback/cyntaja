package de.schabby.cyntaja;

public interface Visitor
{
    void struct(Struct struct);
    void function(Function func);
    Statement statement(Statement statement);
    Expression expression(Expression expression);
    Chainable chainable(Chainable chainable);

}
