package assets.hello_assets.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class CreateAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void creatAccount_HttpStatus() throws Exception{

        //given
        String requestBody1= "{\"accountNumber\":12345678, \"balance\":10000, \"password\":\"1234\", \"accountType\":\"GENERAL\"}";
        String requestBody2 = "{\"accountNumber\":12345679, \"balance\":10000, \"password\":\"1234\", \"accountType\":\"TERM_DEPOSIT\", " +
                "\"termMonth\":\"12\", \"interestRate\":\"3.5\"}";



        //when and then

        mockMvc.perform(MockMvcRequestBuilders.post("/account/create/general")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/account/create/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody2))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}
