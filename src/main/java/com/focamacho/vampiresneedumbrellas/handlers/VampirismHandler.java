package com.focamacho.vampiresneedumbrellas.handlers;

import com.focamacho.vampiresneedumbrellas.config.ConfigUmbrella;
import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.core.ModPotions;
import de.teamlapen.vampirism.player.vampire.VampirePlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class VampirismHandler {
	
	public static IAttribute sunDamage = VReference.sunDamage;

	public static void applyEffect(ItemStack stack, World worldIn, Entity entityIn, boolean breakable) {
		if(canApplyEffect(entityIn)) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if((ConfigUmbrella.umbrellaMainHand && player.getHeldItemMainhand().equals(stack)) || (ConfigUmbrella.umbrellaOffHand && player.getHeldItemOffhand().equals(stack))) {
				if(!player.isPotionActive(ModPotions.sunscreen)) {
					player.addPotionEffect(new PotionEffect(ModPotions.sunscreen, ConfigUmbrella.umbrellaProtectionTime * 20, 5, false, false));
					if(breakable) stack.damageItem(ConfigUmbrella.umbrellaProtectionTime, player);
				}
			}
		}
	}
	
	public static void applyCreativeEffect(ItemStack stack, World worldIn, Entity entityIn) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(ConfigUmbrella.creativeUmbrellaConfigs && ((ConfigUmbrella.umbrellaMainHand && player.getHeldItemMainhand().equals(stack)) || (ConfigUmbrella.umbrellaOffHand && player.getHeldItemOffhand().equals(stack)))) {
				if(!player.isPotionActive(ModPotions.sunscreen)) {
					player.addPotionEffect(new PotionEffect(ModPotions.sunscreen, ConfigUmbrella.umbrellaProtectionTime * 20, 5, false, false));
				}
			} else if(player.getHeldItemMainhand().equals(stack) || player.getHeldItemOffhand().equals(stack)){
				if(!player.isPotionActive(ModPotions.sunscreen)) {
					player.addPotionEffect(new PotionEffect(ModPotions.sunscreen, ConfigUmbrella.umbrellaProtectionTime * 20, 5, false, false));
				}
			}
		}
	}
	
	public static void applyBaubleEffect(ItemStack stack, World worldIn, Entity entityIn, boolean breakable) {
		if(canApplyEffect(entityIn)) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(!player.isPotionActive(ModPotions.sunscreen)) {
				player.addPotionEffect(new PotionEffect(ModPotions.sunscreen, ConfigUmbrella.umbrellaProtectionTime * 20, 5, false, false));
				if(breakable) stack.damageItem(ConfigUmbrella.umbrellaProtectionTime, player);
			}
		}
	}

	private static boolean canApplyEffect(Entity entity) {
		if(entity.world.isRemote || !(entity instanceof EntityPlayer)) return false;
		VampirePlayer vampire = VampirePlayer.get((EntityPlayer) entity);
		return (vampire.isGettingSundamage());
	}

}
