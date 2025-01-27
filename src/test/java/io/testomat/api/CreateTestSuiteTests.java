package io.testomat.api;

import com.github.javafaker.Faker;
import io.testomat.api.controllers.AuthController;
import io.testomat.api.controllers.ProjectController;
import io.testomat.api.controllers.SuitesController;
import io.testomat.api.dtos.Attributes;
import io.testomat.api.dtos.DataItem;
import io.testomat.api.dtos.ProjectsDto;
import io.testomat.api.dtos.SuiteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTestSuiteTests {

    Faker faker = new Faker();

    @Test
    @DisplayName("Test name")
    void testName() {

        String jwtToken = new AuthController().loginUser("criyixubrica-8216@yopmail.net", "PRe8!cKa3BavZPS").getJwt();

        var projectTitle = ((ProjectsDto) new ProjectController()
                .withToken(jwtToken)
                .getAllProjects()
                .toObject())
                .getData().get(8).getAttributes()//gegege
                .getTitle().toLowerCase();

        var suitesController = new SuitesController().withToken(jwtToken);

        var suite = (SuiteDto) suitesController.createTestSuite(
                projectTitle,
                generateSuiteDto()
        ).toObject();

        suitesController.getSuiteForProjectById(projectTitle, suite.getData().getId());

        suitesController.getAllSuitesForProject(projectTitle);
    }

    private SuiteDto generateSuiteDto() {
        return SuiteDto.builder()
                .data(DataItem.builder()
                        .type("suites")
                        .attributes(Attributes.builder()
                                .title(faker.book().title())
                                .description(faker.book().publisher())
                                .build())
                        .build())
                .build();
    }
}
