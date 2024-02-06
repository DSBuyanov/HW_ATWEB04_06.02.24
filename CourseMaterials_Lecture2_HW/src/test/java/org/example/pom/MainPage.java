package org.example.pom;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class MainPage {

    private SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");


    private SelenideElement createGroupButton = $("#create-btn");
    private SelenideElement loginNameField = $x("//*[@id=\"upsert-item\"]/div[5]/label/input");
    private SelenideElement groupNameField = $x("//*[@id=\"upsert-item\"]/div[1]/label/input");
    private SelenideElement submitButtonOnModalWindow = $("form div.submit button");

    private SelenideElement modalWindow = $x("//div[contains(@class, 'mdc-dialog') and //h2[contains(text(), 'Editing Dummy')]]");

    private SelenideElement closeCreateStudentsFormIcon = $x("//*[@id='app']/main/div/div/div[3]/div[2]/div/div[1]/button");

    private ElementsCollection rowsInGroupTable = $$x("//*[@id='app']/main/div/div/div[1]/div[1]/table/tbody/tr/td[4]");

    public MainPage() {

    }

    public void createGroup(String groupName) {
        createGroupButton.shouldBe(visible).click();
        groupNameField.shouldBe(visible).setValue(groupName);
        loginNameField.shouldBe(visible).setValue(groupName);
        submitButtonOnModalWindow.click();
        closeCreateStudentsFormIcon.click();
        modalWindow.should(disappear);
        firstGroupNameCell.shouldHave(text(groupName));


    }

    public void closeCreateGroupModalWindow() {
        closeCreateStudentsFormIcon.click();
        closeCreateStudentsFormIcon.should(disappear);
    }

    public SelenideElement firstGroupNameCell = $x("//*[@id='app']/main/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]");


    public String getUsernameLabelText() {
        return usernameLinkInNavBar.shouldBe(visible).getText().replace("\n", " ");
    }


}

