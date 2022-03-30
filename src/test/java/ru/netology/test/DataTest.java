package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = System.getProperty("app.url");
        open(appUrl);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    public class CreditCard {

        @Test
        void shouldSuccessWithValidCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val validCardInformation = DataHelper.getValidCardInformation();
            cardData.fillCardInformationForSelectedWay(validCardInformation);
            cardData.checkIfPaymentSuccessful();
            val paymentId = SqlHelper.getPaymentId();
            val statusForPaymentByCreditCard = SqlHelper.getStatusForPaymentByCreditCard(paymentId);
            assertEquals("APPROVED", statusForPaymentByCreditCard);

        }

        @Test
        void shouldNotSuccessWithInvalidCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val validCardInformation = DataHelper.getInvalidCardInformation();
            cardData.fillCardInformationForSelectedWay(validCardInformation);
            cardData.checkIfPaymentNotSuccessful();
            val paymentId = SqlHelper.getPaymentId();
            val statusForPaymentByCreditCard = SqlHelper.getStatusForPaymentByCreditCard(paymentId);
            assertThat(statusForPaymentByCreditCard, equalTo("DECLINED"));
        }

        @Test
        void shouldNotSuccessWithWrongCreditCardNumber() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongLongCardNumber();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardNumber();
            System.out.print("Failed test for a wrong Credit Card number");

        }

        @Test
        void shouldNotSuccessWithShortestCreditCardNumber() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithShortestCardNumber();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardNumber();

        }

        @Test
        void shouldNotSuccessWithWrongMonthCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongMonth();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardMonth();

        }

        @Test
        void shouldNotSuccessWithWrongYearCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongYear();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardYear();

        }

        @Test
        void shouldNotSuccessWithWrongYearFromOneNumberCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongYearWithOneNumber();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardYear();

        }

        @Test
        void shouldNotSuccessWithWrongCVCCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongCvc();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardCode();

        }

        @Test
        void shouldNotSuccessWithWrongNameCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithWrongHolderName();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardOwner();

        }

        @Test
        void shouldNotSuccessWithoutNameCreditCard() {
            val mainPage = new MainPage();
            val cardData = mainPage.buyByCreditCard();
            val invalidCardInformation = DataHelper.getCardInformationWithoutName();
            cardData.fillCardInformationForSelectedWay(invalidCardInformation);
            cardData.checkIfWrongCardOwner();

        }

        @Nested
        public class DebitCard {
            @Test
            void shouldSuccessWithValidDebitCard() {
                val mainPage = new MainPage();
                val cardData = mainPage.buyByDebitCard();
                val validCardInformation = DataHelper.getValidCardInformation();
                cardData.fillCardInformationForSelectedWay(validCardInformation);
                cardData.checkIfPaymentSuccessful();
                val paymentId = SqlHelper.getPaymentId();
                val statusForPaymentByDebitCard = SqlHelper.getStatusForPaymentByDebitCard(paymentId);
                val paymentAmount = SqlHelper.getPaymentAmount(paymentId);
                assertEquals("APPROVED", statusForPaymentByDebitCard);

            }

            @Test
            void shouldNotSuccessWithInvalidDebitCard() {
                val mainPage = new MainPage();
                val cardData = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getInvalidCardInformation();
                cardData.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData.checkIfPaymentNotSuccessful();
                val paymentId = SqlHelper.getPaymentId();
                val statusForPaymentByDebitCard = SqlHelper.getStatusForPaymentByDebitCard(paymentId);
                assertThat(statusForPaymentByDebitCard, equalTo("DECLINED"));

            }

            @Test
            void shouldNotSuccessWithWrongDebitCardNumber() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongLongCardNumber();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardNumber();

            }

            @Test
            void shouldNotSuccessWithShortestDebitCardNumber() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithShortestCardNumber();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardNumber();

            }

            @Test
            void shouldNotSuccessWithWrongMonthDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongMonth();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardMonth();

            }

            @Test
            void shouldNotSuccessWithWrongYearDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongYear();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardYear();

            }

            @Test
            void shouldNotSuccessWithWrongYearFromOneNumberDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongYearWithOneNumber();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardYear();

            }

            @Test
            void shouldNotSuccessWithWrongCVCDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongCvc();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardCode();

            }

            @Test
            void shouldNotSuccessWithWrongNameDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithWrongHolderName();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardOwner();

            }

            @Test
            void shouldNotSuccessWithoutNameDebitCard() {
                val mainPage = new MainPage();
                val cardData2 = mainPage.buyByDebitCard();
                val invalidCardInformation = DataHelper.getCardInformationWithoutName();
                cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
                cardData2.checkIfWrongCardOwner();
            }
        }
    }
}




