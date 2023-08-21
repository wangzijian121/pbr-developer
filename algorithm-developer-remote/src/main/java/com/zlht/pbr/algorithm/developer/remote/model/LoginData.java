package com.zlht.pbr.algorithm.developer.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {

    private Integer id;
    private String nickname;
    private String session_id;
}
