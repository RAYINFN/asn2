package com.cmpt276.asn2.DBControl;

import com.cmpt276.asn2.Application.StaffRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBController extends JpaRepository<StaffRating, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

}


