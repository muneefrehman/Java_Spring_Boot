package com.example.demo.dao.impl;

import com.example.demo.TestDataUtil;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl undertest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor();
        undertest.create(author);
        Optional<Author> result = undertest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

}
