package org.example;

public class iPhone implements MusicPlayer, Telephone, InternetBrowser {

    @Override
    public void play() {
        System.out.println("Playing music...");
    }

    @Override
    public void pause() {
        System.out.println("Pausing music...");
    }

    @Override
    public void selectMusic(String music) {
        System.out.println("Selected music: " + music);
    }

    @Override
    public void call(String number) {
        System.out.println("Calling " + number + "...");
    }

    @Override
    public void answer() {
        System.out.println("Answering the call...");
    }

    @Override
    public void startVoiceMail() {
        System.out.println("Starting voice mail...");
    }

    @Override
    public void displayPage(String url) {
        System.out.println("Displaying page: " + url);
    }

    @Override
    public void addNewTab() {
        System.out.println("Added new tab.");
    }

    @Override
    public void refreshPage() {
        System.out.println("Refreshing page...");
    }
}