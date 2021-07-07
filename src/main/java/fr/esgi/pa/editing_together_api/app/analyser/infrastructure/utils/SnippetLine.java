package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.Objects;

public class SnippetLine {
    private int snippet_id;
    private int line;
    private int snippet_order;

    public SnippetLine(int snippet_id, int snippet_order, int line) {
        this.snippet_id = snippet_id;
        this.line = line;
        this.snippet_order = snippet_order;
    }

    public int getSnippet_id() {
        return snippet_id;
    }

    public void setSnippet_id(int snippet_id) {
        this.snippet_id = snippet_id;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getSnippet_order() {
        return snippet_order;
    }

    public void setSnippet_order(int snippet_order) {
        this.snippet_order = snippet_order;
    }

    @Override
    public String toString() {
        return "SnippetLine{" +
                "snippet_id=" + snippet_id +
                ", line=" + line +
                '}';
    }

    public boolean isAfter (SnippetLine sl) {
        if (this.snippet_order > sl.snippet_order) {
            return true;
        }
        if (this.snippet_order < sl.snippet_order) {
            return false;
        }
        if (this.line > sl.line){
            return true;
        }
        return false;
    }

    public boolean isBetween (SnippetLine sl1, SnippetLine sl2) {
        if (this.isAfter(sl1) && sl2.isAfter(this))
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnippetLine that = (SnippetLine) o;
        return snippet_id == that.snippet_id &&
                line == that.line &&
                snippet_order == that.snippet_order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(snippet_id, line, snippet_order);
    }
}
