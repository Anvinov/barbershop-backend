package co.edu.unicauca.service_service.exception;

public class CategoryNameAlreadyExistsException extends RuntimeException {
    public CategoryNameAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists");
    }
}

