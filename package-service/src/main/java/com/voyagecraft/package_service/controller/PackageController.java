package com.voyagecraft.package_service.controller;

import com.voyagecraft.package_service.model.TravelPackage;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final List<TravelPackage> packages = new ArrayList<>();

    public PackageController() {

        packages.add(
            new TravelPackage(
                1,
                "Goa",
                15000,
                20
            )
        );

        packages.add(
            new TravelPackage(
                2,
                "Manali",
                25000,
                15
            )
        );
    }

    @GetMapping
    public List<TravelPackage> getPackages() {
        return packages;
    }

    @GetMapping("/{id}")
    public TravelPackage getPackage(@PathVariable int id) {

        return packages.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}