package com.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
public class OrderDTO {
    private long id;
    private Set<Long> articleNumbers;
    private BigDecimal amount;
    private LocalDate orderDate;
    private String status;

    @JsonCreator
    public OrderDTO(@JsonProperty("id") long id,
                    @JsonProperty("articleNumbers") Set<Long> articleNumbers,
                    @JsonProperty("amount") BigDecimal amount,
                    @JsonProperty("orderDate") LocalDate orderDate,
                    @JsonProperty("status") String status) {
        this.id = id;
        this.articleNumbers = articleNumbers;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
    }
}