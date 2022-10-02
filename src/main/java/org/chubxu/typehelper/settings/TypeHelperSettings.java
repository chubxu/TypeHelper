package org.chubxu.typehelper.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

@State(
        name = "TypeHelperSettings",
        storages = @Storage("TypeHelper.xml")
)
public class TypeHelperSettings implements PersistentStateComponent<TypeHelperSettings>, Serializable {

    // 1: check; 2: uncheck
    private boolean enableTypeHelper;

    public static TypeHelperSettings getInstance() {
        TypeHelperSettings typeHelperSettings = ApplicationManager.getApplication().getService(TypeHelperSettings.class);
        if (Objects.isNull(typeHelperSettings)) {
            typeHelperSettings = new TypeHelperSettings();
        }
        return typeHelperSettings;
    }

    @Override
    public @Nullable TypeHelperSettings getState() {
        return this;
    }

    @Override
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

    @Override
    public String toString() {
        return "TypeHelperSettings{" +
                "enableTypeHelper=" + enableTypeHelper +
                '}';
    }
}
