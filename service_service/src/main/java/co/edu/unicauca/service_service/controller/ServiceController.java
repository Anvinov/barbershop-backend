package co.edu.unicauca.service_service.controller;


import co.edu.unicauca.service_service.infra.dto.request.CategoryRequestDTO;
import co.edu.unicauca.service_service.infra.dto.request.ServiceRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.CategoryResponseDTO;
import co.edu.unicauca.service_service.infra.dto.response.ServiceResponseDTO;
import co.edu.unicauca.service_service.service.CategoryService;
import co.edu.unicauca.service_service.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final ServiceService serviceService;
    private final CategoryService categoryService;

    public ServiceController(ServiceService serviceService, CategoryService categoryService) {
        this.serviceService = serviceService;
        this.categoryService = categoryService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok().build();
    }

    // Category endpoints
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO request) {
        CategoryResponseDTO response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryResponseDTO> response = categoryService.getAllCategories();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/category/search")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
        CategoryResponseDTO response = categoryService.getCategoryByName(name);
        return ResponseEntity.ok().body(response);
    }

    // Service endpoints
    @PostMapping
    public ResponseEntity<?> createService(@RequestBody ServiceRequestDTO request) {
        ServiceResponseDTO response = serviceService.createService(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody ServiceRequestDTO request) {
        ServiceResponseDTO response = serviceService.updateService(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        ServiceResponseDTO response = serviceService.deleteService(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getServices(@RequestParam(required = false) String category) {
        List<ServiceResponseDTO> response;

        if (category != null) {
            response = serviceService.getServicesByCategory(category);
        }else {
            response = serviceService.getAllServices();
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        ServiceResponseDTO response = serviceService.getServiceById(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableService(@PathVariable Long id) {
        ServiceResponseDTO response = serviceService.disableService(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enableService(@PathVariable Long id) {
        ServiceResponseDTO response = serviceService.enableService(id);
        return ResponseEntity.ok().body(response);
    }
}
