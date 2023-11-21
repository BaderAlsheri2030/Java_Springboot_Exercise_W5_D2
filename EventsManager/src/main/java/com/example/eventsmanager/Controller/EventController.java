package com.example.eventsmanager.Controller;

import com.example.eventsmanager.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v2/event")
public class EventController {
ArrayList<Event> events = new ArrayList<>();

@GetMapping("/get")
public ResponseEntity getEvents(){
    return ResponseEntity.status(HttpStatus.OK).body(events);
}
@PostMapping("/add")
public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    events.add(event);
    return ResponseEntity.status(HttpStatus.OK).body("Event added successfully");
}
@PutMapping("/update/{index}")
public ResponseEntity updateEvent(@Valid @RequestBody Event event, @PathVariable int index, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    events.set(index,event);
    return ResponseEntity.status(HttpStatus.OK).body("Event updated ");
}
@DeleteMapping("/delete/{index}")
public ResponseEntity deleteEvent(@PathVariable int index){
    events.remove(index);
    return ResponseEntity.status(HttpStatus.OK).body("Event removed");
}

@GetMapping("/search/{id}")
public ResponseEntity searchEvent(@PathVariable String id){
    for (Event e:events) {
        if(e.getId().equals(id)){
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid,id wasn't found");
}

@PutMapping("/capacity/{index}/{capacity}")
public ResponseEntity changeCapacity(@PathVariable int index,@PathVariable int capacity){
    if (capacity <25){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not valid");

    }
    events.get(index).setCapacity(capacity);
    return ResponseEntity.status(HttpStatus.OK).body("Capacity updated");

}



}
