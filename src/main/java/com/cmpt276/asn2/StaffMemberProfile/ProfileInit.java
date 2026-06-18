package com.cmpt276.asn2.StaffMemberProfile;

import com.cmpt276.asn2.Role.*;

public class ProfileInit {
    
    public static StaffMemberProfile fromRole(Role role) {
        return switch (role) {
            case TA -> new TAProfile();
            case PROF -> new ProfessorProfile();
            case INSTRUCTOR -> new InstructorProfile();
            case STUFF -> () -> "Stuff";
        };
    }
}
