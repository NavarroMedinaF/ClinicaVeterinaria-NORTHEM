package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Prepaid;

import java.util.List;

public interface PrepaidService {
    List<Prepaid> findPrepaids();

    Prepaid findPrepaidById(Long id);

    void savePrepaid(Prepaid prepaid);

    void deletePrepaid(Prepaid prepaid);
}
