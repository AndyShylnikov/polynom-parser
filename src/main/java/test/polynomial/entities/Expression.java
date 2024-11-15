package test.polynomial.entities;

import jakarta.persistence.*;
/**
 * Entity used to work with expressions table
 *
 */
@Entity
@Table(name = "expressions")
public class Expression {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Passed expression
     */
    @Column(name = "original")
    private String originalExpression;
    /**
     * Reference to polymonial
     */

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polynomial_id")
    private Polynomial polynomial;

    public Expression() {
    }

    public Expression(String originalExpression) {
        this.originalExpression = originalExpression;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalExpression() {
        return originalExpression;
    }

    public void setOriginalExpression(String originalExpression) {
        this.originalExpression = originalExpression;
    }

    public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(Polynomial polynomial) {
        this.polynomial = polynomial;
    }
}