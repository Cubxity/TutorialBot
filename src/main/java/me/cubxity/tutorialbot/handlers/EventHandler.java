package me.cubxity.tutorialbot.handlers;

import me.cubxity.tutorialbot.TutorialBot;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TutorialBot.getCommandHandler().handle(event);
    }
}
