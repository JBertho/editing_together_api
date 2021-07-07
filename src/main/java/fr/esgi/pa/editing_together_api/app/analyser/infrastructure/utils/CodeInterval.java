package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

public class CodeInterval {
    private SnippetLine begin;
    private SnippetLine end;

    public CodeInterval(SnippetLine begin, SnippetLine end) {
        this.begin = begin;
        this.end = end;
    }

    public SnippetLine getBegin() {
        return begin;
    }

    public void setBegin(SnippetLine begin) {
        this.begin = begin;
    }

    public SnippetLine getEnd() {
        return end;
    }

    public void setEnd(SnippetLine end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CodeInterval{" +
                "begin=" + begin.toString() +
                ", end=" + end.toString() +
                '}';
    }
}
