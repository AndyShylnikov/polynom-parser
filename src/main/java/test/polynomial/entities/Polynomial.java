package test.polynomial.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "polynomial", cascade = CascadeType.ALL)
    private List<Term> terms = new ArrayList<>();

    /**
     * Reference to expressions table
     */
    @OneToMany(mappedBy = "polynomial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expression> expressions;

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

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
