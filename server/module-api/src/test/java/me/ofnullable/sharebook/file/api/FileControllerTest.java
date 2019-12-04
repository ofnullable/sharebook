package me.ofnullable.sharebook.file.api;

import me.ofnullable.file.service.FileStorageService;
import me.ofnullable.sharebook.error.ApiErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;

import static me.ofnullable.sharebook.utils.StorageTestUtils.getMultipartFile;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    @DisplayName("이미지 업로드")
    void file_upload() throws Exception {
        given(fileStorageService.store(any(InputStream.class), any(String.class)))
                .willReturn("/static/image/filename.jpg");

        mvc.perform(multipart("/file/image")
                .file(getMultipartFile("image")))
                .andExpect(jsonPath("$", is("/static/image/filename.jpg")))
                .andExpect(status().isOk());
    }

}
