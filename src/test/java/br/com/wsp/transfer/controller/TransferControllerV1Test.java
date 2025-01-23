package br.com.wsp.transfer.controller;

import br.com.wsp.transfer.controller.v1.TransferControllerV1;
import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.model.enums.TransferStatus;
import br.com.wsp.transfer.service.ITransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(TransferControllerV1.class)
class TransferControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ITransferService transferService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveNewSchedulingTransfer() throws Exception {

        doReturn(getTransferDto()).when(transferService).save(any(TransferDto.class));

        ResultActions result = mockMvc.perform(
                post("/api/v1/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getTransferDto())));

        result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.originAccount").value("1234567890"));
    }

    private TransferDto getTransferDto() {

        TransferDto transferDto = new TransferDto();
        transferDto.setId(1L);
        transferDto.setOriginAccount("123456789");
        transferDto.setDestinationAccount("987654321");
        transferDto.setAmount(Float.parseFloat("30"));
        transferDto.setTransferDate(LocalDateTime.now());
        transferDto.setScheduleDate(LocalDateTime.now().plusDays(3));
        transferDto.setStatus(TransferStatus.PENDING);

        return transferDto;
    }
}