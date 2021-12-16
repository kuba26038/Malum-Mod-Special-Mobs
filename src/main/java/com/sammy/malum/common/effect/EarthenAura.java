package com.sammy.malum.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.MobEffect;
import net.minecraft.potion.EffectType;

public class EarthenAura extends MobEffect
{
    public EarthenAura()
    {
        super(EffectType.BENEFICIAL, 6468648);
        addAttributeModifier(Attributes.ARMOR, "04448cbf-ee2c-4f36-b71f-e641a312834a", 3f, AttributeModifier.Operation.ADDITION);
        addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "dc5fc5d7-db54-403f-810d-a16de6293ffd", 1.5f, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
    }
}