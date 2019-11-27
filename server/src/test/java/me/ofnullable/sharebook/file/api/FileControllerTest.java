package me.ofnullable.sharebook.file.api;

import me.ofnullable.sharebook.error.ApiErrorHandler;
import me.ofnullable.sharebook.file.service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class FileControllerTest {

    @InjectMocks
    private FileController fileController;

    @Mock
    private FileStorageService fileStorageService;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(fileController)
                .setControllerAdvice(ApiErrorHandler.class)
                .alwaysDo(print())
                .build();
    }

    @Test
    void saveFile() throws Exception {
        var mockFile = ResourceUtils.getFile("classpath:static/image/두근두근-파이썬.jpg");
        var file = new MockMultipartFile("image", "두근두근-파이썬.jpg", "image/jpeg", Files.readAllBytes(mockFile.toPath()));

        given(fileStorageService.store(any(MultipartFile.class)))
                .willReturn("/image/두근두근-파이썬.jpg");

        mvc.perform(multipart("/file/image")
                .file("image", file.getBytes())
                .accept("text/plain;charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

}
