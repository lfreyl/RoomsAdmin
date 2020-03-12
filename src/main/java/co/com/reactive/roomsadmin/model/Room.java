package co.com.reactive.roomsadmin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    private String id;

    private String bloque;
    private int piso;
    private int capacidad;
    private String nombreSala;
    private String horasDisp;
}
