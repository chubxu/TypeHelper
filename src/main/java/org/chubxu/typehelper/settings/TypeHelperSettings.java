package org.chubxu.typehelper.settings;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TypeHelperSettings{

    private static class TypeHelperSettingsHolder {
        public static final TypeHelperSettings INSTANCE = new TypeHelperSettings();
    }


    // 1: check; 2: uncheck
    private boolean enableTypeHelper;

    public static TypeHelperSettings getInstance() {
        return TypeHelperSettingsHolder.INSTANCE;
    }

//    @Override
    public @Nullable TypeHelperSettings getState() {
        return null;
    }

//    @Override
    public void loadState(@NotNull TypeHelperSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isEnable() {
        return enableTypeHelper;
    }

    public boolean getEnableTypeHelper() {
        return enableTypeHelper;
    }

    public void setEnableTypeHelper(boolean enableTypeHelper) {
        this.enableTypeHelper = enableTypeHelper;
    }
}
