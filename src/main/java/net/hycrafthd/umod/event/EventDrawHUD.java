package net.hycrafthd.umod.event;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.gui.GuiModIngame;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.*;

public class EventDrawHUD {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onItemChanged(RenderGameOverlayEvent ev) {
		EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if (pl != null && pl.worldObj.isRemote) {
			if (pl.getHeldItemMainhand() != null && pl.getHeldItemMainhand().isItemEqual(new ItemStack(UItems.energydisplay))) {
				new GuiModIngame(ev.getResolution(), UReference.getClientProxy().getModelRenderHelper()).render();
			}
		}
	}
	
}