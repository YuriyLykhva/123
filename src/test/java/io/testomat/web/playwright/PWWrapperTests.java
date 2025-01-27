package io.testomat.web.playwright;

import com.github.javafaker.Faker;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.PlaywrightWrapper;
import io.testomat.web.asserts.pw.TestSuitesPageAssertsPW;
import io.testomat.web.pages.LoginPage;
import io.testomat.web.pages.pw.LoginPagePW;
import io.testomat.web.pages.pw.ProjectsPagePW;
import io.testomat.web.pages.pw.TestSuitesPagePW;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("pwweb")
@ExtendWith(PWContextExtension.class)
public class PWWrapperTests {

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://app.testomat.io";
        Configuration.headless = false;
        Configuration.saveTraces = true;
        Configuration.poolingInterval = 100;
    }

    @Test
    @DisplayName("Should Be Possible To Create Test Suite For New Project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {

        PlaywrightWrapper.open("/users/sign_in");
        loginPage
                .isLoaded()
//                .loginUser("mmax68955@gmail.com", "d#6m@$MnPzEyg7Z");
//                .loginUser("yu1.0710@yopmail.com", "ZSx5EN!FHFvrubH");
                .loginUser(LoginPage.CredsWithRoles.YULU_KOTU);

        new ProjectsPagePW()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(faker.commerce().department())
                .submitProjectCreation();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPagePW()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuiteName(targetTestSuite)

                //todo just as an option:
                .asserts()
                .listShouldHaveSize(1)
                .firstTestSuiteInListShouldHaveText(targetTestSuite);


        //just for example
        new TestSuitesPageAssertsPW()
                .listShouldHaveSize(1)
                .firstTestSuiteInListShouldHaveText(targetTestSuite);

    }
}
