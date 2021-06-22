package com.minttea.cfbpmmocompat;

import harmonised.pmmo.config.JType;
import harmonised.pmmo.events.ChunkDataHandler;
import harmonised.pmmo.skills.Skill;
import harmonised.pmmo.util.XP;
import harmonised.pmmo.api.APIUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
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
        if(uuid != null && !world.isRemote)
        {
            Double award = APIUtils.getXp(input, JType.XP_VALUE_COOK).getOrDefault("cooking", 0);
            String source = "Cooking " + input.getItem().getRegistryName();
            APIUtils.addXp("cooking", uuid, award, source, false, false);
        }
    }
}
