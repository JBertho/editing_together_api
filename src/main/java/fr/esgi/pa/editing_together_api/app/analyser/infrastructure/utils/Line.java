package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

public class Line {

    private String content;
    private int snippet_id;
    private int number_line;
    private int snippet_order;

    public Line(String content, int snippet_id, int number_line, int snippet_order) {
        this.content = content;
        this.snippet_id = snippet_id;
        this.number_line = number_line;
        this.snippet_order = snippet_order;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSnippet_id() {
        return snippet_id;
    }

    public void setSnippet_id(int snippet_id) {
        this.snippet_id = snippet_id;
    }

    public int getNumber_line() {
        return number_line;
    }

    public void setNumber_line(int number_line) {
        this.number_line = number_line;
    }

    public int getSnippet_order() {
        return snippet_order;
    }

    public void setSnippet_order(int snippet_order) {
        this.snippet_order = snippet_order;
    }

    @Override
    public String toString() {
        return "Line{" +
                "content='" + content + '\'' +
                ", snippet_id=" + snippet_id +
                ", number_line=" + number_line +
                ", snippet_order=" + snippet_order +
                '}';
    }
}
