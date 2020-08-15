package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Ticket extends Command {

    public Ticket() { this.guildOnly = false; this.name = "ticket"; }

    @Override
    protected void execute(CommandEvent e) {

        String[] args = e.getArgs().split(" ");
        String[] cat = {"suporte", "duvidas"};
        Role StaffRole= e.getGuild().getRoleById(Config.StaffRole);

        if (e.getAuthor().isBot()) { return; }
        if (args[0].equals("finalizar")) {
            if (e.getMessage().getCategory().getId().equals(Config.Supportcategory)) {
                if (e.getMember().getRoles().contains(StaffRole)) {


                    Member mem = e.getTextChannel().getPermissionOverrides().get(1).getMember();
                    e.getGuild().removeRoleFromMember(mem, e.getGuild().getRoleById(Config.SupportRole)).queue();
                    mem.getUser().openPrivateChannel().complete().sendMessage("Seu ticket foi fechado por **" + e.getAuthor().getAsTag()  + "**.\nCaso tenha alguma reclamação em relação ao atendimento, envie uma mensagem ao canal <#742564724620787723>.").queue();
                    e.getTextChannel().delete().queue();
                }
            } else {
                e.reply("Você não está em um canal de ticket.");
            }
        }
        if (e.getChannel().getId().equals(Config.Supportchat)) {

                if (args[0].equals(cat[0])) {

                    e.getGuild().getCategoryById(Config.Supportcategory).createTextChannel( cat[0] + "-" + e.getAuthor().getAsTag().toLowerCase().replaceAll("#", "")).queue(ch -> {
                        e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(Config.SupportRole)).queue();

                        e.getTextChannel().sendMessage(e.getAuthor().getAsMention() + ", seu ticket foi criado. Vá ao canal " + ch.getAsMention() + ".").queue(msg -> {
                            e.getMessage().delete().queue();
                            try {
                                Thread.sleep(5000);
                                msg.delete().queue();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Ticket de suporte | " + e.getAuthor().getAsTag())
                                    .setDescription("Para encerrar este ticket, basta digitar **/ticket finalizar**. Lembrando que tudo que for dito aqui ficará gravado.")
                                    .setColor(e.getMember().getColor());
                            ch.sendMessage(embed.build()).queue();

                            ch.getManager().getChannel().createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                            ch.getManager().getChannel().createPermissionOverride(e.getGuild().getRoleById("729511218921865247")).setDeny(Permission.VIEW_CHANNEL).queue();
                        });

                    });

                } else {
                    if (args[0].equals(cat[1])) {

                        e.getGuild().getCategoryById(Config.Supportcategory).createTextChannel( cat[1] + "-" + e.getAuthor().getAsTag().toLowerCase().replaceAll("#", "")).queue(ch -> {

                            e.getTextChannel().sendMessage(e.getAuthor().getAsMention() + ", seu ticket foi criado. Vá ao canal " + ch.getAsMention() + ".").queue(msg -> {
                                e.getMessage().delete().queue();
                                try {
                                    Thread.sleep(5000);
                                    msg.delete().queue();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Ticket de dúvidas | " + e.getAuthor().getAsTag())
                                    .setDescription("Para encerrar este ticket, basta digitar **/ticket finalizar**. Lembrando que tudo que for dito aqui ficará gravado.")
                                    .setColor(e.getMember().getColor());
                            ch.sendMessage(embed.build()).queue();

                            ch.getManager().getChannel().createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                            ch.getManager().getChannel().createPermissionOverride(e.getGuild().getRoleById("729511218921865247")).setDeny(Permission.VIEW_CHANNEL).queue();

                        });

                    } else {
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Suporte | UniumHost")
                                    .setDescription("Tem uma dúvida, ou precisa de ajuda com um produto adquirido? \n Use ``" + Config.Preffix + "ticket <suporte/duvidas>``. \n **" + Config.Preffix + "ticket suporte**: Caso esteja tendo problemas com um produto adquirido. \n **" + Config.Preffix + "ticket duvidas**: Caso ainda não seja cliente e deseje tirar dúvidas relacionadas a hospedagem.")
                                    .setColor(e.getMember().getColor())
                                    .setFooter(Config.footer);
                            e.getTextChannel().sendMessage(embed.build()).queue(msg -> {
                                e.getMessage().delete().queue();
                                try {
                                    Thread.sleep(15000);
                                    msg.delete().queue();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            });
                    }
                }
        } else {
            if (!args[0].equals("finalizar"))
            e.reply("Comando disponível apenas no canal <#" + Config.Supportchat + ">.");
        }
    }
}
