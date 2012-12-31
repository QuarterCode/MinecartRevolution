
package com.quartercode.basiccontrols.sign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import com.quartercode.minecartrevolution.get.Lang;
import com.quartercode.minecartrevolution.sign.ControlSign;
import com.quartercode.minecartrevolution.sign.ControlSignInfo;

public class EffectSign extends ControlSign {

    public EffectSign() {

    }

    @Override
    public ControlSignInfo getInfo() {

        return new ControlSignInfo(Lang.getValue("basiccontrols.signs.effect.name"), Lang.getValue("basiccontrols.signs.effect.description"), "effect.place", "effect.destroy", "effect");
    }

    @Override
    public void execute(final Minecart minecart, final Location signLocation, final String label, final Sign sign) {

        for (final String line : new String[] { sign.getLine(1), sign.getLine(2), sign.getLine(3) }) {
            if (line.startsWith("+")) {
                executeExpression(minecart, "+ effect " + line.replaceAll("+", "").trim());
            } else if (line.startsWith("-")) {
                executeExpression(minecart, "- effect " + line.replaceAll("-", "").trim());
            } else {
                executeExpression(minecart, "effect " + line);
            }
        }
    }

}
