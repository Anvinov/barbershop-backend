package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.service.request.CategoryRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.service.request.ServiceRequestDTO;
import co.edu.unicauca.api_gateway.facade.client.ServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final ServiceClient serviceClient;

    public ServiceController(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return serviceClient.getHealth();
    }

    // Category endpoints
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO request) {
        return serviceClient.createCategory(request);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories() {
        return serviceClient.getAllCategories();
    }

    @GetMapping("/category/search")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
        return serviceClient.getCategoryByName(name);
    }

    // Service endpoints
    @PostMapping
    public ResponseEntity<?> createService(@RequestBody ServiceRequestDTO request) {
        return serviceClient.createService(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody ServiceRequestDTO request) {
        return serviceClient.updateService(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        return serviceClient.deleteService(id);
    }

    @GetMapping
    public ResponseEntity<?> getServices(@RequestParam(required = false) String category) {
        return  serviceClient.getServices(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        return serviceClient.getServiceById(id);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableService(@PathVariable Long id) {
        return  serviceClient.disableService(id);
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<?> enableService(@PathVariable Long id) {
        return  serviceClient.enableService(id);
    }
}
