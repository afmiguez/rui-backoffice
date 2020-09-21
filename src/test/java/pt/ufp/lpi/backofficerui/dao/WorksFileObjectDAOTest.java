package pt.ufp.lpi.backofficerui.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pt.ufp.lpi.backofficerui.models.Work;

import static org.junit.jupiter.api.Assertions.*;

class WorksFileObjectDAOTest {

    private final WorksFileObjectDAO worksFileObjectDAO=new WorksFileObjectDAO();

    @Test
    public void test(){
        assertNotNull(worksFileObjectDAO.getAllWorks());
        assertNotNull(worksFileObjectDAO.getWorkByTitle("@agua_um conto digital"));
    }

    @Test
    public void test2() throws JsonProcessingException {
        String json="{\"authorData\":[{\"first\":\"Antonio\",\"last\":\"Abernu\"}]}";
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.readValue(json, Work.class));
    }
}