package test.polynomial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.polynomial.entities.Polynomial;

/**
 * Repository for working with polynomials table
 */
@Repository
public interface PolynomialRepository extends JpaRepository<Polynomial,Integer> {
}
