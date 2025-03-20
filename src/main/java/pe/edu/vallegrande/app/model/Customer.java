package pe.edu.vallegrande.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String state = "A"; // Valor por defecto
}
