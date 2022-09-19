package com.demoqa.dakharitonov;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillAndSubmitFormTest () {
        String firstName = "Dmitry";
        String lastName = "Kharitonov";
        String email = "dmitry_kharitonovv@gmail.com";
        String gender = "Male";
        String number = "9155951498";
        String monthOfBirth = "February";
        String yearOfBirth = "1995";
        String dayOfBirth = "02";
        String subject = "Computer Science";
        String hobby = "Sports";
        String address = "Pushkin Street, Kolotushkin house";
        String state = "Haryana";
        String city = "Panipat";
        String imageName = "testImage.jpeg";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        String daySelector = ".react-datepicker__day--0" + dayOfBirth;
        $(daySelector).click();

        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#subjectsInput").setValue(subject).pressEnter();

        $("#uploadPicture").uploadFromClasspath(imageName);

        $("#currentAddress").setValue(address);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(number),
                text("02 " + monthOfBirth + "," + yearOfBirth),
                text(subject),
                text(hobby),
                text(imageName),
                text(address),
                text(state + " " + city));
    }
}
