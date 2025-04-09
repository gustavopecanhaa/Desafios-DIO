package org.example;

public class iPhoneUser {
    public static void main(String[] args) {
        iPhone myPhone = new iPhone();

        // Testando o Reprodutor Musical
        myPhone.play();
        myPhone.pause();
        myPhone.selectMusic("Bohemian Rhapsody");

        // Testando o Aparelho Telefônico
        myPhone.call("123-456-7890");
        myPhone.answer();
        myPhone.startVoiceMail();

        // Testando o Navegador na Internet
        myPhone.displayPage("http://example.com");
        myPhone.addNewTab();
        myPhone.refreshPage();
    }
}