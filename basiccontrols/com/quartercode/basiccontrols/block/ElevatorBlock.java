
package com.quartercode.basiccontrols.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.block.ControlBlock;
import com.quartercode.minecartrevolution.block.ControlBlockInfo;
import com.quartercode.minecartrevolution.get.Lang;

public class ElevatorBlock extends ControlBlock {

    public ElevatorBlock() {

    }

    @Override
    protected ControlBlockInfo createInfo() {

        return new ControlBlockInfo(Lang.getValue("basiccontrols.blocks.elevator.name"), Lang.getValue("basiccontrols.blocks.elevator.description"), "elevator.place", "elevator.destroy", Material.DIAMOND_BLOCK.getId());
    }

    @Override
    public void execute(final Minecart minecart, final Location blockLocation, final int blockId, final Block block) {

        int heightCounter = minecart.getLocation().getBlockY() + 1;

        while (heightCounter <= minecart.getWorld().getMaxHeight()) {
            heightCounter++;

            final Block elevBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter, block.getZ());
            final Block railBlock2 = minecart.getWorld().getBlockAt(block.getX(), heightCounter + 1, block.getZ());
            for (final int id : getInfo().getBlockIds()) {
                if (elevBlock2.getTypeId() == id && (railBlock2.getType() == Material.RAILS || railBlock2.getType() == Material.POWERED_RAIL || railBlock2.getType() == Material.DETECTOR_RAIL)) {
                    executeExpression(minecart, "vertical " + (heightCounter - minecart.getLocation().getBlockY() + 1));
                    return;
                }
            }
        }
    }

}