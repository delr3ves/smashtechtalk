package io.delr3ves.smashtechtalk.services;

import io.delr3ves.smashtechtalk.SmashtechConfig;
import io.delr3ves.smashtechtalk.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class UserServiceWebdriverImpl implements UserService {

    private WebDriver driver;
    private SmashtechConfig config;

    @Inject
    public UserServiceWebdriverImpl(WebDriver driver, SmashtechConfig config) {
        this.driver = driver;
        this.config = config;
    }

    @Override
    public User register(User user) {
        driver.findElement(By.id("registro_nombre")).clear();
        driver.findElement(By.id("registro_nombre")).sendKeys(user.getName());

        Integer genrePosition = 1;
        if (user.getGenre()!= null && user.getGenre().equals(User.Genre.FEMALE)) {
            genrePosition = 2;
        }
        driver.findElement(By.xpath("(//input[@name='registro_sexo'])[" + genrePosition + "]")).click();

        driver.findElement(By.id("registro_apellidos")).clear();
        driver.findElement(By.id("registro_apellidos")).sendKeys(user.getSurname());
        driver.findElement(By.id("registro_email")).clear();
        driver.findElement(By.id("registro_email")).sendKeys(user.getEmail());
        if (user.getReceiveOffers()) {
            driver.findElement(By.name("recibir_ofertas")).click();
        }

        driver.findElement(By.id("registro_password")).clear();
        driver.findElement(By.id("registro_password")).sendKeys(user.getPassword());

        if (user.getRegion() != null) {
            driver.findElement(By.className("select2-choice")).click();
            List<WebElement> regions = driver.findElements(By.className("select2-result-label"));
            Optional<WebElement> maybeRegion =
                    regions.stream().filter((WebElement e) -> e.getText().equals(user.getRegion())).findFirst();
            if (maybeRegion.isPresent()) {
                maybeRegion.get().click();
            }
        }

        driver.findElement(By.id("registro_dia_nacimiento")).clear();
        driver.findElement(By.id("registro_dia_nacimiento")).sendKeys(user.getBirthdayDay());
        driver.findElement(By.id("registro_mes_nacimiento")).clear();
        driver.findElement(By.id("registro_mes_nacimiento")).sendKeys(user.getBirthdayMonth());
        driver.findElement(By.id("registro_anyo_nacimiento")).clear();
        driver.findElement(By.id("registro_anyo_nacimiento")).sendKeys(user.getBirthdayYear());

        driver.findElement(By.id("js-input-save")).click();

        return user;
    }

}
