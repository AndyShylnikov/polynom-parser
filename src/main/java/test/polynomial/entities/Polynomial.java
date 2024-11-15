package test.polynomial.entities;


import jakarta.persistence.*;

import java.util.List;
/**
 * Polynomial class represents wrapper on list of terms
 */
@Entity
@Table(name = "polymonials")
public class Polynomial {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Represents simplified expression
     */
    private String simplified;

    /**
     * terms list
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "polynomial_id")
    private List<Term> terms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSimplified() {
        return simplified;
    }

    public void setSimplified(String simplified) {
        this.simplified = simplified;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
}
