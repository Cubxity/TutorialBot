package me.cubxity.tutorialbot.command;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collections;
import java.util.List;

public class Command {
    public  String getName(){
         return null;
     }
    public String getDescription(){
         return "Not available";
     }
    public List<String> getAliases(){
         return Collections.emptyList();
     }
     public void onExecute(GuildMessageReceivedEvent e, Message msg, String m, User a, Guild g, String... args){}

}
