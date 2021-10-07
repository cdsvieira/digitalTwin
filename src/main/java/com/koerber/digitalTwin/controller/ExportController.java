package com.koerber.digitalTwin.controller;

import com.koerber.digitalTwin.domain.Machine;
import com.koerber.digitalTwin.dto.MachineExport;
import com.koerber.digitalTwin.exceptions.NotFoundException;
import com.koerber.digitalTwin.service.ExportService;
import com.koerber.digitalTwin.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Title Export Controller
 * I'm assuming this is the equivalent to get by id.
 * However, if this is to be some kind of tracking parent and children kind of export
 * maybe the document should e organized in a tree form instead of having a parentID.
 * In another case a SQL schema could be produced.
 *
 * COuld also be a post with a list of ids to get
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping(value= "{id}" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MachineExport> exportOne(@PathVariable String id) {
        try {
            return new ResponseEntity<>(exportService.exportOne(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MachineExport>> exportAll() {
        try {
            return new ResponseEntity<>(exportService.exportAll(), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
