
package manytomany.ExpenseAnalyzer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseAnalyzerRepository extends JpaRepository<ExpenseAnalyzer, Integer> {
    // This interface will automatically provide basic CRUD operations for ExpenseAnalyzer objects.
}
