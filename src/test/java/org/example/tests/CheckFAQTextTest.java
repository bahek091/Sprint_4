package org.example.tests;

import org.example.pageobject.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CheckFAQTextTest extends BaseUITest{
    private String questionText;
    private String answerText;

    public CheckFAQTextTest(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void shouldBeCorrectFAQAnswersTest(){
        MainPage mainPage = openMainPage(driver);

        Assert.assertTrue("Answer differs from origin value.", answerText.equals(mainPage.getFAQAnswerByQuestionText(questionText)));
    }

}
