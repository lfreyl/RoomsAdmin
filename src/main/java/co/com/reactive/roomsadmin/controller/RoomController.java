package co.com.reactive.roomsadmin.controller;

import co.com.reactive.roomsadmin.model.Room;
import co.com.reactive.roomsadmin.repository.RoomsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class    RoomController {

    private RoomsRepository repository;

    /**
     * Controlador que devuelve todos los libros
     * @return Flux<Book>
     */
    @GetMapping
    public Flux<Room> getAllRooms(){
        return repository.findAll();
    }

    /**
     * Controlador que devuelve un libro identificado con el id
     * @return Mono<ResponseEntity<Book>>
     */
    @GetMapping("{id}")
    public Mono<ResponseEntity<Room>> getRoom(@PathVariable String id){
        return repository.findById(id).map(room -> ResponseEntity.ok(room))
                         .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que almacena un libro enviado en el body
     * @param book
     * @return Mono<Book>
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Room> saveRoom(@RequestBody Room room){
        return repository.save(room);

    }

    /**
     * Controlador que actualiza un libro existente en base a su id y a la entidad enviada en el body
     * @param id
     * @param book
     * @return Mono<ResponseEntity<Book>>
     */
    @PutMapping("{id}")
    public Mono<ResponseEntity<Room>> updateBook(@PathVariable(value="id") String id, @RequestBody Room room){
        return  repository.findById(id)
                .flatMap(existingRoom -> {
                    existingRoom.setBloque(room.getBloque());
                    existingRoom.setPiso(room.getPiso());
                    existingRoom.setCapacidad(room.getCapacidad());
                    existingRoom.setNombreSala(room.getNombreSala());
                    existingRoom.setHorasDisp(room.getHorasDisp());
                    return repository.save(existingRoom);
                }).map(updateRoom -> ResponseEntity.ok(updateRoom))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    /**
     * Controlador que elimina un libro en base a su id
     * @param id
     * @return <ResponseEntity<Void>>
     */
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteRoom(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(existingRoom -> repository.delete(existingRoom)
                .then(Mono.just(ResponseEntity.ok().<Void>build())))
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que elimina todos los libros
     * @return Mono<Void>
     */
    @DeleteMapping
    public Mono<Void> deleteAllRooms(){
        return repository.deleteAll();
    }

}
