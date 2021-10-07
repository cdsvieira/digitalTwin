package com.koerber.digitalTwin.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Document("machines")
public class Machine {

    @Id
    private String id;

    @NotBlank
    @Size(max=50, message = "'name' should is expected and no longer than 50 characters")
    private String name;

    @Size(max=150, message = "'description' should be no longer than 150 characters")
    private String description;

    private String parentId;

    @NotNull
    private MachineType type;

    private String lastModified;

    private boolean enabled = true;

    public boolean validateParentId(){
        //validate if it exists
        return true;
    }

    public void copyValues(Machine newMachine) {
        this.name=newMachine.getName();
        this.description=newMachine.getDescription();
        this.parentId = newMachine.getParentId();
        this.type= newMachine.getType();
        this.enabled= newMachine.isEnabled();
    }
}
