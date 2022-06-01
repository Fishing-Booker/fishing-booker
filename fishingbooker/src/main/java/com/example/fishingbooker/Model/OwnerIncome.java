package com.example.fishingbooker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "owner_income")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerIncome {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "ownerIncomeSeqGen", sequenceName = "ownerIncomeSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownerIncomeSeqGen")
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User owner;

    @Column(name = "income")
    private double income;

    @Column(name = "system_income")
    private double systemIncome;
}
