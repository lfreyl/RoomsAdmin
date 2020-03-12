package co.com.reactive.roomsadmin.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.com.reactive.roomsadmin.model.Room;


public interface RoomsRepository extends ReactiveMongoRepository<Room, String>{

}