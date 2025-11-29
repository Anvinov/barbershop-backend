package co.edu.unicauca.service_service.repository;

import co.edu.unicauca.service_service.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
    List<ServiceEntity> findAllByCategory_Name(String name);

    boolean existsByName(String name);
}
