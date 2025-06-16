package com.expense_sharing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_no", nullable = false, unique = true)
    private String phoneNo;

    @ManyToMany(mappedBy = "members")
    private List<Group> groups;

    @OneToMany(mappedBy = "paidBy")
    private List<Expense> expensesPaid;

    @OneToMany(mappedBy = "user")
    private List<Share> shares;

    @OneToMany(mappedBy = "fromUser")
    private List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "toUser")
    private List<Transaction> transactionsReceived;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Balance balance;
}
