package pt.ufp.lpi.backofficerui.dao;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;

@Component
public class TagsFileDAO implements TagsDAO {

    private final JSONArray tags;

    @SneakyThrows
    public TagsFileDAO() {
        File resource = new ClassPathResource("tags.json").getFile();
        BufferedReader reader = new BufferedReader(new FileReader(resource));
        StringBuilder sb=new StringBuilder();
        String line;
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        this.tags=new JSONArray(sb.toString());
        reader.close();
    }

    @Override
    public JSONArray getAllTags() {
        return tags;
    }

    @Override
    public Optional<JSONObject> getWorkByName(String name) {
        for(int i=0;i<this.tags.length();i++){
            JSONObject tag=this.tags.getJSONObject(i);
            if(tag.get("name").equals(name)){
                return Optional.of(tag);
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void replaceAllTags(JSONArray jsonArray) {
        File resource = new ClassPathResource("tags.json").getFile();
        BufferedWriter bw=new BufferedWriter(new FileWriter(resource));
        bw.write(jsonArray.toString());
        bw.close();
    }
}
