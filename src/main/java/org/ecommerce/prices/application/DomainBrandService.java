package org.ecommerce.prices.application;

import org.ecommerce.prices.domain.repository.BrandsRepository;
import org.ecommerce.prices.domain.service.BrandService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DomainBrandService implements BrandService {

    private final BrandsRepository brandsRepository;

    public DomainBrandService(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    @Override
    public Integer getBrandId(String brand) {
        Optional<Integer> brandId = brandsRepository.findAll()
                .filter(f -> brand.equals(f.getName()))
                .map(filteredBrand -> filteredBrand.getId())
                .findFirst();
        return brandId.orElseThrow(() -> new EntityNotFoundException());
    }
}
