package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

public class Userinfo {

    public void Command(Message Msg, Member User, String[] args, JDA jda) {
        Msg.delete().queue();

        boolean case0 = args[1] == "";
        boolean case1 = Msg.getMentionedMembers().size() >= 1;
        boolean case2 = args[1].length() == 18;

        if (case0) {
            String Memberid = Msg.getAuthor().getId();

            Member Member = Msg.getGuild().getMemberById(Memberid);

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

            Msg.getTextChannel().sendMessage(userinfo.build()).queue();

        }
        if (case1){
            try {
                Member Member = Msg.getMentionedMembers().get(0);

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

                Msg.getTextChannel().sendMessage(userinfo.build()).queue();

            } catch (NullPointerException exc) {

                Msg.getTextChannel().sendMessage("Usuário inválido.").queue();

            }

        }

        if (case2) {

            try {
                String Memberid = args[0];

                Member Member = Msg.getGuild().getMemberById(Memberid);

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

                Msg.getTextChannel().sendMessage(userinfo.build()).queue();

            } catch (NullPointerException exc) {

                Msg.getTextChannel().sendMessage("Usuário inválido.").queue();

            }
        }

    }
}
