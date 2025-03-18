package com.example.model;

import com.example.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    public OrderDTO convertToDTO() {
        return new OrderDTO(this.id, this.amount, this.status);
    }
}