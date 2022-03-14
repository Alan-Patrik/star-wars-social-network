package com.starwars.resistence.modules.rebel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RebelRepository extends JpaRepository<Rebel, Long> {
    List<Rebel> findByTraitorTrue();

    List<Rebel> findByTraitorFalse();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM rebels WHERE rebels.localization_id = :id AND rebels.traitor = false"
    )
    Page<Rebel> findByLocalization(Long id, Pageable pageable);
}
