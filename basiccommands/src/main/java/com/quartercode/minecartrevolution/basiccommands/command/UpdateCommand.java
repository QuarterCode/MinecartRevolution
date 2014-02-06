/*
 * This file is part of MinecartRevolution-Basiccommands.
 * Copyright (c) 2012 QuarterCode <http://www.quartercode.com/>
 *
 * MinecartRevolution-Basiccommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MinecartRevolution-Basiccommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MinecartRevolution-Basiccommands. If not, see <http://www.gnu.org/licenses/>.
 */

package com.quartercode.minecartrevolution.basiccommands.command;

import com.quartercode.minecartrevolution.core.MinecartRevolution;
import com.quartercode.minecartrevolution.core.command.MRCommandHandler;
import com.quartercode.minecartrevolution.core.exception.SilentMinecartRevolutionException;
import com.quartercode.minecartrevolution.core.util.Updater;
import com.quartercode.quarterbukkit.api.command.Command;
import com.quartercode.quarterbukkit.api.command.CommandInfo;
import com.quartercode.quarterbukkit.api.exception.ExceptionHandler;
import com.quartercode.quarterbukkit.api.query.FilesQuery.ProjectFile;
import com.quartercode.quarterbukkit.api.query.QueryException;

public class UpdateCommand extends MRCommandHandler {

    public UpdateCommand() {

    }

    @Override
    public CommandInfo createInfo() {

        return new CommandInfo(true, null, MinecartRevolution.getLang().get("basiccommands.update.description"), "minecartrevolution.command.versions.update", "update");
    }

    @Override
    public void execute(Command command) {

        for (Updater updater : minecartRevolution.getUpdaters()) {
            try {
                ProjectFile latestVersion = updater.getLatestVersion();
                if (!latestVersion.equals(minecartRevolution.getDescription().getVersion())) {
                    command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.update.update", "plugin", updater.getPlugin().getName(), "newVersion", latestVersion.getVersion()));
                    updater.changeVersion(latestVersion);
                    command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.update.updated", "plugin", updater.getPlugin().getName(), "newVersion", latestVersion.getVersion()));
                }
            }
            catch (QueryException e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Update: Something went wrong while querying the server mods api"));
                command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.update.error", "plugin", updater.getPlugin().getName(), "error", e.toString()));
            }
            catch (Exception e) {
                ExceptionHandler.exception(new SilentMinecartRevolutionException(minecartRevolution, e, "Update: An unknown error occurred"));
                command.getSender().sendMessage(MinecartRevolution.getLang().get("basiccommands.update.error", "plugin", updater.getPlugin().getName(), "error", e.toString()));
            }
        }
    }

}
