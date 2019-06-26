package net.mrfantivideo.morecrafting.Configuration.Configs;

import net.mrfantivideo.morecrafting.Configuration.AbstractConfig;

public class ConfigPermissions extends AbstractConfig
{
    public ConfigPermissions()
    {
        super("permissions.yml");
    }

    /**
     * Get Permission from config
     * @param path Path
     * @return Permission if exists, false otherwise
     */
    public String GetPermission(String path)
    {
        if(m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getString(path);
        return null;
    }

    /**
     * Get Admin Reload Permission
     * @return Admin Reload Permission if exists, null otherwise
     */
    public String GetAdminReloadPerm()
    {
        return GetPermission("permissions.morecrafting.admin.reload");
    }

    /**
     * Get Admin book Permission
     * @return Admin Book Permission if exists, null otherwise
     */
    public String GetAdminBookPerm()
    {
        return GetPermission("permissions.morecrafting.admin.book");
    }

    /**
     * Get Admin All Permissions
     * @return Admin All Permissions if exists, null otherwise
     */
    public String GetAdminAllPerm()
    {
        return GetPermission("permissions.morecrafting.admin.*");
    }

    /**
     * Get Book Permission
     * @return Book Permission if exists, null otherwise
     */
    public String GetBookPerm()
    {
        return GetPermission("permissions.morecrafting.book");
    }

    /**
     * Get All Permissions
     * @return All Permissions if exists, null otherwise
     */
    public String GetAllPerm()
    {
        return GetPermission("permissions.morecrafting.*");
    }
}
