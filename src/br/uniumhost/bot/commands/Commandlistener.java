package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commandlistener extends ListenerAdapter {
    String[] Comandos = {"Avatar", "Say", "Ticket", "Userinfo"};

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getAuthor().isBot()) { return; }
        Member Membro = e.getMember();
        User Usuario = e.getMember().getUser();
        Message Msg = e.getMessage();
        String[] args = e.getMessage().getContentRaw().split(" ");

        if(args[0].startsWith(Config.Preffix)) {
            if (args[0].equalsIgnoreCase(Config.Preffix + Comandos[0])) {
                new Avatar().Command(Msg, Membro, args, e.getJDA());
                return;
            }
            if (args[0].equalsIgnoreCase(Config.Preffix + Comandos[1])) {
                new Say().Command(Msg, Membro, args, e.getJDA());
                return;
            }
            if (args[0].equalsIgnoreCase(Config.Preffix + Comandos[2])) {
                new Ticket().Command(Msg, Membro, args, e.getJDA());
                return;
            }
            if (args[0].equalsIgnoreCase(Config.Preffix + Comandos[3])) {
                new Userinfo().Command(Msg, Membro, args, e.getJDA());
                return;
            }
        } else { return; }
        return;
    }
}
