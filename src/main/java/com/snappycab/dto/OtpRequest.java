package com.snappycab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ajay Kathwate - 9/9/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpRequest {
    private String username;
    private String phoneNumber;
}
