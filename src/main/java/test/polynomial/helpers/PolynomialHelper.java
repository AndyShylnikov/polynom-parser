package test.polynomial.helpers;


import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for operation on Polynomial expressions(adding, substracting and multiplying).
 * Also provides ability to use expression as function f(x) and solve it with given argument
 */
public class PolynomialHelper {
    /**
     * Adds terms to list inside polynomial
     * @param polynomial - given polynomial
     * @param term - term to add
     */
    public static void addTerm(Polynomial polynomial, Term term) {
        List<Term> termList = polynomial.getTerms();
        termList.add(term);
        polynomial.setTerms(termList);
    }

    /**
     * Adds two polynomials and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of adding
     */
    public static Polynomial add(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        List<Term> termList = result.getTerms();
        termList.addAll(first.getTerms());
        termList.addAll(second.getTerms());
        result.setTerms(termList);
        return simplify(result);
    }

    /**
     * Substract polynomial from another and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of substracting
     */
    public static Polynomial subtract(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        result.getTerms().addAll(first.getTerms());
        for (Term term : second.getTerms()) {
            addTerm(result, new Term(-term.getCoefficient(), term.getExponent()));
        }
        return simplify(result);
    }

    /**
     * Multiplies two polynomials and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of multiplying
     */
    public static Polynomial multiply(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        for (Term term1 : first.getTerms()) {
            for (Term term2 : second.getTerms()) {
                addTerm(result, new Term(term1.getCoefficient() * term2.getCoefficient(),
                        term1.getExponent() + term2.getExponent()));
            }
        }
        return simplify(result);
    }

    /**
     * Simplifies polynomial by reducing terms.
     * Coefficients are added if exponents are same, like [3x^2, 4x^2] will be converted to [7x^2]
     * @param polynomial -given polynomial expression
     * @return  result of simplifying
     */
    public static Polynomial simplify(Polynomial polynomial) {
        Map<Integer, Integer> termMap = new HashMap<>();
        for (Term term : polynomial.getTerms()) {
            int exponent = term.getExponent();
            int coefficient = termMap.getOrDefault(exponent, 0) + term.getCoefficient();
            termMap.put(exponent, coefficient);
        }
        Polynomial simplified = new Polynomial();
        for (Map.Entry<Integer, Integer> entry : termMap.entrySet()) {
            if (entry.getValue() != 0) {
                addTerm(simplified, new Term(entry.getValue(), entry.getKey()));
            }
        }
        simplified.getTerms().sort((t1,t2)->{
            return t2.getExponent() - t1.getExponent();
        });
        return simplified;
    }

    /**
     * Treats given polynomial as f(x) function and solves it by given x value
     * @param polynomial - given expression
     * @param x - given x
     * @return function result
     */
    public static int solve(Polynomial polynomial, int x) {
        int result = 0;
        for (Term term : polynomial.getTerms()) {
            result += term.getCoefficient() * (Math.pow(x, term.getExponent()));
        }
        return result;
    }
}
