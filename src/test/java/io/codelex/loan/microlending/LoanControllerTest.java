/*
package io.codelex.loan.microlending;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.service.RepositoryLoanService;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryLoanService service;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );

        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
        javaTimeModule.addSerializer(
                LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );

        builder.modules(javaTimeModule);
        builder.featuresToDisable(WRITE_DATES_AS_TIMESTAMPS);

        MAPPER.registerModule(javaTimeModule);
    }

    @Test
    public void should_get_200_when_loan_is_created() throws Exception{
        //given
        BigDecimal bigDecimal = new BigDecimal(50);
        LoanRequest request = new LoanRequest(
                300L,
                30L
        );
        User user = new User(
                1L,
                "User",
                "12345",
                "Krists",
                "Abols",
                "kas@gmail.com"
        );
        String jsonResponse = MAPPER.writeValueAsString(request);

        Loan loan = new Loan(
                1L,
                request.getAmount(),
                request.getTerm(),
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                bigDecimal,
                user,
                true

        );

        Mockito.lenient()
                .when(service.createLoan(any(), any(), any()))
                .thenReturn(loan);

        String json = mockMvc.perform(MockMvcRequestBuilders.post("/api/loans")
                .content(jsonResponse)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse()
                .getContentAsString();

        LoanRecord result = MAPPER.readValue(
                json, LoanRecord.class);

        Assertions.assertEquals(loan.getAmount(), result.getAmount());


    }
}
*/
