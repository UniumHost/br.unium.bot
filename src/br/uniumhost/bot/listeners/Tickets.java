package br.uniumhost.bot.listeners;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Tickets extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getMessage().getCategory().getId().equals("739587583629197373")) {
            e.getJDA().getTextChannelById(Config.TicketLogs).sendMessage("Mensagem de: " + e.getAuthor().getAsTag() + ". \nTexto: ``" + e.getMessage().getContentRaw() + "``. \nTicket: ``" + e.getChannel().getName() + "``.").queue();
        }
        return;
    }

}
