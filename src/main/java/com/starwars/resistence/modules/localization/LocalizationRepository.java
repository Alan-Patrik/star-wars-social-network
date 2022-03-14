package com.starwars.resistence.modules.localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {
    Optional<Localization> findByBasename(String basename);

    @Query(nativeQuery = true, value = "SELECT l.rebels FROM localizations l WHERE l.basename = :basename")
    List<Localization> getRebels(@Param("basename") String basename);

}
