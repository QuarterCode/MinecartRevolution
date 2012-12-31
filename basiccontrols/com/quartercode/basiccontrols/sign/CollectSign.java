
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class CollectSign extends ControlSign {

    public CollectSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.collect.name"), Lang.getValue("basiccontrols.signs.collect.description"), "collect.place", "collect.destroy", "collect");
    }

    @Override
    public void execute(final Minecart minecart, final Location signLocation, final String label, final Sign sign) {

        executeExpression(minecart, "collect " + sign.getLine(1) + sign.getLine(2) + sign.getLine(3));
    }

}
