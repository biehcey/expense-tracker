package org.example.ui;

import org.example.services.ExpenseService;

import java.util.Scanner;

public class UserInterface {

    private final ExpenseService expenseService;
    private final Scanner scanner = new Scanner(System.in);

    public UserInterface(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public void start() {
        // Load expenses from JSON file
        String jsonPath = "jsonfiles/expenses.json";
        expenseService.loadExpensesFromJson(jsonPath);
        System.out.println("Expenses successfully loaded from JSON file.");
        System.out.println("Welcome to the Expense Tracking System");

        boolean running = true;

        while (running) {
            System.out.println("\n-----------------------------");
            System.out.println("1 - Add Expense");
            System.out.println("2 - List All Expenses");
            System.out.println("3 - Delete Expense (by ID)");
            System.out.println("4 - List Expenses by Category");
            System.out.println("5 - Update Expense Amount (by ID)");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> listExpenses();
                case 3 -> deleteExpense();
                case 4 -> filterByCategory();
                case 5 -> editAmount();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Amount (₺): ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // consume newline
        expenseService.addExpense(category, amount);
        System.out.println("✅ Expense added successfully.");
    }

    private void listExpenses() {
        System.out.println("📋 All Expenses:");
        expenseService.getExpenseList().forEach(System.out::println);
    }

    private void deleteExpense() {
        System.out.print("Enter Expense ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        expenseService.deleteExpense(id);
    }

    private void filterByCategory() {
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine();
        expenseService.getExpensesByCategory(category).forEach(System.out::println);
    }

    private void editAmount() {
        System.out.print("Enter Expense ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New Amount (₺): ");
        int amount = Integer.parseInt(scanner.nextLine());
        expenseService.updateExpenseAmount(id, amount);
    }
}
