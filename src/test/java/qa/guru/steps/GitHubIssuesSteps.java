package qa.guru.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubIssuesSteps {

    @Step("Открыть главную страницу")
    public static void openMainPage() {
        open("");
    }

    @Step("Найти репозиторий {name}")
    public static void repositorySearchByName(String name) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(name);
        $("#query-builder-test").submit();
    }

    @Step("Открыть репозиторий {name}")
    public static void openRepositoryByName(String name) {
        $(byLinkText(name)).click();
    }

    @Step("Открыть вкладку Issues")
    public static void openIssuesPage() {
        $("#issues-tab").click();
    }

    @Step("Проверить, что issue {name} существует")
    public static void checkIssueExist(String name) {
        $(byText(name)).should(exist);
    }
}
