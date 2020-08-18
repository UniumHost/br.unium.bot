package br.uniumhost.bot.commands;

import br.uniumhost.bot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

public class Ticket {
    public void Command(Message Msg, Member User, String[] args, JDA jda) {
        String[] cat = {"suporte", "duvidas"};
        Role StaffRole= Msg.getGuild().getRoleById(Config.StaffRole);

        if(User.getUser().isBot()) { return; }
        if(args.length == 2 && args[1].equals("finalizar")) {
            if(Msg.getCategory().getId().equals(Config.Supportcategory)) {
                if(Msg.getMember().getRoles().contains(StaffRole)) {
                    Member mem = Msg.getTextChannel().getPermissionOverrides().get(1).getMember();
                    Msg.getGuild().removeRoleFromMember(mem, Msg.getGuild().getRoleById(Config.SupportRole)).queue();
                    mem.getUser().openPrivateChannel().complete().sendMessage("Seu ticket foi fechado por **" + Msg.getAuthor().getAsTag()  + "**.\nCaso tenha alguma reclamação em relação ao atendimento, envie uma mensagem ao canal <#742564724620787723>.").queue();
                    Msg.getTextChannel().delete().queue();
                }
            } else {
                Msg.getTextChannel().sendMessage("Você não está em um canal de ticket.").queue();
            }
            return;
        }
        if (Msg.getChannel().getId().equals(Config.Supportchat)) {

                if (args.length == 2 && args[1].equals(cat[0])) {

                    Msg.getGuild().getCategoryById(Config.Supportcategory).createTextChannel( cat[0] + "-" + Msg.getAuthor().getAsTag().toLowerCase().replaceAll("#", "")).queue(ch -> {
                        Msg.getGuild().addRoleToMember(Msg.getMember(), Msg.getGuild().getRoleById(Config.SupportRole)).queue();

                        Msg.getTextChannel().sendMessage(Msg.getAuthor().getAsMention() + ", seu ticket foi criado. Vá ao canal " + ch.getAsMention() + ".").queue(msg -> {
                            Msg.delete().queue();
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Ticket de suporte | " + Msg.getAuthor().getAsTag())
                                    .setDescription("Para encerrar este ticket, basta digitar **/ticket finalizar**. Lembrando que tudo que for dito aqui ficará gravado.")
                                    .setColor(Msg.getMember().getColor());
                            ch.sendMessage(embed.build()).queue();

                            ch.getManager().getChannel().putPermissionOverride(Msg.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                            ch.getManager().getChannel().putPermissionOverride(Msg.getGuild().getRoleById("729511218921865247")).setDeny(Permission.ALL_PERMISSIONS).queue();
                            try {
                                Thread.sleep(5000);
                                msg.delete().queue();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        });

                    });

                } else {
                    if (args.length == 2 && args[1].equals(cat[1])) {

                        Msg.getGuild().getCategoryById(Config.Supportcategory).createTextChannel( cat[1] + "-" + Msg.getAuthor().getAsTag().toLowerCase().replaceAll("#", "")).queue(ch -> {

                            Msg.getTextChannel().sendMessage(Msg.getAuthor().getAsMention() + ", seu ticket foi criado. Vá ao canal " + ch.getAsMention() + ".").queue(msg -> {
                                Msg.delete().queue();
                                try {
                                    Thread.sleep(5000);
                                    msg.delete().queue();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Ticket de dúvidas | " + Msg.getAuthor().getAsTag())
                                    .setDescription("Para encerrar este ticket, basta digitar **/ticket finalizar**. Lembrando que tudo que for dito aqui ficará gravado.")
                                    .setColor(Msg.getMember().getColor());
                            ch.sendMessage(embed.build()).queue();
                            ch.getManager().getChannel().putPermissionOverride(Msg.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                            ch.getManager().getChannel().putPermissionOverride(Msg.getGuild().getRoleById("729511218921865247")).setDeny(Permission.VIEW_CHANNEL).queue();
                        });

                    } else {
                            EmbedBuilder embed = new EmbedBuilder()
                                    .setTitle("Suporte | UniumHost")
                                    .setDescription("Tem uma dúvida, ou precisa de ajuda com um produto adquirido? \n Use ``" + Config.Preffix + "ticket <suporte/duvidas>``. \n **" + Config.Preffix + "ticket suporte**: Caso esteja tendo problemas com um produto adquirido. \n **" + Config.Preffix + "ticket duvidas**: Caso ainda não seja cliente e deseje tirar dúvidas relacionadas a hospedagem.")
                                    .setColor(Msg.getMember().getColor())
                                    .setFooter(Config.footer);
                            Msg.getTextChannel().sendMessage(embed.build()).queue(msg -> {
                                Msg.delete().queue();
                                try {
                                    Thread.sleep(15000);
                                    msg.delete().queue();
                                } catch (InterruptedException exc) {

                                }
                            });
                    }
                }
        } else {
            if (!args[0].equals("finalizar"))
            Msg.getTextChannel().sendMessage("Comando disponível apenas no canal <#" + Config.Supportchat + ">.").queue();
        }
    }
}
