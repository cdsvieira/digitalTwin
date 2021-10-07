package com.koerber.digitalTwin.repo;

import com.koerber.digitalTwin.domain.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MachineRepository extends MongoRepository<Machine, String> {

}
