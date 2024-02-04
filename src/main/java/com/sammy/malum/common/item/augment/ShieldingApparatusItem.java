package com.sammy.malum.common.item.augment;

import com.sammy.malum.registry.common.*;

public class ShieldingApparatusItem extends AbstractAugmentItem{
    public ShieldingApparatusItem(Properties pProperties) {
        super(pProperties, SpiritTypeRegistry.ARCANE_SPIRIT);
    }

    @Override
    public float getShieldingChance() {
        return 0.06f;
    }

    @Override
    public float getInstabilityIncrease() {
        return -0.01f;
    }

    @Override
    public float getSpeedIncrease() {
        return -0.2f;
    }
}
