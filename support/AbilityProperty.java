package support;

// todo: mb it's better to split into several AbilityProperties: active/passive etc
public enum AbilityProperty implements Property {
    ABILITY,
        ACTIVE_ABILITY,
        DAMAGE_UP,
        DECREASE_DAMAGE,
        RAGE,
    AURA,
        VISION,
    BUFF,
        BLEEDING,
        DAMAGE_UP_BUFF,
        DECREASE_DAMAGE_BUFF,
        NECROSIS_BUFF,
        RAGE_BUFF,
    PASSIVE_ABILITY,
        PROFESSION,
            ALCHEMIST,
            BLACKSMITH,
            STEAL,
        CRITICAL_STRIKE,
        EVASION,
        LITTLE_FOOL,
        TWO_ONE_HANDED_WEAPONS,
    ENCHANTMENT,
        ARMOR_ENCHANTMENT,
            SPIKE_ARMOR,
            HIGHER_PATH,
        WEAPON_ENCHANTMENT,
            KORNEL_CURSE,
            VAMPIRISM
}
