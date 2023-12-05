
package manytomany.ExpenseAnalyzers;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseAnalyzerController {

    @Autowired
    private ExpenseAnalyzerRepository expenseAnalyzerRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseAnalyzer>> getAllExpenses() {
        return ResponseEntity.ok(expenseAnalyzerRepository.findAll());
    }

    @GetMapping(path = "/{SignUpName}")
    public ResponseEntity<List<ExpenseAnalyzer>> getExpenseBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getExpen() == null) {
            return ResponseEntity.notFound().build();
        }

        List<ExpenseAnalyzer> exp = new ArrayList<>(user.getExpen());

        return ResponseEntity.ok(exp);
    }

    @PostMapping("/{SignUpName}")
    public ResponseEntity<ExpenseAnalyzer> createExpenseForSignUpName(
            @PathVariable String SignUpName,
            @RequestBody ExpenseAnalyzer expenseAnalyzer
    ) {
        // Find the user with the provided SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Associate the GoogleMap entry with the user
        expenseAnalyzer.setPerson(user);

        // Save the GoogleMap entry
        ExpenseAnalyzer savedExpenseAnalyzer = expenseAnalyzerRepository.save(expenseAnalyzer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpenseAnalyzer);
    }


}
