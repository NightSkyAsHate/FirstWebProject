package com.nightsky.serverapi.repository;

import com.nightsky.serverapi.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    List<Cargo> findAll();

//    @Query("select * from cargo where")
//    List<Cargo> findAllByUser(String username);
}
