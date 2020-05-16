package com.demo.domain.db1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAEntity {
    private Long id;

    private String userName;

    private Long money;

    private String status;
}