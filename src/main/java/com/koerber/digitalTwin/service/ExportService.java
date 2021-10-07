package com.koerber.digitalTwin.service;

import com.koerber.digitalTwin.dto.MachineExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {

    @Autowired
    private MachineService machineService;

    public MachineExport exportOne(String id){
        return new MachineExport(machineService.getMachine(id));
    }

    public List<MachineExport> exportAll(){
        return machineService.getAllMachines().stream()
                .map(MachineExport::new).collect(Collectors.toList());
    }


}
