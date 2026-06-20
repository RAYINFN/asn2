package com.cmpt276.asn2;

import com.cmpt276.asn2.Application.StaffRating;
import com.cmpt276.asn2.DBControl.DBController;
import com.cmpt276.asn2.Role.Role;
import com.cmpt276.asn2.StaffMemberProfile.StaffProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PersistenceTest {

    @Autowired
    private DBController repo;

    private StaffRating sample() {
        StaffRating r = new StaffRating();
        r.setStaffName("Dave");
        r.setEmail("dave@example.com");
        StaffProfile profile = new StaffProfile();
        profile.setRole(Role.TA);
        profile.setProfileTitle("Teaching Assistant");
        r.setProfile(profile);
        r.setClarityScore(5);
        r.setNicenessScore(5);
        r.setKnowledgeabilityScore(5);
        return r;
    }

    @Test
    void save_andRetrieve_works() {
        StaffRating saved = repo.save(sample());
        assertThat(repo.findById(saved.getId())).isPresent();
    }

    @Test
    void delete_removesEntry() {
        StaffRating saved = repo.save(sample());
        repo.deleteById(saved.getId());
        assertThat(repo.findById(saved.getId())).isEmpty();
    }
}
