package pt.ufp.lpi.backofficerui.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Optional;

public interface TagsDAO {
    JSONArray getAllTags();
    Optional<JSONObject> getWorkByName(String name);

    void replaceAllTags(JSONArray jsonArray);
}
