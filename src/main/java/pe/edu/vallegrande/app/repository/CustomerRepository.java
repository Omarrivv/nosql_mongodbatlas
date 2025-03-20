package pe.edu.vallegrande.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.app.model.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
