package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4;

import antlr4.CParser;
import antlr4.Java8Parser;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnippetToVisitor {

    private List<Snippet> snippets;
    private LanguageType language;


    public SnippetToVisitor (List<Snippet> snippets, LanguageType language){
        this.snippets = snippets;
        this.language = language;


    }
    public Map<Snippet, String> getMapVisitor (){
        Map<Snippet, String> mapOfVisitor = new HashMap<>();
        for (Snippet snippet : snippets) {
            String visitor = getVisitor (snippet);
            mapOfVisitor.put(snippet, visitor);
        }
        return mapOfVisitor;
    }

    private String getVisitor(Snippet snippet) {


        String result = "";
        if (language == LanguageType.JAVA) {
            String s = "public class Toto{".concat(snippet.getContent()).concat("}");
            CharStream cs = CharStreams.fromString(s);
            System.out.println(s);
            antlr4.Java8Lexer lexer = new antlr4.Java8Lexer (cs);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            antlr4.Java8Parser java8Parser = new antlr4.Java8Parser (commonTokenStream);
            Java8Parser.CompilationUnitContext tree = java8Parser.compilationUnit();
            System.out.println(tree.toStringTree());
            Java8VisitorNormalizer java8vn = new Java8VisitorNormalizer(java8Parser);
            java8vn.visit(tree);
            System.out.println(java8vn.sb.toString());
            result = java8vn.sb.toString();
        }
        else {
            String s = "class Toto{".concat(snippet.getContent()).concat("}");
            CharStream cs = CharStreams.fromString(s);
            antlr4.CLexer lexer = new antlr4.CLexer (cs);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            antlr4.CParser cParser = new CParser(commonTokenStream);
            CParser.CompilationUnitContext tree = cParser.compilationUnit();
            CVisitorNormalizer cvn = new CVisitorNormalizer(cParser);
            cvn.visit(tree);
            result = cvn.sb.toString();

        }
        return result;
    }
}
