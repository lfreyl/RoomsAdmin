package co.com.reactive.roomsadmin;

import co.com.reactive.roomsadmin.model.Room;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.reactive.roomsadmin.repository.RoomsRepository;
import reactor.core.publisher.Flux;


@SpringBootApplication
public  class Application {
    public  static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * CommandLineRunner se usa para ejecutar codigo una vez finalice el inicio.
     * Para este ejercicio inicializa valores en la base de datos
     * @param repository
     * @return lambda expresion
     */
    @Bean
    CommandLineRunner init(RoomsRepository repository){
        return args -> {

            Flux<Room> rooms = Flux.just(
                    Room.builder().bloque("A").piso(1).capacidad(10).nombreSala("sala1A").horasDisp("de 8 a 5").build(),
                    Room.builder().bloque("A").piso(1).capacidad(10).nombreSala("sala1A").horasDisp("de 8 a 5").build(),
                    Room.builder().bloque("A").piso(1).capacidad(10).nombreSala("sala1A").horasDisp("de 8 a 5").build(),
                    Room.builder().bloque("A").piso(1).capacidad(10).nombreSala("sala1A").horasDisp("de 8 a 5").build()
            ).flatMap(p-> repository.save(p));

            rooms.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}
