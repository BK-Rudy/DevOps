package com.infnet.expenseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "\"EXPENSE\"")
public class Expense {
    @Id
    private Long id;
    private String name;
    private String description;
    @Column("EXPENSE_VALUE")
    private Float expenseValue;
    private Float discount;
    private Float ipi;
    private Float icms;
    private Integer installments;
}
