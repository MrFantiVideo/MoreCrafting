package fr.mrfantivideo.morecrafting.recipes;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomRecipePotion implements IConfigObject
{
    @ConfigValue(Path = "effect")
    private PotionEffectType m_effectType;
    @ConfigValue(Path = "duration")
    private Integer          m_duration;
    @ConfigValue(Path = "level")
    private Integer          m_level;
    @ConfigValue(Path = "ambient")
    private Boolean          m_ambient;
    @ConfigValue(Path = "particles")
    private Boolean          m_particles;
    @ConfigValue(Path = "icon")
    private Boolean          m_icon;

    @Override
    public void onConfigLoaded(String name)
    {
        m_duration = JavaUtils.ensureNotNull(m_duration, 1000);
        m_level = JavaUtils.ensureNotNull(m_level, 1);
        m_ambient = JavaUtils.ensureNotNull(m_ambient, true);
        m_particles = JavaUtils.ensureNotNull(m_particles, true);
        m_icon = JavaUtils.ensureNotNull(m_icon, true);
    }

    /**
     * Returns {@link PotionEffectType}
     *
     * @return Potion Effect Type
     */
    public PotionEffectType getEffectType()
    {
        return m_effectType;
    }

    /**
     * Returns potion duration
     *
     * @return Potion Duration
     */
    public int getDuration()
    {
        return m_duration;
    }

    /**
     * Returns potion level
     *
     * @return Potion level
     */
    public int getLevel()
    {
        return m_level;
    }

    /**
     * Returns potion ambient
     *
     * @return Potion Ambient
     */
    public boolean isAmbient()
    {
        return m_ambient;
    }

    /**
     * Returns potion particle visibility
     *
     * @return Potion particles visibility
     */
    public boolean hasParticles()
    {
        return m_particles;
    }

    /**
     * Returns potion icon visibility
     *
     * @return Potion icon visibility
     */
    public boolean hasIcon()
    {
        return m_icon;
    }

    /**
     * Returns a freshly created potion
     *
     * @return Potion
     */
    public PotionEffect getPotion()
    {
        return new PotionEffect(getEffectType(), getDuration(), getLevel(), isAmbient(), hasParticles(), hasIcon());
    }
}
