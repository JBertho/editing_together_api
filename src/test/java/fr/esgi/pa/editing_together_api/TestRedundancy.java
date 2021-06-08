package fr.esgi.pa.editing_together_api;

import fr.esgi.pa.editing_together_api.app.analyser.usecase.CalculateRedundancy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestRedundancy {


    @Test
            public void redundancy() {
        String c1 = "0\n1\n2\n3\n4\n5\n6\n0\n1\n2\n3\n4\n5\n6";
        CalculateRedundancy calculateRedundancy = new CalculateRedundancy();
        String s = calculateRedundancy.get(c1);
        assertEquals("toto", s);

    }

}
