package stepDefinition;

import java.io.IOException;

import Driver.Driver;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() throws IOException {
        Driver.quitDriver();  
}
}