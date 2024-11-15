package test.polynomial.entities;


import jakarta.persistence.*;

/**
 * Represents single term in polynomial.
 * In a*x^b coefficient will be responsible for a, and exponent for b
 */
@Entity
@Table(name = "terms")
public class Term {
    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term() {
    }

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Multiplier coefficient
     * In a*x^b coefficient will be responsible for a
     */
    private int coefficient;
    /**
     * Exponent of x
     * In a*x^b term exponent is responsibe for b
     */
    private int exponent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
}
