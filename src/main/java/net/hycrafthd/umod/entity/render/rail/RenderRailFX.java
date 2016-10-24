package net.hycrafthd.umod.entity.render.rail;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.umod.IMPL_LWJGLU;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRailFX extends Render<EntityRailFX> implements IRenderFactory<EntityRailFX>{
	
	public RenderRailFX(RenderManager r) {
		super(r);
	}
	
	@Override
	public void doRender(EntityRailFX entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		IMPL_LWJGLU.drawSwell("textures/blocks/stone.png", x, y - 0.5, z);
		// new VIADrawer(fl).drawNormal("", x, y, z, new RGBA(Color.white));
	}
	
	@Override
	public Render<? super EntityRailFX> createRenderFor(RenderManager manager) {
		return new RenderRailFX(manager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRailFX entity) {
		return null;
	}
}
