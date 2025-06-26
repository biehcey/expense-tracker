package org.example.models;

public class Expense {

    private int id;
    private String category;
    private int amount;

    public Expense(int id, String category, int amount) {
        this.id = id;
        this.category = category;
        this.amount = amount;
    }
    public Expense(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
