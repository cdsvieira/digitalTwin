package com.koerber.digitalTwin.dto;

import com.koerber.digitalTwin.domain.Machine;
import com.koerber.digitalTwin.domain.MachineType;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Getter
@XmlRootElement
public class MachineExport {

    private String name;

    private String description;

    private String parentId;

    private MachineType type;

    private String lastModified;

    private boolean enabled;

    public MachineExport(Machine machine){
        this.name=machine.getName();
        this.description=machine.getDescription();
        this.parentId = machine.getParentId();
        this.type = machine.getType();
        this.lastModified =machine.getLastModified();
        this.enabled = machine.isEnabled();
    }
}
