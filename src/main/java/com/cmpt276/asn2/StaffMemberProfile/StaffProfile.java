package com.cmpt276.asn2.StaffMemberProfile;

import com.cmpt276.asn2.Role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class StaffProfile {

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    
    private Role role;
    private String profileTitle;

    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public String getProfileTitle() {
        return profileTitle;
    }
    
    public void setProfileTitle(String profileTitle) {
        this.profileTitle = profileTitle;
    }
}
