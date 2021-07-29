package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4;

import antlr4.Java8BaseVisitor;
import antlr4.Java8Parser;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.util.HashMap;
import java.util.Map;


public class Java8VisitorNormalizer<T> extends Java8BaseVisitor<T> {

    public StringBuilder sb;
    public Parser parser;
    public Map<String,String> normalizeVariables;
    public int nbVariables;

    public Java8VisitorNormalizer(Parser parser){
        super();
        sb=new StringBuilder();
        this.parser = parser;
        this.normalizeVariables = new HashMap<String,String>();
        this.nbVariables = 0;
    }

    @Override
    public T visitMethodDeclarator (Java8Parser.MethodDeclaratorContext ctx) {
        String normVariableName = "method";
        sb.append("(methodDeclarator " + normVariableName + ")");
        return null;
    }

    @Override
    public T visitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
        String normVariableName = "v" + String.valueOf(nbVariables);
        nbVariables++;
        normalizeVariables.put(ctx.getText(), normVariableName);
        sb.append("(variableDeclaratorId " + normVariableName + ")");
        return null;
    }

    @Override
    public T visitExpressionName(Java8Parser.ExpressionNameContext ctx) {
        if (!normalizeVariables.containsKey(ctx.getText())){
            throw new RuntimeException("Missing definition of variable before using it");
        }
        String normVariable = normalizeVariables.get(ctx.getText());
        sb.append("(expressionName " + normVariable + ")");
        return null;
    }

    @Override
    public T visitChildren(RuleNode node) {
        sb.append('(');
        sb.append(Trees.getNodeText(node, parser));
        sb.append(' ');
        T r = super.visitChildren(node);
        sb.append(")");
        return r;
    }

    @Override
    public T visitTerminal(TerminalNode node) {
        Token symbol = node.getSymbol();
        if (symbol != null) {
            sb.append(symbol.getText());
        }
        return null;
    }
}
