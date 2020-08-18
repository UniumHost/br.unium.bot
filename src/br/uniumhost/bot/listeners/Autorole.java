package br.uniumhost.bot.listeners;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Autorole extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Date Data = new Date();

        if (e.getUser().isBot()) {
            Role cargobot = e.getGuild().getRoleById(Config.Botrole);
            e.getGuild().addRoleToMember(e.getMember(), cargobot).queue();
            e.getJDA().getTextChannelById(Config.Logschannel).sendMessage("[LOG] [" + new SimpleDateFormat("HH:mm:ss").format(Data) + "] **BOT " + e.getMember().getUser().getAsTag() + " foi adicionado e recebeu o seu respectivo cargo**.").queue();
            return;
        } else {
            Role cargouser = e.getGuild().getRoleById(Config.Memberrole);
            e.getGuild().addRoleToMember(e.getMember(), cargouser).queue();
            e.getJDA().getTextChannelById(Config.Logschannel).sendMessage("[LOG] [" + new SimpleDateFormat("HH:mm:ss").format(Data) + "] **USUÁRIO " + e.getMember().getUser().getAsTag() + " entrou e recebeu o seu respectivo cargo**.").queue();
            return;
        }

    }

}
