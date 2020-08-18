package br.uniumhost.bot;

import br.uniumhost.bot.commands.*;
import br.uniumhost.bot.listeners.Autorole;
import br.uniumhost.bot.listeners.Ready;
import br.uniumhost.bot.listeners.Tickets;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Launcher extends ListenerAdapter {
    public static void main(String[] args) {
        JDABuilder bot = new JDABuilder()
                .setToken(Config.Token)
                .addEventListeners(new Ready())
                .addEventListeners(new Autorole())
                .addEventListeners(new Tickets())
                .addEventListeners(new Commandlistener())
                .setAutoReconnect(true);
        try {
            bot.build();
        } catch (LoginException e) {
            System.out.println("Erro ao inicializar: " + e.getMessage());
        }
    }
}
