package br.uniumhost.bot;

import br.uniumhost.bot.commands.*;
import br.uniumhost.bot.listeners.Autorole;
import br.uniumhost.bot.listeners.Ready;
import br.uniumhost.bot.listeners.Tickets;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


public class Launcher extends ListenerAdapter {


    public static void main(String[] args) {
        CommandClientBuilder cli = new CommandClientBuilder()
                .setPrefix(Config.Preffix)
                .addCommand(new Userinfo())
                .addCommand(new Say())
                .addCommand(new Avatar())
                .addCommand(new Ticket())
                .setOwnerId(Config.Owner)
                .setCoOwnerIds(Config.Owners)
                .useHelpBuilder(false)
                .setActivity(Activity.watching(Config.Status));

        CommandClient client = cli.build();

        JDABuilder bot = new JDABuilder()
                .setToken(Config.Token)
                .addEventListeners(new Ready())
                .addEventListeners(new Autorole())
                .addEventListeners(new Tickets())
                .addEventListeners(client)
                .setAutoReconnect(true);

        try {
            bot.build();
        } catch (LoginException e) {
            System.out.println("Erro ao inicializar: " + e.getMessage());
        }

    }

}
