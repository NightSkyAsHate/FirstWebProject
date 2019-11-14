package com.nightsky.serverapi.repository;

import com.nightsky.serverapi.domain.Package_size;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package_size, Long> {
}
