package com.example.demo.entities;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Persons", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_seq")
    @SequenceGenerator(name = "entity_id_seq", sequenceName = "global_id_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @Parameter(description = "Индетификатор сотрудника")
    private Long id;

    @Column(name = "first_name")
    @Parameter(description = "Имя сотрудника")
    private String firstName;

    @Column(name = "last_name")
    @Parameter(description = "Фамилия сотрудника", name = "Фамилия сотрудника")
    private String lastName;

    @Column(name = "middle_name")
    @Parameter(description = "Отчество сотрудника")
    private String middleName;

    @Column(name = "date")
    @Parameter(description = "Дата рождения сотрудника", name = "Дата рождения сотрудника")
    private LocalDate date;

    @Column(name = "month_salary")
    @Parameter(description = "Зарплата в месяц")
    private int monthSalary;

    @OneToOne
    private Department department;
}
