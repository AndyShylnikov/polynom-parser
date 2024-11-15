package test.polynomial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;
import test.polynomial.helpers.StringHelper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StringHelperTest {
    @Test
    void testTermToString1() {
        Term term = new Term();
        assertEquals(StringHelper.termToString(term), "0");
    }

    @Test
    void testTermToString2() {
        Term term = new Term();
        term.setExponent(2);
        assertEquals(StringHelper.termToString(term), "0");
    }

    @Test
    void testTermToString3() {
        Term term = new Term();
        term.setCoefficient(2);
        assertEquals(StringHelper.termToString(term), "2");
    }

    @Test
    void testTermToString4() {
        Term term = new Term();
        term.setCoefficient(-2);
        assertEquals(StringHelper.termToString(term), " - 2");
    }

    @Test
    void testTermToString5() {
        Term term = new Term();
        term.setCoefficient(1);
        term.setExponent(2);
        assertEquals(StringHelper.termToString(term), "x^2");
    }

    @Test
    void testTermToString6() {
        Term term = new Term(2, 1);
        assertEquals(StringHelper.termToString(term), "2*x");
    }

    @Test
    void testTermToString7() {
        Term term = new Term(1, 1);
        assertEquals(StringHelper.termToString(term), "x");
    }

    @Test
    void testTermToString8() {
        Term term = new Term(3, 2);
        assertEquals(StringHelper.termToString(term), "3*x^2");
    }

    @Test
    void testPolymonialToString1() {
        Polynomial polynomial = new Polynomial();

        assertEquals(StringHelper.polymonialToString(polynomial), "");
    }

    @Test
    void testPolymonialToString2() {
        Polynomial polynomial = new Polynomial();
        List<Term> terms = Stream.<Term>builder().add(new Term(1, 0)).add(new Term(2, 4)).
                add(new Term(-2, 5)).build().collect(Collectors.toList());
        polynomial.setTerms(terms);
        assertEquals(StringHelper.polymonialToString(polynomial), "-2*x^5 + 2*x^4 + 1");
    }

    @Test
    void testPolymonialToString3() {
        Polynomial polynomial = new Polynomial();
        List<Term> terms = Stream.<Term>builder().add(new Term(1, 1)).build().collect(Collectors.toList());
        polynomial.setTerms(terms);
        assertEquals(StringHelper.polymonialToString(polynomial), "x");
    }

    @Test
    void testPolymonialToString4() {
        Polynomial polynomial = new Polynomial();
        List<Term> terms = Stream.<Term>builder().
                add(new Term(1, 2)).
                add(new Term(-4, 0)).
                build().collect(Collectors.toList());
        polynomial.setTerms(terms);
        assertEquals(StringHelper.polymonialToString(polynomial), "x^2 - 4");
    }

    @Test
    void testPolymonialToString5() {
        Polynomial polynomial = new Polynomial();
        List<Term> terms = Stream.<Term>builder().
                add(new Term(1, 2)).
                add(new Term(4, 0)).
                build().collect(Collectors.toList());
        polynomial.setTerms(terms);
        assertEquals(StringHelper.polymonialToString(polynomial), "x^2 + 4");
    }
}
