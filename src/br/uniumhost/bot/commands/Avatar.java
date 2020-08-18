package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

public class Avatar {
    public void Command(Message Msg, Member User, String[] args, JDA jda) {
        if (args.length == 1) {
            String avatar = User.getUser().getAvatarUrl();
            System.out.println(avatar);
            String Username = User.getUser().getName();
            String footer = Config.footer;

            EmbedBuilder avataremb = new EmbedBuilder()
                    .setTitle("Avatar | " + Username)
                    .setColor(User.getColor())
                    .setImage(avatar.replace("null", User.getUser().getDefaultAvatarUrl()))
                    .setFooter(footer);
            Msg.getTextChannel().sendMessage(avataremb.build()).queue();
            Msg.delete().queue();
        } else {
            if (Msg.getMentionedMembers().size() == 1) {
                Member Menc = Msg.getMentionedMembers().get(0);
                String avatar = Menc.getUser().getAvatarUrl();
                System.out.println(avatar);
                String Username = Menc.getUser().getName();
                String footer = Config.footer;

                EmbedBuilder avataremb = new EmbedBuilder()
                        .setTitle("Avatar | " + Username)
                        .setColor(User.getColor())
                        .setImage(avatar.replace("null", Msg.getMentionedMembers().get(0).getUser().getDefaultAvatarUrl()))
                        .setFooter(footer);
                Msg.getTextChannel().sendMessage(avataremb.build()).queue();
                Msg.delete().queue();
            }
            if (args[1].length() == 18) {
                try {
                    Member Mem = Msg.getGuild().getMemberById(args[1]);

                    String avatar = Mem.getUser().getAvatarUrl();
                    String Username = Mem.getUser().getName();
                    String footer = Config.footer;

                    EmbedBuilder avataremb = new EmbedBuilder()
                            .setTitle("Avatar | " + Username)
                            .setColor(Color.blue)
                            .setImage(avatar.replace("null", User.getUser().getDefaultAvatarUrl()))
                            .setFooter(footer);
                    Msg.getTextChannel().sendMessage(avataremb.build()).queue();
                    Msg.delete().queue();
                } catch (NullPointerException e) {

                }
                try {
                    this.finalize();
                } catch (Throwable a) {

                }
            }

        }
    }
}
