package com.yunsseong.chuckbodymainserver.dto;

import java.time.LocalDate;

public record PatientCreateRequest(String name, LocalDate birthDate) {
}
