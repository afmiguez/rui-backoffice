package pt.ufp.lpi.backofficerui.dao;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;


@Component
public class WorksFileDAO implements WorksDAO {

    private final JSONArray works;

    @SneakyThrows
    public WorksFileDAO() {
        File resource = new ClassPathResource("works.json").getFile();
        BufferedReader reader = new BufferedReader(new FileReader(resource));
        StringBuilder sb=new StringBuilder();
        String line;
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        this.works=new JSONArray(sb.toString());
        reader.close();
    }

    @Override
    public JSONArray getAllWorks() {
        return this.works;
    }

    @Override
    public Optional<JSONObject> getWorkByTitle(String title) {
        for(int i=0;i<this.works.length();i++){
            JSONObject work=this.works.getJSONObject(i);
            if(work.get("title").equals(title)){
                return Optional.of(work);
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void replaceAllWorks(JSONArray jsonArray) {
        File resource = new ClassPathResource("works.json").getFile();
        BufferedWriter bw=new BufferedWriter(new FileWriter(resource));
        bw.write(jsonArray.toString());
        bw.close();
    }
}
