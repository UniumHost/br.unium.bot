package br.uniumhost.bot.listeners;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;


public class Ready extends ListenerAdapter {

    public void onReady(ReadyEvent e) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("BOT INICIADO")
                .setColor(Color.GREEN)
                .setFooter(Config.footer);

        e.getJDA().getTextChannelById(Config.Logschannel).sendMessage(embed.build()).queue(msg -> {
                try {
                    Thread.sleep(10000);
                    msg.delete().queue();
                } catch (InterruptedException exc) {

                    exc.printStackTrace();

                }
        });
        return;
    }

}
