package flumine.sgm.focodata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DataTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = {"delete-data.sql"})
    public void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Double> body = new HashMap<>();
        body.put("temp", 20.5);
        body.put("u", 220.3);
        String contentBody = objectMapper.writeValueAsString(body);
        this.mockMvc.perform(post("http://localhost:8080/1/upload").contentType(MediaType.APPLICATION_JSON)
                .content(contentBody)).andExpect(status().is(202))
                .andExpect(content().string("ok"));
    }

    @Test
    @Sql(value = {"add-data.sql"})
    public void get_data() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Double> data = new HashMap<>();
        data.put("temp", 20.5);
        data.put("u", 220.3);
        List<HashMap<String, Double>> body = new ArrayList<>();
        body.add(data);
        String contentBody = objectMapper.writeValueAsString(body);
        System.out.println(contentBody);
        this.mockMvc.perform(get("http://localhost:8080/1/get")
                .param("fi", "0")
                .param("li", "2")
                .param("sort", "date"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
