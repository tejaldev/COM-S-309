
package manytomany.ExpenseAnalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenseAnalyzer")
public class ExpenseAnalyzerController {

    @Autowired
    private ExpenseAnalyzerRepository expenseAnalyzerRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseAnalyzer>> getAllExpenses() {
        return ResponseEntity.ok(expenseAnalyzerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseAnalyzer> getExpenseById(@PathVariable int id) {
        Optional<ExpenseAnalyzer> expense = expenseAnalyzerRepository.findById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ExpenseAnalyzer> addExpense(@RequestBody ExpenseAnalyzer expenseAnalyzer) {
        return ResponseEntity.ok(expenseAnalyzerRepository.save(expenseAnalyzer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseAnalyzer> updateExpense(@PathVariable int id, @RequestBody ExpenseAnalyzer expenseDetails) {
        return expenseAnalyzerRepository.findById(id)
                .map(existingExpense -> {
                    existingExpense.setDestination(expenseDetails.getDestination());
                    existingExpense.setEstimatedCost(expenseDetails.getEstimatedCost());
                    existingExpense.setSelectedCostItem(expenseDetails.getSelectedCostItem());
                    return ResponseEntity.ok(expenseAnalyzerRepository.save(existingExpense));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable int id) {
        if (expenseAnalyzerRepository.existsById(id)) {
            expenseAnalyzerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
