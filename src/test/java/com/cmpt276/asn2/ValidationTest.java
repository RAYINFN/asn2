package com.cmpt276.asn2;

import com.cmpt276.asn2.Application.StaffRating;
import com.cmpt276.asn2.Role.Role;
import com.cmpt276.asn2.StaffMemberProfile.StaffProfile;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private StaffRating sample() {
        StaffRating r = new StaffRating();
        r.setStaffName("Validation User");
        r.setEmail("eval@example.com");
        StaffProfile profile = new StaffProfile();
        profile.setRole(Role.TA);
        r.setProfile(profile);
        r.setClarityScore(5);
        r.setNicenessScore(5);
        r.setKnowledgeabilityScore(5);
        return r;
    }

    @Test
    void test_noViolations() {
        assertThat(validator.validate(sample())).isEmpty();
    }

    @Test
    void test_invalidEmail() {
        StaffRating r = sample();
        r.setEmail("not-an-email");
        assertThat(validator.validate(r)).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    void test_OutOfRange() {
        StaffRating r = sample();
        r.setClarityScore(99);
        assertThat(validator.validate(r)).anyMatch(v -> v.getPropertyPath().toString().equals("clarityScore"));
    }

    @Test
    void test_missingName() {
        StaffRating r = sample();
        r.setStaffName(null);
        assertThat(validator.validate(r)).anyMatch(v -> v.getPropertyPath().toString().equals("staffName"));
    }

    @Test
    void test_missingRole() {
        StaffRating r = sample();
        r.getProfile().setRole(null);
        assertThat(validator.validate(r)).anyMatch(v -> v.getPropertyPath().toString().contains("role"));
    }
}
