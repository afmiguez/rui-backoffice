package pt.ufp.lpi.backofficerui.dao;

import pt.ufp.lpi.backofficerui.models.Work;

import java.util.List;
import java.util.Optional;

public interface WorksObjectDAO {
    List<Work> getAllWorks();
    Optional<Work> getWorkByTitle(String title);
    void replaceAllWorks(String works);

    Work addWork(Work work);

    Work updateWork(Work work);
}
