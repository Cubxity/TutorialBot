/*
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                     Version 2, December 2004
 *
 *  Copyright (C) 2018 Cubxity
 *
 *  Everyone is permitted to copy and distribute verbatim or modified
 *  copies of this license document, and changing it is allowed as long
 *  as the name is changed.
 *
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 */

package me.cubxity.tutorialbot.command.mod;

import me.cubxity.tutorialbot.command.Command;
import me.cubxity.tutorialbot.utils.EmbedUtils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collections;
import java.util.List;

/*
 * Created by Cubxity on 07/04/2018
 */
public class KickCommand extends Command {
    @Override
    public String getName() {
        return ".kick";
    }

    @Override
    public String getDescription() {
        return "Kick a member off your server";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList(".k");
    }

    @Override
    public void onExecute(GuildMessageReceivedEvent e, Message msg, String m, User a, Guild g, String... args) {
        Member target = msg.getMentionedMembers().isEmpty() ? null : msg.getMentionedMembers().get(0);
        if(target == null){
            e.getChannel().sendMessage(EmbedUtils.embed(":x: No member mentioned")).queue();
            return;
        }

        if(!e.getMember().hasPermission(Permission.KICK_MEMBERS) || !e.getMember().canInteract(target)){
            e.getChannel().sendMessage(EmbedUtils.embed(":x: You do not have permission to kick this member")).queue();
            return;
        }

        StringBuilder reason = new StringBuilder();
        for(int i = 2; i < args.length; i++)
            reason.append(args[i]).append(" ");
        g.getController().kick(target, reason.toString().isEmpty() ? "No reason provided" : reason.toString()).queue(
                success -> e.getChannel().sendMessage(EmbedUtils.embed("Successfully kicked "+target.getUser().getName()+"#"+target.getUser().getDiscriminator())).queue(),
                failure -> e.getChannel().sendMessage(EmbedUtils.embed("Could not kick "+target.getUser().getName()+"#"+target.getUser().getDiscriminator())).queue()
        );
    }
}
