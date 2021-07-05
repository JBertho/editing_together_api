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
        return Objects.equals(firstRedundancy, that.firstRedundancy) &&
                Objects.equals(secondRedundancy, that.secondRedundancy);
    }


}


