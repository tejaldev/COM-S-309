
package manytomany.ExpenseAnalyzers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import manytomany.Persons.Person;

import javax.persistence.*;

@Entity
public class ExpenseAnalyzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destination;
    private int estimatedCost;
    private String selectedCostItem;

    @ManyToOne
    @JsonIgnore
    private Person person;

    // Default constructor
    public ExpenseAnalyzer() {
    }

    // Constructor with parameters
    public ExpenseAnalyzer(String destination, int estimatedCost, String selectedCostItem) {
        this.destination = destination;
        this.estimatedCost = estimatedCost;
        this.selectedCostItem = selectedCostItem;
    }

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getSelectedCostItem() {
        return selectedCostItem;
    }

    public void setSelectedCostItem(String selectedCostItem) {
        this.selectedCostItem = selectedCostItem;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
