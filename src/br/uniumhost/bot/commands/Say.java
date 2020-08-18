package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class Say {
    public void Command(Message Msg, Member User, String[] args, JDA jda) {
        if(User.hasPermission(Permission.MESSAGE_MANAGE)) {
            Msg.delete().queue();

            if(args[1].length() == 18) {
                try {
                        String say = Msg.getContentRaw().replace(args[0], "").replace(args[1], "");
                        jda.getTextChannelById(args[0]).sendMessage(say).queue();
                    } catch (IllegalStateException ex) {
                        Msg.getTextChannel().sendMessage("Digite uma mensagem válida.").queue();
                    }
            }
            if (Msg.getMentionedChannels().size() == 1) {
                Msg.getMentionedChannels().get(0).sendMessage(Msg.getContentRaw().replace(Config.Preffix + "say", "").replace("<#" + Msg.getMentionedChannels().get(0).getId() + ">", "")).queue();
            }
            if (args[1].length() == 0) {
                try {
                    Msg.getTextChannel().sendMessage(User.getAsMention() + ", use ``/say [Canal] <Mensagem>``.");
                } catch (IllegalStateException ex) {
                    Msg.getTextChannel().sendMessage("Digite uma mensagem válida.").queue();
                }
            } else {
                if (Msg.getMentionedChannels().size() == 1) {
                    return;
                }
                if (args[1].length() == 18) {
                    return;
                } else {
                    Msg.getTextChannel().sendMessage(Msg.getContentRaw().replace(Config.Preffix + "say", ""));
                }
            }
        } else {
            Msg.getTextChannel().sendMessage(":x: Sem permissão.");
        }
    }
}