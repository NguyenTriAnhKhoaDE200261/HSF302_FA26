package com.fu.ch08.b3;

import java.time.LocalDate;
import java.util.List;

// TODO 1: Model & "database" trong bộ nhớ
public record User(String fullName, String email, String username, String password,
                   String gender, LocalDate dob, String major, List<String> hobbies) {}
