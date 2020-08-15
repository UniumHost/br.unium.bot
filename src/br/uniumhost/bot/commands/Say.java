package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class Say extends Command {

    public Say() { this.guildOnly = false; this.name = "say"; }

    @Override
    protected void execute(CommandEvent ev) {
        if (ev.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            ev.getMessage().delete().queue();
            String[] args = ev.getArgs().split(" ");


            if (args[0].length() == 18) {
                try {
                    String say = ev.getMessage().getContentRaw().replace(args[0], "");
                    ev.getJDA().getTextChannelById(args[0]).sendMessage(say).queue();
                } catch (IllegalStateException ex) {
                    ev.reply("Digite uma mensagem válida.");
                }
            }
            if (ev.getMessage().getMentionedChannels().size() == 1) {
                ev.getMessage().getMentionedChannels().get(0).sendMessage(ev.getMessage().getContentRaw().replace(Config.Preffix + "say", "").replace("<#" + ev.getMessage().getMentionedChannels().get(0).getId() + ">", "")).queue();
            }
            if (args[0].length() == 0) {
                try {
                    ev.reply(ev.getAuthor().getAsMention() + ", use ``/say [Canal] <Mensagem>``.");
                } catch (IllegalStateException ex) {
                    ev.reply("Digite uma mensagem válida.");
                }
            } else {
                if (ev.getMessage().getMentionedChannels().size() == 1) {
                    return;
                }
                if (ev.getArgs().split(" ")[0].length() == 18) {
                    return;
                } else {
                    ev.reply(ev.getMessage().getContentRaw().replace(Config.Preffix + "say", ""));
                }
            }
        } else {
            ev.reply(":x: Sem permissão.");
        }
    }
}