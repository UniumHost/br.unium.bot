package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class Userinfo extends Command {

    public Userinfo() { this.guildOnly = false; this.name = "userinfo"; }

    @Override
    protected void execute(CommandEvent ev) {
        String[] args = ev.getArgs().split(" ");
        ev.getMessage().delete().queue();

        boolean case0 = args[0] == "";
        boolean case1 = ev.getMessage().getMentionedMembers().size() >= 1;
        boolean case2 = args[0].length() == 18;

        if (case0) {
            String Memberid = ev.getAuthor().getId();

            Member Member = ev.getGuild().getMemberById(Memberid);

            String Useravatar = Member.getUser().getAvatarUrl();
            String Usernick = Member.getEffectiveName().replace(Member.getUser().getName(), "Usuário não possui apelido");

            String Namewithhash = Member.getUser().getAsTag();
            Color cor = Member.getColor();

            Activity act1 = Member.getActivities().get(0);

            String act0 = act1.getType().name().replace("WATCHING", "Assistindo:").replace("PLAYING", "Jogando:").replace("LISTENING TO", "Ouvindo:");

            int entrAno = Member.getTimeJoined().getYear();
            int entrMes = Member.getTimeJoined().getMonthValue();
            int entrDia = Member.getTimeJoined().getDayOfMonth();

            Boolean a = Member.getUser().isBot();

            String ebot = a.toString().replace("true", "Sim").replace("false", "Não");

            EmbedBuilder userinfo = new EmbedBuilder()
                    .setColor(cor)
                    .setFooter(Config.footer)
                    .setThumbnail(Useravatar)
                    .setTitle("Informações | " + Usernick)
                    .setDescription("**Tag**: " + Namewithhash + "\n **Apelido neste servidor**: " + Usernick + "\n **ID**: " + Memberid + "\n **Entrou aqui em**: " + entrDia + "/" + entrMes + "/" + entrAno + "\n **Status**: " + act0 + " " + act1.getName() + "\n **É BOT**? " + ebot);

            ev.reply(userinfo.build());

        }
        if (case1){
            try {
                Member Member = ev.getMessage().getMentionedMembers().get(0);

                String Useravatar = Member.getUser().getAvatarUrl();
                String Usernick = Member.getEffectiveName().replace(Member.getUser().getName(), "Usuário não possui apelido");
                String Namewithhash = Member.getUser().getAsTag();
                String Id = Member.getUser().getId();

                Color cor = Member.getColor();

                int Joinyear = Member.getTimeJoined().getYear();
                int Joinmonth = Member.getTimeJoined().getMonthValue();
                int Joinday = Member.getTimeJoined().getDayOfMonth();

                Boolean a = Member.getUser().isBot();

                String Isbot = a.toString().replace("true", "Sim").replace("false", "Não");

                EmbedBuilder userinfo = new EmbedBuilder()
                        .setColor(cor)
                        .setFooter(Config.footer)
                        .setThumbnail(Useravatar)
                        .setTitle("Informações | " + Usernick)
                        .setDescription("**Tag**: " + Namewithhash + "\n **Apelido neste servidor**: " + Usernick + "\n **ID**: " + Id + "\n **Entrou aqui em**: " + Joinday + "/" + Joinmonth + "/" + Joinyear + "\n **É BOT**? " + Isbot);

                ev.reply(userinfo.build());

            } catch (NullPointerException exc) {

                ev.reply("Usuário inválido.");

            }

        }

        if (case2) {

            try {
                String Memberid = args[0];

                Member Member = ev.getGuild().getMemberById(Memberid);

                Activity Memberactivity = Member.getActivities().get(0);

                String Useravatar = Member.getUser().getAvatarUrl();
                String Usernick = Member.getEffectiveName().replace(Member.getUser().getName(), "Usuário não possui apelido");
                String Namewithhash = Member.getUser().getAsTag();
                String Userid = Member.getUser().getId();
                String Memberactivity1 = Memberactivity.getType().name().replace("WATCHING", "Assistindo:").replace("PLAYING", "Jogando:").replace("LISTENING TO", "Ouvindo:").replace("DEFAULT", "Jogando:").replace("Custom status", "Status personalizado");

                Color Membercolor = Member.getColor();

                int Joinyear = Member.getTimeJoined().getYear();
                int Joinmonth = Member.getTimeJoined().getMonthValue();
                int Joinday = Member.getTimeJoined().getDayOfMonth();

                Boolean Isbot0 = Member.getUser().isBot();

                String Isbot = Isbot0.toString().replace("true", "Sim").replace("false", "Não");

                EmbedBuilder userinfo = new EmbedBuilder()
                        .setColor(Membercolor)
                        .setFooter(Config.footer)
                        .setThumbnail(Useravatar)
                        .setTitle("Informações | " + Usernick)
                        .setDescription("**Tag**: " + Namewithhash + "\n **Apelido neste servidor**: " + Usernick + "\n **ID**: " + Userid + "\n **Entrou aqui em**: " + Joinday + "/" + Joinmonth + "/" + Joinyear + "\n **Status**: " + Memberactivity1 + " " + Memberactivity.getName() + "\n **É BOT**? " + Isbot);

                ev.reply(userinfo.build());

            } catch (NullPointerException exc) {

                ev.reply("Usuário inválido.");

            }
        }

    }
}
