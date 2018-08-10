package de.schabby.cyntaja;

public class Field {

    private Variable variable;
    private Expression initExp;

    public Field(Variable variable, Expression initExp) {
        this.variable = variable;
        this.initExp = initExp;
        //this.variable.setClassField(true);
    }

    public Field(Variable variable)
    {
        this(variable, null);
    }

    public Variable getVariable() {
        return variable;
    }

    public Expression getInitExp() {
        return initExp;
    }

    public void setInitExp(Expression initExp) {
        this.initExp = initExp;
    }
}
