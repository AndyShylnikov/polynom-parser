package test.polynomial.helpers;



import test.polynomial.entities.Polynomial;
import test.polynomial.entities.Term;

import java.util.List;

/**
 * Helper used for converting Term and Polynomial objects to String format
 */
public class StringHelper {
    /**
     * Converts Term to String, with adding needed spaces (for better readability.
     * Possible outputs are(a represents coefficient, b - exponent):
     *  x
     *   - x
     *  a
     *   - a
     *  a*x
     *   - a*x
     *  a*x^b
     *   - a*x^b
     *
     * @param term - instance of Term class
     * @return converted value
     *
     */
    public static String termToString(Term term) {
        StringBuilder returnValue = new StringBuilder();
        int coefficient = term.getCoefficient();
        if (coefficient < 0) {
            returnValue.append(" - "); // add minus symbol
        }
        if (term.getExponent() == 0) { // exponent is zero, so no 'x' or 'x^exponent' is needed
            returnValue.append(Math.abs(coefficient));
        } else if (term.getExponent() == 1) { // exponent is equal to one, so we return `a*x`, `x`, ` - x` or ` - a*x`
            returnValue.append(coefficient != 1 ? Math.abs(coefficient) + "*x" : "x");
        } else { //Exponent is greater than 1. Output is `a*x^b`, `x^b`,` - a*x^b`, ` - x^b`,
            returnValue.append(Math.abs(coefficient) != 1 ? Math.abs(coefficient) + "*x^" + term.getExponent() : "x^" + term.getExponent());
        }
        return returnValue.toString();
    }

    /**
     * Converts Polynomial to String, with sorting terms by exponent.
     * @param polynomial - instance of Term class
     * @return converted value
     *
     */
    public static String polymonialToString(Polynomial polynomial) {
        List<Term> termsList = polynomial.getTerms();
        termsList.sort((t1, t2) -> Integer.compare(t2.getExponent(), t1.getExponent()));
        StringBuilder returnValue = new StringBuilder();
        for (Term term : termsList) {
            if (returnValue.length() > 0 && term.getCoefficient() > 0)
                returnValue.append(" + "); //if coefficient is greater than 0, we add ` + `, otherwise we will use ` - ` from term representation
            returnValue.append(termToString(term));
        }
        return returnValue.toString();
    }
}
