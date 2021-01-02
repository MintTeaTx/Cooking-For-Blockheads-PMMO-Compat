package com.minttea.cfbpmmocompat.mixins;

import com.minttea.cfbpmmocompat.OvenHandler;
import net.blay09.mods.cookingforblockheads.tile.OvenTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(OvenTileEntity.class)
public class OvenTileEntityMixin {


    private static final Logger LOGGER = LogManager.getLogger();

@Inject( method = "tick()V",
        at = @At(value = "INVOKE",target ="Lnet/minecraftforge/items/wrapper/RangedWrapper;setStackInSlot(ILnet/minecraft/item/ItemStack;)V", ordinal = 1),
        remap = false,
    locals = LocalCapture.CAPTURE_FAILHARD)
private void handleCooking(CallbackInfo ci, boolean hasChanged, int burnPotential, int firstEmptySlot, int firstTransferSlot, int i, ItemStack itemStack, double maxCookTime, ItemStack resultStack)
    {

        World world = ((TileEntity)(Object)this).getWorld();
        BlockPos pos = ((TileEntity)(Object)this).getPos();
        OvenHandler.handleCooking(itemStack, world, pos);
    }

}
