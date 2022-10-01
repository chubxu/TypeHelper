package org.chubxu.typehelper.extension;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TypeHelperConfigurable implements SearchableConfigurable {

    @Override
    public @NotNull @NonNls String getId() {
        return "org.chubxu.typehelper.TypeHelperSettingComponent";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "TypeHelper";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return null;
    }

    // Setting中的"Apply" button是否生效是由该方法进行判断。
    // 我们可以在该方法中定义自己的判断逻辑，比如用户修改了配置后，就让"Apply" button生效。
    // 该方法经常被调用，因此逻辑应该尽可能简单
    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
