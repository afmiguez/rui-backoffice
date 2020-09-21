package pt.ufp.lpi.backofficerui.dao;

import pt.ufp.lpi.backofficerui.models.Tag;

import java.util.List;
import java.util.Optional;

public interface TagsObjectDAO {
    List<Tag> getAllTags();
    Optional<Tag> getWorkByName(String name);
    void replaceAllTags(List<Tag> tags);
}
