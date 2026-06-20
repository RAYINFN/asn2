package com.cmpt276.asn2.Service;

import com.cmpt276.asn2.Application.*;
import com.cmpt276.asn2.DBControl.DBController;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MainService {
    private final DBController dbc;

    public MainService(DBController dbc) {
        this.dbc = dbc;
    }

    public StaffRating findById(Long id) {
        return dbc.findById(id).orElseThrow(() -> new IllegalAccessError(id + " Not Found"));
    }

    public List<StaffRating> findAll() {
        return dbc.findAll();
    }

    public StaffRating save(StaffRating staffRating) {
        return dbc.save(staffRating);
    }

    public void deleteById(Long id) {
        dbc.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return dbc.existsByEmail(email);
    }

    public boolean existsByEmailAndIdNot(String email, Long id) {
        return dbc.existsByEmailAndIdNot(email, id);
    }
}
