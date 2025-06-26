package org.example.services;

import org.example.readers.JsonReader;
import org.example.utils.IdCreator;
import org.example.models.Expense;
import org.example.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final IdCreator idCreator;

    public ExpenseService(ExpenseRepository expenseRepository, IdCreator idCreator){
        this.expenseRepository = expenseRepository;
        this.idCreator = idCreator;
    }
    public void addExpense(String category, int amount){
        int id = idCreator.generateId();
        Expense expense = new Expense(id, category, amount);
        expenseRepository.addExpense(expense);
    }
    public List<Expense> getExpenseList(){
        return expenseRepository.findAll();
    }
    public void deleteExpense(int id){
        expenseRepository.deleteExpense(id);
    }
    public List<Expense> getExpensesByCategory(String category){
        return expenseRepository.getMatchedByCategory(category);
    }
    public void updateExpenseAmount(int id, int amount){
        expenseRepository.editAmountById(id, amount);
    }
    public void loadExpensesFromJson(String filePath){
        List<Expense> expenses = JsonReader.read(filePath);
        expenses.forEach(e -> addExpense(e.getCategory(), e.getAmount()));
    }
}
