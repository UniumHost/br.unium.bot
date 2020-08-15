package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Avatar extends Command {

    public Avatar() { this.guildOnly = false; this.name = "avatar"; }

    @Override
    protected void execute(CommandEvent ev) {
        String[] args = ev.getArgs().split(" ");
        if (ev.getMessage().getContentDisplay().equals(Config.Preffix + "avatar")) {

            Member User = ev.getMessage().getMember();
            String avatar = User.getUser().getAvatarUrl();
            String Username = User.getUser().getName();
            String footer = Config.footer;

            EmbedBuilder avataremb = new EmbedBuilder()
                    .setTitle("Avatar | " + Username)
                    .setColor(User.getColor())
                    .setImage(avatar)
                    .setFooter(footer);
            ev.reply(avataremb.build());
            ev.getMessage().delete().queue();

        }
        if (ev.getMessage().getMentionedMembers().size() == 1) {
            Member User = ev.getMessage().getMentionedMembers().get(0);
            String avatar = User.getUser().getAvatarUrl();
            String Username = User.getUser().getName();
            String footer = Config.footer;

            EmbedBuilder avataremb = new EmbedBuilder()
                    .setTitle("Avatar | " + Username)
                    .setColor(User.getColor())
                    .setImage(avatar)
                    .setFooter(footer);
            ev.reply(avataremb.build());
            ev.getMessage().delete().queue();
        }
            if (args[0].length() == 18) {
                try {
                    User user = ev.getJDA().getUserById(args[0]);

                    String avatar = user.getAvatarUrl();
                    String Username = user.getName();
                    String footer = Config.footer;

                    EmbedBuilder avataremb = new EmbedBuilder()
                            .setTitle("Avatar | " + Username)
                            .setColor(Color.blue)
                            .setImage(avatar)
                            .setFooter(footer);
                    ev.reply(avataremb.build());
                    ev.getMessage().delete().queue();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        }
    }
