package com.openclassrooms.P3_API_Portail_locataire.repositories;

import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}