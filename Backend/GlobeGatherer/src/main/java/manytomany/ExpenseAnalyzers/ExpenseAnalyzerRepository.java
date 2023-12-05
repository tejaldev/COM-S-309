
package manytomany.ExpenseAnalyzers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ExpenseAnalyzerRepository extends JpaRepository<ExpenseAnalyzer, Long> {
    ExpenseAnalyzer findById(int id);

    @Transactional
    void deleteById(int id);
}
