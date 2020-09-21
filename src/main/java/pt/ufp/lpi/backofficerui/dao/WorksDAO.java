package pt.ufp.lpi.backofficerui.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Optional;

public interface WorksDAO {
    JSONArray getAllWorks();
    Optional<JSONObject> getWorkByTitle(String title);
    void replaceAllWorks(JSONArray jsonArray);
}
