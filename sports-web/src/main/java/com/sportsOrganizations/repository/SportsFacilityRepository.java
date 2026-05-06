package com.sportsOrganizations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportsOrganizations.model.sports.SportsFacility;

public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {

  List<SportsFacility> findByFacilityType_TypeName(String typeName);

  @Query("""
          select sf
          from SportsFacility sf
          join sf.facilityType ft
          where ft.typeName = :typeName
            and (:minCapacity is null or treat(ft as Stadium).capacity >= :minCapacity)
            and (:minAreaSize is null or treat(ft as SportsArea).areaSize >= :minAreaSize)
            and (:minSimulatorsCount is null or treat(ft as Gym).simulatorsCount >= :minSimulatorsCount)
      """)
  List<SportsFacility> findByTypeAndCharacteristics(
      @Param("typeName") String typeName,
      @Param("minCapacity") Integer minCapacity,
      @Param("minAreaSize") Double minAreaSize,
      @Param("minSimulatorsCount") Integer minSimulatorsCount);
}
