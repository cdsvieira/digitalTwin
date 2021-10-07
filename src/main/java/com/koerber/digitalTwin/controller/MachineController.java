package com.koerber.digitalTwin.controller;

import com.koerber.digitalTwin.domain.Machine;
import com.koerber.digitalTwin.exceptions.BadRequestException;
import com.koerber.digitalTwin.exceptions.NotFoundException;
import com.koerber.digitalTwin.service.MachineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO - promote exceptions to @ExceptionHandler and proper response instead of ResponseEntity

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping
    public ResponseEntity<Machine> createMachine(@RequestBody Machine machine){
        try {
            return new ResponseEntity<>(machineService.createMachine(machine),HttpStatus.CREATED);
        }catch (BadRequestException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Machine>> getAllMachines(){
        return  new ResponseEntity<>(machineService.getAllMachines(),HttpStatus.OK);
    }

    @GetMapping("{id")
    public ResponseEntity<Machine> getMachine(@PathVariable String id){
        return  new ResponseEntity<>(machineService.getMachine(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMachine(@PathVariable String id){
        try{
            machineService.deleteMachine(id);
        }catch(NotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMachine(@PathVariable String id, @RequestBody Machine newMachine){
        try{
            return new ResponseEntity(machineService.updateMachine(id, newMachine),HttpStatus.OK);
        }catch(NotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
