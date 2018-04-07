package me.cubxity.tutorialbot;

import me.cubxity.tutorialbot.command.core.Help;
import me.cubxity.tutorialbot.command.mod.KickCommand;
import me.cubxity.tutorialbot.handlers.CommandHandler;
import me.cubxity.tutorialbot.handlers.EventHandler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;

public class TutorialBot {
    private static JDA jda;
    private static CommandHandler commandHandler = new CommandHandler();

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static void main(String... args){
        try {
            registerCommands();
            jda = new JDABuilder(AccountType.BOT)
                    .setToken("ha u think u will git my token? nei")
                    .setGame(Game.of(Game.GameType.DEFAULT, "with codes"))
                    .addEventListener(new EventHandler())
                    .buildBlocking();
        } catch (LoginException | RateLimitedException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void registerCommands() {
        commandHandler.register(new Help());
        commandHandler.register(new KickCommand());
    }
}
