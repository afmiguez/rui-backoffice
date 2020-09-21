package pt.ufp.lpi.backofficerui.dao;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pt.ufp.lpi.backofficerui.models.Work;
import pt.ufp.lpi.backofficerui.models.WorkComparator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class WorksFileObjectDAO implements WorksObjectDAO {

    private final List<Work> works;
    private WorkComparator workComparator=new WorkComparator();

    @SneakyThrows
    public WorksFileObjectDAO() {
        File resource = new ClassPathResource("works.json").getFile();
        works=new ObjectMapper().readValue(resource,new TypeReference<>() {});
    }

    private List<Work> getworks(String works){
        return new ObjectMapper().convertValue(works, new TypeReference<>() {});
    }

    @Override
    public List<Work> getAllWorks() {
        works.sort(workComparator);
        return works;
    }

    @Override
    public Optional<Work> getWorkByTitle(String title) {
        for(Work work:works){
            if(work.getTitle().equalsIgnoreCase(title)){
                return Optional.of(work);
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void replaceAllWorks(String works) {
        File resource = new ClassPathResource("works.json").getFile();
        BufferedWriter bw=new BufferedWriter(new FileWriter(resource));
        bw.write(works);
        bw.close();
    }

    @Override
    public Work addWork(Work work) {
        works.add(work);
        return work;
    }

    @Override
    public Work updateWork(Work work) {
        Optional<Work> optionalWork=this.getWorkByTitle(work.getTitle());
        optionalWork.ifPresent(this.works::remove);
        this.works.add(work);
        return work;
    }
}
