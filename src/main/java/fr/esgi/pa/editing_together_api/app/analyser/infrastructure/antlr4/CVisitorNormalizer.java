package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4;

import antlr4.CBaseVisitor;
import antlr4.CParser;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.util.HashMap;
import java.util.Map;


public class CVisitorNormalizer <T> extends CBaseVisitor<T> {

    public StringBuilder sb;
    public Parser parser;
    public Map<String,String> normalizeVariables;
    public int nbVariables;

    public CVisitorNormalizer(Parser parser){
        super();
        sb=new StringBuilder();
        this.parser = parser;
        this.normalizeVariables = new HashMap<String,String>();
        this.nbVariables = 0;
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

    @Override
    public T visitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        String normVariableName = "v" + String.valueOf(nbVariables);
        nbVariables++;
        normalizeVariables.put(ctx.getText(), normVariableName);
        sb.append("(variableDeclaratorId " + normVariableName + ")");
        return null;
    }

    @Override
    public T visitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        String normVariable = normalizeVariables.get(ctx.getText());
        sb.append("(expressionName " + normVariable + ")");
        return null;
    }
}
