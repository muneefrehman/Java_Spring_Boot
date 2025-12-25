package com.example.demo.repositories;

import com.example.demo.TestDataUtil;
import com.example.demo.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTests {

    private final AuthorRepository undertest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntity);
        Optional<AuthorEntity> result = undertest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        undertest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        undertest.save(authorEntityC);

        Iterable<AuthorEntity> result = undertest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorEntityA, authorEntityB, authorEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntityA);
        authorEntityA.setName("UPDATED");
        undertest.save(authorEntityA);
        Optional<AuthorEntity> result = undertest.findById(authorEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntityA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntityA);

        undertest.deleteById(authorEntityA.getId());
        Optional<AuthorEntity> result = undertest.findById(authorEntityA.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        undertest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        undertest.save(authorEntityC);

        Iterable<AuthorEntity> result = undertest.ageLessThan(50);
        assertThat(result)
                .containsExactly(authorEntityB, authorEntityC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        undertest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        undertest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        undertest.save(authorEntityC);

        Iterable<AuthorEntity> result = undertest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result)
                .containsExactly(authorEntityA);
    }

}
