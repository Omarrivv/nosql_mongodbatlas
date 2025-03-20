package pe.edu.vallegrande.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.app.model.Customer;
import pe.edu.vallegrande.app.repository.CustomerRepository;
import pe.edu.vallegrande.app.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<Customer> findAll() {
        log.info("Iniciando búsqueda de todos los clientes");
        return customerRepository.findAll()
            .doOnComplete(() -> log.info("Búsqueda de clientes completada"))
            .doOnError(error -> log.error("Error al buscar clientes: " + error.getMessage()));
    }

    @Override
    public Mono<Customer> findById(String id) {
        log.info("Buscando cliente con ID: {}", id);
        return customerRepository.findById(id)
            .doOnSuccess(customer -> {
                if (customer != null) {
                    log.info("Cliente encontrado: {}", customer);
                } else {
                    log.info("Cliente no encontrado con ID: {}", id);
                }
            })
            .doOnError(error -> log.error("Error al buscar cliente: " + error.getMessage()));
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        log.info("Guardando nuevo cliente: {}", customer);
        return customerRepository.save(customer)
            .doOnSuccess(savedCustomer -> log.info("Cliente guardado exitosamente: {}", savedCustomer))
            .doOnError(error -> log.error("Error al guardar cliente: " + error.getMessage()));
    }

    @Override
    public Mono<Customer> update(String id, Customer customer) {
        log.info("Actualizando cliente con ID {}: {}", id, customer);
        return customerRepository.findById(id)
            .flatMap(existingCustomer -> {
                customer.setId(id);
                return customerRepository.save(customer);
            })
            .doOnSuccess(updatedCustomer -> log.info("Cliente actualizado exitosamente: {}", updatedCustomer))
            .doOnError(error -> log.error("Error al actualizar cliente: " + error.getMessage()));
    }

    @Override
    public Mono<Void> delete(String id) {
        log.info("Eliminando cliente con ID: {}", id);
        return customerRepository.deleteById(id)
            .doOnSuccess(v -> log.info("Cliente eliminado exitosamente con ID: {}", id))
            .doOnError(error -> log.error("Error al eliminar cliente: " + error.getMessage()));
    }
}