package com.hsf302.ch4.dto;

import com.hsf302.ch4.dto.StudentCreditDTO;

public record StudentCreditDTO(String studentCode, String fullName,
                               Long courseCount, Long totalCredits) {
}
