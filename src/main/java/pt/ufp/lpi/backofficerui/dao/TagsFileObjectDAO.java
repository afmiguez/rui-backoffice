package pt.ufp.lpi.backofficerui.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pt.ufp.lpi.backofficerui.models.Tag;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class TagsFileObjectDAO implements TagsObjectDAO {

    private final List<Tag> tags;

    @SneakyThrows
    public TagsFileObjectDAO() {
        File resource = new ClassPathResource("tags.json").getFile();
        tags=new ObjectMapper().readValue(resource,new TypeReference<>() {});
    }

    @Override
    public List<Tag> getAllTags() {
        return tags;
    }

    @Override
    public Optional<Tag> getWorkByName(String name) {
        return tags.stream().filter(t->t.getName().equalsIgnoreCase(name)).findAny();
    }

    @Override
    public void replaceAllTags(List<Tag> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
    }
}
