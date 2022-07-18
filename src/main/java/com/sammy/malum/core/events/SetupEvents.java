package com.sammy.malum.core.events;

import com.sammy.malum.common.capability.MalumItemDataCapability;
import com.sammy.malum.common.capability.MalumLivingEntityDataCapability;
import com.sammy.malum.common.capability.MalumPlayerDataCapability;
import com.sammy.malum.core.handlers.MissingMappingHandler;
import com.sammy.malum.core.setup.client.ParticleRegistry;
import com.sammy.ortus.capability.OrtusEntityDataCapability;
import com.sammy.ortus.capability.OrtusPlayerDataCapability;
import com.sammy.ortus.capability.OrtusWorldDataCapability;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        MalumPlayerDataCapability.registerCapabilities(event);
        MalumLivingEntityDataCapability.registerCapabilities(event);
        MalumItemDataCapability.registerCapabilities(event);
    }
    @SubscribeEvent
    public static void correctMissingItemMappings(RegistryEvent.MissingMappings<Item> event) {
        MissingMappingHandler.correctMissingItemMappings(event);
    }

    @SubscribeEvent
    public static void correctMissingBlockMappings(RegistryEvent.MissingMappings<Block> event) {
        MissingMappingHandler.correctMissingBlockMappings(event);
    }

    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
        ParticleRegistry.registerParticleFactory(event);
    }

}