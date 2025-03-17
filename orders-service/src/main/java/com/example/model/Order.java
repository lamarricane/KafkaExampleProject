package com.example.model;

import com.example.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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

    @ElementCollection
    @Column(name = "article_numbers", columnDefinition = "bigint[]", nullable = false)
    private Set<Long> articleNumbers;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    public OrderDTO convertToDTO() {
        return new OrderDTO(this.id, this.articleNumbers, this.amount, this.orderDate, this.status);
    }
}