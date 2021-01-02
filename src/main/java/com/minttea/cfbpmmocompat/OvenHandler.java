package com.minttea.cfbpmmocompat;

import harmonised.pmmo.config.JType;
import harmonised.pmmo.events.ChunkDataHandler;
import harmonised.pmmo.skills.Skill;
import harmonised.pmmo.util.XP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class OvenHandler {


    private static final Logger LOGGER = LogManager.getLogger();
    public static void handleCooking(ItemStack input, World world, BlockPos pos)
    {
        UUID uuid = ChunkDataHandler.checkPos(world, pos);
        LOGGER.debug("Got UUID, " + uuid);
        if(uuid != null)
        {
            Double award = XP.getXp(input.getItem().getRegistryName(), JType.XP_VALUE_COOK).get("cooking");
            String source = "Cooking " + input.getItem().getRegistryName();
            Skill.COOKING.addXp(uuid, award, source, true, false);
        }
    }
}
