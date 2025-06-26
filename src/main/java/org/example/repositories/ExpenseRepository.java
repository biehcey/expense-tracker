package org.example.repositories;

import org.example.models.Expense;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExpenseRepository {

    private List<Expense> expenseList = new ArrayList<>();


    public void addExpense(Expense expense){
        expenseList.add(expense);
    }

    public void deleteExpense(int id){
        boolean removed = expenseList.removeIf(e -> e.getId() == id);
        if (!removed)
            System.out.println("Absent id: "+id);
    }
    public List<Expense> findAll() {
        return expenseList;
    }
    public Optional<Expense> findById(int id){
        return expenseList.stream().filter(e -> e.getId()==id).findFirst();
    }
    public int getTotalExpenseAmount(){
        int totalExpenseAmount = expenseList.stream().mapToInt(e -> e.getAmount()).sum();
        return totalExpenseAmount;
    }

    public List<Expense> getMatchedByCategory(String category) {
        List<Expense> categoryList = this.expenseList.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return categoryList;
    }

    public void editAmountById(int id, int newAmount){
        for (Expense e : expenseList) {
            if (e.getId() == id) {
                e.setAmount(newAmount);
                break;
            }
        }
    }
}
