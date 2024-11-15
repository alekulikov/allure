package qa.guru.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static qa.guru.steps.gitHubIssuesSteps.*;

@Feature("Issue в репозитории")
@Story("Создание Issue")
@Owner("alekulikov")
@Link(value = "Testing", url = "https://github.com/alekulikov/allure")
class GitHubIssuesTest {

    public static String REPOSITORY = "alekulikov/allure";
    public static String ISSUES_NAME = "My test issue";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue для неавторизованного пользователя")
    void selenideIssueSearchByNameTest() {
        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
        $(byLinkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(byText(ISSUES_NAME)).should(exist);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue для неавторизованного пользователя")
    void lambdaIssueSearchByNameTest() {
        step("Открыть главную страницу", () -> open("/"));
        step("Найти репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Открыть репозиторий " + REPOSITORY,
                () -> $(byLinkText(REPOSITORY)).click());
        step("Открыть вкладку Issues", () -> $("#issues-tab").click());
        step("Проверить, что issue " + ISSUES_NAME + " существует",
                () -> $(byText(ISSUES_NAME)).should(exist));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue для неавторизованного пользователя")
    void annotationIssueSearchByNameTest() {
        openMainPage();
        repositorySearchByName(REPOSITORY);
        openRepositoryByName(REPOSITORY);
        openIssuesPage();
        checkIssueExist(ISSUES_NAME);
    }
}