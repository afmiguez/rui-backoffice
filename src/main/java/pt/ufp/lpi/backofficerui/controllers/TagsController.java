package pt.ufp.lpi.backofficerui.controllers;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.backofficerui.dao.TagsDAO;
import pt.ufp.lpi.backofficerui.dao.TagsObjectDAO;
import pt.ufp.lpi.backofficerui.models.Tag;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tags")
@AllArgsConstructor
public class TagsController {

    private final TagsObjectDAO tagsDAO;

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getAllTags(){
        return ResponseEntity.ok(tagsDAO.getAllTags());
    }

    @GetMapping(value="/name/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getWorkByTitle(@PathVariable String name){
        Optional<Tag> optionalTag=tagsDAO.getWorkByName(name);
        return optionalTag.map(object -> ResponseEntity.ok(object.toString())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value="",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> replaceAllWorks(@RequestBody List<Tag> tags){
//        JSONArray jsonArray=new JSONArray(worksAsString);
        tagsDAO.replaceAllTags(tags);
        return ResponseEntity.ok().build();
    }
}
