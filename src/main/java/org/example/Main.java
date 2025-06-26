package org.example;

import org.example.configs.AppConfig;
import org.example.services.ExpenseService;
import org.example.ui.UserInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ExpenseService expenseService = context.getBean(ExpenseService.class);

        UserInterface ui = new UserInterface(expenseService);
        ui.start();

        context.close();
    }
}
