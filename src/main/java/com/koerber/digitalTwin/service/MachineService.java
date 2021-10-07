package com.koerber.digitalTwin.service;

import com.koerber.digitalTwin.domain.Machine;
import com.koerber.digitalTwin.exceptions.BadRequestException;
import com.koerber.digitalTwin.exceptions.NotFoundException;
import com.koerber.digitalTwin.repo.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MachineService {

    private static final String ERROR_PARENT_ID="parentId non-existent";
    private static final String ERROR_MACHINE_NOT_FOUND="The Machine with id %s wast not found";
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Autowired
    private MachineRepository machineRepository;

    public Machine createMachine(Machine newMachine){
        if(!newMachine.validateParentId()){
            throw new BadRequestException(ERROR_PARENT_ID);
        }
        newMachine.setLastModified(LocalDateTime.now().format(FORMATTER));
        return machineRepository.insert(newMachine);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Machine getMachine(String id){
        return machineRepository.findById(id)
                .orElseThrow(() ->new NotFoundException(String.format(ERROR_MACHINE_NOT_FOUND,id)));
    }

    public void deleteMachine(String id) {
        machineRepository.delete(this.getMachine(id));
    }

    public Machine updateMachine(String id,Machine newMachine) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() ->new NotFoundException(String.format(ERROR_MACHINE_NOT_FOUND,id)));
        machine.copyValues(newMachine);
        if(!newMachine.validateParentId()){
            throw new BadRequestException(ERROR_PARENT_ID);
        }
        newMachine.setLastModified(LocalDateTime.now().format(FORMATTER));
        return machineRepository.save(machine);
    }
}
