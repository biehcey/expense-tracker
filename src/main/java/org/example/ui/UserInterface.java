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
        // JSON’dan masrafları yükle
        String jsonPath = "jsonfiles/expenses.json";
        expenseService.loadExpensesFromJson(jsonPath);
        System.out.println("📥 JSON dosyasından masraflar başarıyla yüklendi.");
        System.out.println("💸 Masraf Takip Sistemine Hoş Geldiniz");

        boolean devam = true;

        while (devam) {
            System.out.println("\n-----------------------------");
            System.out.println("1 - Masraf Ekle");
            System.out.println("2 - Masrafları Listele");
            System.out.println("3 - Masraf Sil (ID ile)");
            System.out.println("4 - Masrafları Kategoriye Göre Listele");
            System.out.println("5 - Masrafın Tutarını Değiştir (ID ile)");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // dummy newline

            switch (secim) {
                case 1 -> addExpense();
                case 2 -> listExpenses();
                case 3 -> deleteExpense();
                case 4 -> filterByCategory();
                case 5 -> editAmount();
                case 0 -> {
                    devam = false;
                    System.out.println("👋 Çıkış yapılıyor...");
                }
                default -> System.out.println("❌ Geçersiz seçim.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Tutar (₺): ");
        int tutar = scanner.nextInt();
        scanner.nextLine();
        expenseService.addExpense(kategori, tutar);
        System.out.println("✅ Masraf eklendi.");
    }

    private void listExpenses() {
        System.out.println("📋 Tüm Masraflar:");
        expenseService.getExpenseList().forEach(System.out::println);
    }

    private void deleteExpense() {
        System.out.print("Silinecek Masraf ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        expenseService.deleteExpense(id);
    }

    private void filterByCategory() {
        System.out.print("Kategori: ");
        String category = scanner.nextLine();
        expenseService.getExpensesByCategory(category).forEach(System.out::println);
    }

    private void editAmount() {
        System.out.print("Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Amount: ");
        int amount = Integer.parseInt(scanner.nextLine());
        expenseService.updateExpenseAmount(id, amount);
    }
}
