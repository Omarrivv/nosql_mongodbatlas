package pe.edu.vallegrande.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.app.model.Customer;
import pe.edu.vallegrande.app.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customers")
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        log.info("Obteniendo todos los clientes");
        return customerService.findAll()
            .doOnError(error -> log.error("Error al obtener clientes: " + error.getMessage()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String id) {
        log.info("Obteniendo cliente con ID: {}", id);
        return customerService.findById(id)
            .map(customer -> ResponseEntity.ok(customer))
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .doOnError(error -> log.error("Error al obtener cliente: " + error.getMessage()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        log.info("Creando nuevo cliente: {}", customer);
        return customerService.save(customer)
            .doOnError(error -> log.error("Error al crear cliente: " + error.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        log.info("Actualizando cliente con ID {}: {}", id, customer);
        return customerService.update(id, customer)
            .map(updatedCustomer -> ResponseEntity.ok(updatedCustomer))
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .doOnError(error -> log.error("Error al actualizar cliente: " + error.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
        log.info("Eliminando cliente con ID: {}", id);
        return customerService.delete(id)
            .then(Mono.just(ResponseEntity.noContent().<Void>build()))
            .doOnError(error -> log.error("Error al eliminar cliente: " + error.getMessage()));
    }
}

class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

