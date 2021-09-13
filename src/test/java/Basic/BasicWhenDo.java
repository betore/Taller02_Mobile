package Basic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicWhenDo {

    private AppiumDriver driver;

    @BeforeEach
    public void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","BHG7N16317010046");
        capabilities.setCapability("platformVersion","6");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // implicit wait - para todos los controles
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after(){
        driver.quit();
    }

    @Test
    public void verifyTheCreateContactApp() throws InterruptedException {
        Thread.sleep(2000);
        // click en el boton más
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();

        // Registrar una actividad
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Limpieza de la casa");
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Se realizará la limpieza de la casa");

        // Selecciona la opción de registro de actividad
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        // verificacion

        String expectedResult="Limpieza de la casa";
        String actualResult=driver.findElement(By.xpath("//android.widget.TextView[@text='Limpieza de la casa']")).getText();
        Assertions.assertEquals(expectedResult,actualResult, "ERROR tarea no creada");

    }


}
