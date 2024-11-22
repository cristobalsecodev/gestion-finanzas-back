package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs.FilterIncomeOrExpense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("income-or-expense")
public class IncomeOrExpenseController {

    private final IncomeOrExpenseService incomeOrExpenseService;

    public IncomeOrExpenseController(
            IncomeOrExpenseService incomeOrExpenseService
    ) {
        this.incomeOrExpenseService = incomeOrExpenseService;
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveIncomeOrExpense(@RequestBody IncomeOrExpense incomeOrExpense) {

        return ResponseEntity.ok(incomeOrExpenseService.saveIncomeOrExpense(incomeOrExpense));

    }

    @PostMapping("/filter")
    public ResponseEntity<PagedModel<IncomeOrExpense>> filterIncomeOrExpense(
            @RequestBody FilterIncomeOrExpense filter,
            PagedResourcesAssembler<IncomeOrExpense> assembler
    ) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());

        Page<IncomeOrExpense> resultPage = incomeOrExpenseService.getFilteredIncomeOrExpenses(filter, pageable);

        // Convierte la página en un PagedModel<EntityModel<IncomeOrExpense>>
        PagedModel<EntityModel<IncomeOrExpense>> pagedEntityModel = assembler.toModel(resultPage);

        // Transforma PagedModel<EntityModel<IncomeOrExpense>> a PagedModel<IncomeOrExpense>
        PagedModel<IncomeOrExpense> pagedModel = PagedModel.of(
                pagedEntityModel.getContent().stream()
                        .map(EntityModel::getContent)
                        .toList(),
                pagedEntityModel.getMetadata(),
                pagedEntityModel.getLinks()
        );

        return ResponseEntity.ok(pagedModel);

    }

}
