package com.sweetmanor.demo.io;

import java.util.prefs.Preferences;

/**
 * Preferences存储配置数据示例
 *
 * <pre>
 *     Preferences允许应用程序存储和获取用户和系统首选项以及配置数据，在Windows即存储在注册表中
 *         userRoot：  HKEY_CURRENT_USER\Software\JavaSoft\Prefs
 *         systemRoot：HKEY_LOCAL_MACHINE\Software\JavaSoft\Prefs
 * </pre>
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class PreferencesDemo1 {

    public static void main(String[] args) {
        Preferences userNode = Preferences.userNodeForPackage(PreferencesDemo1.class);
        userNode.put("user", "ijlhjj");
        String user = userNode.get("user", "获取不到值");
        System.out.println(user);
    }

}
