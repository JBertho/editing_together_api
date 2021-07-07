package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.Objects;

public class CodeIntervalRedundancy {
    private CodeInterval firstRedundancy;
    private CodeInterval secondRedundancy;


    public CodeIntervalRedundancy(CodeInterval firstRedundancy, CodeInterval secondRedundancy) {
        this.firstRedundancy = firstRedundancy;
        this.secondRedundancy = secondRedundancy;
    }

    public CodeInterval getFirstRedundancy() {
        return firstRedundancy;
    }

    public void setFirstRedundancy(CodeInterval firstRedundancy) {
        this.firstRedundancy = firstRedundancy;
    }

    public CodeInterval getSecondRedundancy() {
        return secondRedundancy;
    }

    public void setSecondRedundancy(CodeInterval secondRedundancy) {
        this.secondRedundancy = secondRedundancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeIntervalRedundancy that = (CodeIntervalRedundancy) o;
        return Objects.equals(firstRedundancy.getBegin(), that.firstRedundancy.getBegin()) &&
                Objects.equals(secondRedundancy.getBegin(), that.secondRedundancy.getBegin()) &&
                Objects.equals(firstRedundancy.getEnd(), that.firstRedundancy.getEnd()) &&
                Objects.equals(secondRedundancy.getEnd(), that.secondRedundancy.getEnd());
    }

    @Override
    public String toString() {
        String s = String.format("Duplication between snippet %s line %s - snippet %s line %s and snippet %s line %s - snippet %s line %s",
                this.firstRedundancy.getBegin().getSnippet_id(), this.firstRedundancy.getBegin().getLine(),
                this.firstRedundancy.getEnd().getSnippet_id(), this.firstRedundancy.getEnd().getLine(),
                this.secondRedundancy.getBegin().getSnippet_id(), this.secondRedundancy.getBegin().getLine(),
                this.secondRedundancy.getEnd().getSnippet_id(), this.secondRedundancy.getEnd().getLine());

        return s;
    }
}


