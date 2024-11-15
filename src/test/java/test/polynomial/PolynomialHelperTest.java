package test.polynomial.helpers;

import org.junit.jupiter.api.Test;
import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialHelperTest {

    @Test
    void testAddPolynomials() {
        Polynomial p1 = new Polynomial();
        PolynomialHelper.addTerm(p1, new Term(3, 2));
        PolynomialHelper.addTerm(p1, new Term(5, 1));

        Polynomial p2 = new Polynomial();
        PolynomialHelper.addTerm(p2, new Term(5, 2));
        PolynomialHelper.addTerm(p2, new Term(-4, 1));
        PolynomialHelper.addTerm(p2, new Term(2, 0));

        Polynomial result = PolynomialHelper.add(p1, p2);

        assertNotNull(result);
        assertEquals(3, result.getTerms().size());
        assertEquals(8, result.getTerms().get(0).getCoefficient()); // 3x^2 + 5x^2
        assertEquals(1, result.getTerms().get(1).getCoefficient()); // 5x + (-4x)
        assertEquals(2, result.getTerms().get(2).getCoefficient()); // constant term
    }

    @Test
    void testSubtractPolynomials() {
        Polynomial p1 = new Polynomial();
        PolynomialHelper.addTerm(p1, new Term(3, 2));
        PolynomialHelper.addTerm(p1, new Term(4, 1));

        Polynomial p2 = new Polynomial();
        PolynomialHelper.addTerm(p2, new Term(5, 2));
        PolynomialHelper.addTerm(p2, new Term(-4, 1));
        PolynomialHelper.addTerm(p2, new Term(2, 0));

        Polynomial result = PolynomialHelper.subtract(p1, p2);

        assertNotNull(result);
        assertEquals(3, result.getTerms().size());
        assertEquals(-2, result.getTerms().get(0).getCoefficient()); // 3x^2 - 5x^2
        assertEquals(8, result.getTerms().get(1).getCoefficient()); // 4x - (-4x)
        assertEquals(-2, result.getTerms().get(2).getCoefficient()); // 0 - 2
    }

    @Test
    void testMultiplyPolynomials() {
        Polynomial p1 = new Polynomial();
        PolynomialHelper.addTerm(p1, new Term(2, 1)); // 2x
        PolynomialHelper.addTerm(p1, new Term(3, 0)); // 3

        Polynomial p2 = new Polynomial();
        PolynomialHelper.addTerm(p2, new Term(4, 1)); // 4x
        PolynomialHelper.addTerm(p2, new Term(5, 0)); // 5

        Polynomial result = PolynomialHelper.multiply(p1, p2);

        assertNotNull(result);
        assertEquals(3, result.getTerms().size());
        assertEquals(8, result.getTerms().get(0).getCoefficient()); // 2x * 4x -> 8x^2
        assertEquals(22, result.getTerms().get(1).getCoefficient()); // 2x * 5 + 3x * 4 -> 22x
        assertEquals(15, result.getTerms().get(2).getCoefficient()); // 3 * 5 -> 15
    }

    @Test
    void testSimplifyPolynomial() {
        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(3, 2));
        PolynomialHelper.addTerm(polynomial, new Term(5, 2));
        PolynomialHelper.addTerm(polynomial, new Term(4, 1));
        PolynomialHelper.addTerm(polynomial, new Term(-4, 1));
        PolynomialHelper.addTerm(polynomial, new Term(2, 0));

        Polynomial result = PolynomialHelper.simplify(polynomial);

        assertNotNull(result);
        assertEquals(2, result.getTerms().size());
        assertEquals(8, result.getTerms().get(0).getCoefficient()); // 3x^2 + 5x^2
        assertEquals(2, result.getTerms().get(1).getCoefficient()); // constant term
    }

    @Test
    void testSolvePolynomial() {
        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(2, 2)); // 2x^2
        PolynomialHelper.addTerm(polynomial, new Term(3, 1)); // 3x
        PolynomialHelper.addTerm(polynomial, new Term(5, 0)); // 5

        int result = PolynomialHelper.solve(polynomial, 2); // Solve 2x^2 + 3x + 5 for x = 2

        assertEquals(19, result); // 2(2^2) + 3(2) + 5 = 19
    }

    @Test
    void testAddTermToEmptyPolynomial() {
        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(7, 3));

        assertNotNull(polynomial.getTerms());
        assertEquals(1, polynomial.getTerms().size());
        Term term = polynomial.getTerms().get(0);
        assertEquals(7, term.getCoefficient());
        assertEquals(3, term.getExponent());
    }

    @Test
    void testSimplifyWithZeroCoefficient() {
        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(3, 2));
        PolynomialHelper.addTerm(polynomial, new Term(-3, 2));

        Polynomial result = PolynomialHelper.simplify(polynomial);

        assertNotNull(result);
        assertEquals(0, result.getTerms().size()); // 3x^2 - 3x^2 cancels out
    }
}
