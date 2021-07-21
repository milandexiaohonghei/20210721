package com.example.demo.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String photo;
    private List<ArticleInfo>  alist;

}
