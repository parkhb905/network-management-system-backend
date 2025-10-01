package com.network.nms.controller.device;

import com.network.nms.domain.user.User;
import com.network.nms.security.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "bin", roles = {"USER"})
    void 장비등록_API_테스트() throws Exception {
        String deviceJson = """
            {
                "deviceName": "Router-001",
                "deviceTypeId": 2,
                "vendorId": 3,
                "createdBy": 1
            }
        """;

        User user = User.builder()
                .id(3L)
                .username("bin")
                .password("dummy-password")
                .role("USER")
                .build();

        CustomUserDetails mockUser = new CustomUserDetails(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/devices")
                        .with(user(mockUser)) // <- SecurityContext에 CustomUserDetails 넣음
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deviceJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rowsAffected").value(1));
    }

}