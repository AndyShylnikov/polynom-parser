package test.polynomial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.polynomial.entities.Expression;

import java.util.Optional;

/**
 * Repository for working with expression table
 */
@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Integer> {
    /**
     * Method to fetch Expression by entered non-simplified expression
     */
    public Optional<Expression> findByOriginalExpression(String expression);
}
