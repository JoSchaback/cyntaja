package de.schabby.cyntaja;

public interface Chainable extends Expression {

    Chainable getNext();
    Chainable getPrevious();

}
