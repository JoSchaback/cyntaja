package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Block implements Statement {

    private List<Statement> statements = new ArrayList<>();

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.println("{");
        for( Statement s: statements ) {
            pw.print(indentation+"\t");
            s.toC(pw, indentation+"\t");
            pw.println(";");
        }
        pw.print(indentation + "}");

    }

    @Override
    public void visit(Visitor visitor) {

        for( int i = 0; i < statements.size(); i++ )
        {
            Statement oldStatement = statements.remove(i);
            Statement newStatement = visitor.statement(oldStatement);
            statements.add(i, newStatement);

            newStatement.visit(visitor);
        }
    }

    /**
     * Shortcut for statements.add(statement)
     * @param statement the statement to add
     */
    public void add(Statement statement) {
        statements.add(statement);
    }
}

