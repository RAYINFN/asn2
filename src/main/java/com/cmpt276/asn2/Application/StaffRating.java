package com.cmpt276.asn2.Application;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.cmpt276.asn2.Role.*;

@Entity
public class StaffRating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Staff name is required")
    @Size(min = 2, max = 50, message = "Staff name must be between 2 and 50 characters")
    private String staffName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Roletype is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "Clarity Score is required")
    @Min(value = 1, message = "Clarity Score must be at least 1")
    @Max(value = 10, message = "Clarity Score must be at most 10")
    private Integer clarityScore;

    @NotNull(message = "Niceness Score is required")
    @Min(value = 1, message = "Niceness Score must be at least 1")
    @Max(value = 10, message = "Niceness Score must be at most 10")
    private Integer nicenessScore;

    @NotNull(message = "Knowledgeable Score is required")
    @Min(value = 1, message = "Knowledgeable Score must be at least 1")
    @Max(value = 10, message = "Knowledgeable Score must be at most 10")
    private Integer knowledgeabilityScore;

    @Size(max = 500, message = "Comment must be less than 500 characters")
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}