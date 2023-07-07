package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.w3c.dom.DOMConfiguration;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndNavigateToURL();
    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("the test case started");
        sendText(ConfigReader.getPropertyValue("username"), loginPage.usernameField);
        sendText(ConfigReader.getPropertyValue("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        Log.info("the test case is in final step");
        String actualMsg = dash.WelcomeMsg.getText();
        String expectedMsg = "Welcome Admin";
        Assert.assertEquals(expectedMsg, actualMsg);
    }

    @When("user enters the username {string} and the password {string}")
    public void user_enters_the_username_and_the_password(String userName, String password) {
        sendText(userName, loginPage.usernameField);
        sendText(password, loginPage.passwordField);

    }

    @When("clicks on login Btn")
    public void clicks_on_login_btn() {
        click(loginPage.loginButton);

    }

    @Then("the user is logged in and verifies the message {string}")
    public void the_user_is_logged_in_and_verifies_the_message(String expectedMsg) {
        String actualMsg = dash.WelcomeMsg.getText();
        Assert.assertEquals(actualMsg, expectedMsg);


    }

    @Then("the user is not logged in and verifies the message {string}")
    public void the_user_is_not_logged_in_and_verifies_the_message(String expectedMsg) {
        String actualErrorMsg = loginPage.errorMsgField.getText();
        Assert.assertEquals(actualErrorMsg, expectedMsg);


    }

    @When("the user enters the userName password and clicks on login button Then the errorMsg is verified")
    public void the_user_enters_the_user_name_password_and_clicks_on_login_button_then_the_error_msg_is_verified(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> datalist = dataTable.asMaps();

//        iterate over the list of maps
        for (Map<String, String> data : datalist) {
            String userName = data.get("username");
            String password = data.get("password");
            String expectedError = data.get("errorMsg");

            sendText(userName, loginPage.usernameField);
            sendText(password, loginPage.passwordField);
            click(loginPage.loginButton);

            String actualError = loginPage.errorMsgField.getText();
            Assert.assertEquals(actualError, expectedError);

        }
    }
}




