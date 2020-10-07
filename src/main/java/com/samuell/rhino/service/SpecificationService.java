package com.samuell.rhino.service;

import com.samuell.rhino.model.Specification;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SpecificationService {
    @Nullable
    List<Specification> getAllSpecifications();

    @Nullable
    Specification getSpecificationById(int id);

    @Nullable
    Specification addSpecification(Specification specification);
}
