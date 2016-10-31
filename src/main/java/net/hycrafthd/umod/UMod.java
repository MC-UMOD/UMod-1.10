package net.hycrafthd.umod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.event.*;
import net.hycrafthd.umod.ext.ExtensionList;
import net.hycrafthd.umod.gui.IMPL_MODELRENDERHELPER;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.obj.ObjInterpretter;
import net.hycrafthd.umod.render.GLHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name, dependencies = "required-after:corelib")
public class UMod {
	
	public static org.apache.logging.log4j.Logger log;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		ExtensionList.onStart(event);
		new UConfig(event.getSuggestedConfigurationFile());
		new PacketHandler();
		UReference.proxy.registerRenderer();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtensionList.onInit(event);
		new UPotion();
		new UItems();
		new UBlocks();
		new UArmor();
		new UDamageSource();
		new UBiome();
		new UEntity();
		new UTools();
		this.registerGenerators();
		this.registerEvents();
		UMod.log.info("Init Mod.");
	}
	
	public static GLHelper getGLHelper() {
		return new GLHelper(Minecraft.getMinecraft().getTextureManager(), Tessellator.getInstance().getBuffer());
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		ExtensionList.onPostInit(event);
		new UTiles();
		new URecipes();
		new UChestLoot();
		new UAchievement();
		net.hycrafthd.corelib.registry.NetworkRegistry.registerGuiHandler(new UGuiHandler());
		UReference.proxy.registerModels();
		UMod.log.info("Registered Mod.");
	}
	
	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		ExtensionList.onServer(event);
		new UCommands(event);
		UMod.log.info("Registered Mod Commands.");
	}
	
	public void registerEvents() {
		UEvent event = new UEvent();
		event.addEvent(new EventGettingRadiation());
		event.addEvent(new EventExecuteRadiation());
		event.addEvent(new ProcessHandler());
		event.addEvent(new EventGettingRadiationInv());
		event.addEvent(new EventLoadWorld());
		event.addEvent(new EventDrawHUD());
		event.addEvent(new EventPlayerJoin());
		event.addEvent(new EventToolTip());
		event.addEvent(new EventOnTick());
		event.addEvent(new EventChestInventory());
		event.register();
		UMod.log.info("Registered Mod Events.");
	}
	
	public void registerGenerators() {
		UGeneration generation = new UGeneration();
		generation.addGenerator(new UOreGeneration(), 0);
		generation.register();
		UMod.log.info("Registered Mod Generators.");
	}
	
}
