package me.cubxity.tutorialbot.command.core;

import me.cubxity.tutorialbot.TutorialBot;
import me.cubxity.tutorialbot.command.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Help extends Command {
    @Override
    public String getName() {
        return ".help";
    }

    @Override
    public String getDescription() {
        return "Displays help for this bot";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(".h", ".?", ".cmd", ".cmds", ".commands");
    }

    @Override
    public void onExecute(GuildMessageReceivedEvent e, Message msg, String m, User a, Guild g, String... args) {
        HashMap<String, List<Command>> categories = new HashMap<>();
        TutorialBot.getCommandHandler().getRegisteredCommands().forEach(c ->{
            Class<? extends Command> clazz = c.getClass();
            String category = clazz.getPackage().getName();
            category = category.split("\\.")[category.split("\\.").length-1];
            List<Command> edit = categories.get(category);
            if (edit == null)
                edit = new ArrayList<>();
            edit.add(c);
            categories.put(category, edit);
        });
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle("Help menu for TutorialBot");
        b.setDescription("These are commands for the tutorial bot.");
        b.setColor(Color.CYAN);
        categories.forEach((cat, cmds)->{
            StringBuilder line = new StringBuilder();
            for (Command cmd : cmds)
                line.append("`").append(cmd.getName()).append("` ");
            b.addField(
                    cat,
                    line.toString(),
                    false
            );
        });
        e.getChannel().sendMessage(b.build()).queue();

    }
}
