package com.AnimalShelter.dtos.request;

import com.AnimalShelter.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String email;
    String password;
    ERole role;
}
