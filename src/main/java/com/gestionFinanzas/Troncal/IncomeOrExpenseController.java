package com.gestionFinanzas.Troncal;

import com.gestionFinanzas.Troncal.DTOs.FilterIncomeOrExpense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteIncomeOrExpense(@PathVariable("id") Long id) {

        String message = incomeOrExpenseService.deleteIncomeOrExpense(id);

        Map<String, Object> response = new HashMap<>();

        response.put("message", message);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/filter")
    public ResponseEntity<PagedModel<IncomeOrExpense>> filterIncomeOrExpense(
            @RequestBody FilterIncomeOrExpense filter,
            PagedResourcesAssembler<IncomeOrExpense> assembler
    ) {

        Sort sort = filter.getSortDir().equalsIgnoreCase("desc")
                ? Sort.by(Sort.Order.desc("date"))
                : Sort.by(Sort.Order.asc("date"));

        Pageable pageable = filter.getAllRecords()
                ? Pageable.unpaged()
                : PageRequest.of(filter.getPage(), filter.getSize(), sort);

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
