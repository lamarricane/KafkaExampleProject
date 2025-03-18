package com.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderDTO {
    private long id;
    private BigDecimal amount;
    private String status;

    @JsonCreator
    public OrderDTO(@JsonProperty("id") long id,
                    @JsonProperty("amount") BigDecimal amount,
                    @JsonProperty("status") String status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }
}