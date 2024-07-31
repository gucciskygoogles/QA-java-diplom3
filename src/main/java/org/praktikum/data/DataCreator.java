package org.praktikum.data;


import io.qameta.allure.Step;
import org.praktikum.tools.Tools;


public class DataCreator {

    @Step("Создание рандомного пользователя")
    public static User generateRandomUser() {
        return new User(Tools.generateRandomEmail("yandex.ru", 5), Tools.generateRandomPassword(7), Tools.generateRandomName());
    }}