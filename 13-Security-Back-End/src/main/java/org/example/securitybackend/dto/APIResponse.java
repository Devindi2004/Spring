package org.example.securitybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class APIResponse {
    private int status;
    private String massage;
    private Object data;
}
