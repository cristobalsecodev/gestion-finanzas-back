package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Shared.Email.EmailService;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ResourceConflictException;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseService {

    private final IncomeOrExpenseRepository incomeOrExpenseRepository;

    private final AuthService authService;

    public IncomeOrExpenseService(
            IncomeOrExpenseRepository incomeOrExpenseRepository,
            AuthService authService
    ) {
        this.incomeOrExpenseRepository = incomeOrExpenseRepository;
        this.authService = authService;
    }

    public IncomeOrExpense saveIncomeOrExpense(IncomeOrExpense incomeOrExpense) {

        // Obtenemos el usuario
        User user = this.authService.getInfoUser();

        if(user == null) {

            throw new NotFoundException("User not found");

        }

        // Asignamos el usuario al objeto
        incomeOrExpense.setUser(user);

        // Guardamos y devolvemos el objeto
        return incomeOrExpenseRepository.save(incomeOrExpense);

    }

}
