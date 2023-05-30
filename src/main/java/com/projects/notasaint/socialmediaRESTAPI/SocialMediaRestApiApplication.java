package com.projects.notasaint.socialmediaRESTAPI;

import com.projects.notasaint.socialmediaRESTAPI.mappers.PostMapper;
import com.projects.notasaint.socialmediaRESTAPI.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialMediaRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaRestApiApplication.class, args);
	}

	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}

	@Bean
	public PostMapper postMapper() {
		return Mappers.getMapper(PostMapper.class);
	}

}
