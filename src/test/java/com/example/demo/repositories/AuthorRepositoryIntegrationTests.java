package com.example.demo.repositories;

import com.example.demo.TestDataUtil;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository undertest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        undertest.save(author);
        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        undertest.create(authorA);
//        Author authorB = TestDataUtil.createTestAuthorB();
//        undertest.create(authorB);
//        Author authorC = TestDataUtil.createTestAuthorC();
//        undertest.create(authorC);
//
//        List<Author> result = undertest.find();
//        assertThat(result)
//                .hasSize(3)
//                .containsExactly(authorA, authorB, authorC);
//    }
//
//    @Test
//    public void testThatAuthorCanBeUpdated() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        undertest.create(authorA);
//        authorA.setName("UPDATED");
//        undertest.update(authorA.getId(), authorA);
//        Optional<Author> result = undertest.findOne(authorA.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorA);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        undertest.create(authorA);
//
//        undertest.delete(authorA.getId());
//        Optional<Author> result = undertest.findOne(authorA.getId());
//
//        assertThat(result).isEmpty();
//    }

}
