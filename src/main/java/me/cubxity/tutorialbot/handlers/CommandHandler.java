package me.cubxity.tutorialbot.handlers;

import me.cubxity.tutorialbot.command.Command;
import net.dv8tion.jda.core.entities.MessageType;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private List<Command> registeredCommands = new ArrayList<>();

    public List<Command> getRegisteredCommands() {
        return registeredCommands;
    }

    public void register(Command cmd){
        registeredCommands.add(cmd);
    }
    public void handle(GuildMessageReceivedEvent event){
        if(event.getMessage().getType() != MessageType.DEFAULT)return;
        String[] args = event.getMessage().getContentRaw().split(" ");
        registeredCommands.forEach(cmd ->{
            List<String> aliases = new ArrayList<>(cmd.getAliases());
            aliases.add(cmd.getName());
            aliases.forEach(a -> {
                if (args[0].equalsIgnoreCase(a))
                    cmd.onExecute(event, event.getMessage(), event.getMessage().getContentRaw(), event.getAuthor(), event.getGuild(), args);
            });
        });
    }
}
