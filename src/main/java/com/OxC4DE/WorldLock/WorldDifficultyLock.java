package com.OxC4DE.WorldLock;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.xml.Null;

import java.io.File;

@Mod(modid = WorldDifficultyLock.MODID, name = WorldDifficultyLock.NAME, version = WorldDifficultyLock.VERSION)
public class WorldDifficultyLock
{
    public static final String MODID = "worlddifficultylock";
    public static final String NAME = "World Difficulty Lock";
    public static final String VERSION = "1.0";

    private static Logger logger = LogManager.getLogger(MODID);

    private MinecraftServer server;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event){MinecraftForge.EVENT_BUS.register(this);}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // some example code
        MinecraftForge.EVENT_BUS.register(this);
    }

    //@SubscribeEvent
    //public void onServerStart(FMLServerStartedEvent event){
    //    event.
    //}
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        logger.log(Level.INFO, String.format("Current Difficulty: %s", event.getWorld().getWorldInfo().getDifficulty()));
        if (event.getWorld().provider != null){
            logger.log(Level.INFO, String.format("Wrote difficulty for dim %s as PEACEFUL", event.getWorld().provider.getDimension()));
        }
        MinecraftServer source = event.getWorld().getMinecraftServer();
        if (source != null) {
            event.getWorld().getMinecraftServer().getCommandManager().executeCommand(source, "/difficulty 0");
        }
        //PropertyManager manager = new PropertyManager(new File("../server.properties"));
        //manager.setProperty("difficulty", Integer.valueOf(0));

        //World world = event.getWorld();

        //world.getWorldInfo().setDifficultyLocked(false);
        //world.getWorldInfo().setDifficulty(EnumDifficulty.PEACEFUL);
    }
}
