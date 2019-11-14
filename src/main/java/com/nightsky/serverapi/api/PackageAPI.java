package com.nightsky.serverapi.api;

import com.nightsky.serverapi.domain.Package_size;
import com.nightsky.serverapi.repository.PackageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbPackage")
public class PackageAPI {

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping
    public Iterable<Package_size> getAllPackages() {
        return packageRepository.findAll();
    }

    @GetMapping("/{packageId}")
    public Package_size getPackageById(@PathVariable("id") Package_size package_size) {
        return package_size;
    }

    @PostMapping
    public Package_size createNewPackage(@RequestBody Package_size package_size){
        return packageRepository.save(package_size);
    }

    @PutMapping("/{packageId}")
    public Package_size modifyPackage(@PathVariable("id") Package_size package_sizeFromDb, Package_size package_size){
        BeanUtils.copyProperties(package_size, package_sizeFromDb, "id");
        return packageRepository.save(package_sizeFromDb);
    }

    @DeleteMapping("{packageId}")
    public void deletePackage(@PathVariable("id") Package_size package_size){
        packageRepository.delete(package_size);
    }
}
