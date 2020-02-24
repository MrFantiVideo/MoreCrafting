package fr.mrfantivideo.morecrafting.Configuration.Configs;

import fr.mrfantivideo.morecrafting.Configuration.AbstractConfig;

public class ConfigPermissions extends AbstractConfig
{
    public ConfigPermissions()
    {
        super("permissions.yml");
    }

    /**
     * Get Permission from config
     *
     * @param path Path
     *
     * @return Permission if exists, false otherwise
     */
    public String GetPermission(String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getString(path);
        return null;
    }

    public String GetAdminHelpPerm()
    {
        return GetPermission("permissions.morecrafting.admin.help");
    }

    public String GetAdminReloadPerm()
    {
        return GetPermission("permissions.morecrafting.admin.reload");
    }

    public String GetAdminBookPerm()
    {
        return GetPermission("permissions.morecrafting.admin.book");
    }
    
    public String GetAdminRecipesPerm()
    {
        return GetPermission("permissions.morecrafting.admin.recipes");
    }

    public String GetAdminAllPerm()
    {
        return GetPermission("permissions.morecrafting.admin.*");
    }

    public String GetBookPerm()
    {
        return GetPermission("permissions.morecrafting.book");
    }

    public String GetAllPerm()
    {
        return GetPermission("permissions.morecrafting.*");
    }
}
