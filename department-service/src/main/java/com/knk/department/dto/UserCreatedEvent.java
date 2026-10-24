package com.knk.department.dto;

import java.time.Instant;

public record UserCreatedEvent(Long userId, String userName, Long departmentId, Instant occuredAt) {

}
