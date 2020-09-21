package pt.ufp.lpi.backofficerui.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.backofficerui.dao.WorksDAO;
import pt.ufp.lpi.backofficerui.dao.WorksObjectDAO;
import pt.ufp.lpi.backofficerui.models.Work;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/works")
@AllArgsConstructor
public class WorksController {

    private final WorksObjectDAO worksDAO;

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Work>> getWorks(){
        return ResponseEntity.ok(worksDAO.getAllWorks());
    }

    @GetMapping(value="/title/{title}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> getWorkByTitle(@PathVariable String title){
        Optional<Work> jsonObject=worksDAO.getWorkByTitle(title);
        return jsonObject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value="",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> replaceAllWorks(@RequestBody String worksAsString){
        worksDAO.replaceAllWorks(worksAsString);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> updateWork(@RequestBody Work work)  {
        return ResponseEntity.ok(this.worksDAO.updateWork(work));
    }

    @PatchMapping
    public ResponseEntity<Work> addWork(@RequestBody Work work){
        return ResponseEntity.ok(this.worksDAO.addWork(work));
    }
}
