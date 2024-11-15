package test.polynomial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.polynomial.entities.Term;
/**
 * Repository for working with terms table
 */
@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {
}
