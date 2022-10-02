package org.chubxu.typehelper.extension;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.chubxu.typehelper.settings.TypeHelperSettings;
import org.chubxu.typehelper.ui.TypeHelperConfigurableUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class TypeHelperConfigurable implements SearchableConfigurable {

    private TypeHelperSettings typeHelperSettings;
    private TypeHelperConfigurableUI typeHelperConfigurableUI;

    public TypeHelperConfigurable() {
        typeHelperSettings = TypeHelperSettings.getInstance();
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "org.chubxu.typehelper.extension.TypeHelperConfigurable";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "TypeHelper";
    }

    @Override
    public @Nullable JComponent createComponent() {
        if (Objects.isNull(typeHelperConfigurableUI)) {
            typeHelperConfigurableUI = new TypeHelperConfigurableUI();
        }
        return typeHelperConfigurableUI.mainPanel;
    }

    // Setting中的"Apply" button是否生效是由该方法进行判断。
    // 我们可以在该方法中定义自己的判断逻辑，比如用户修改了配置后，就让"Apply" button生效。
    // 该方法经常被调用，因此逻辑应该尽可能简单
    @Override
    public boolean isModified() {
        if (typeHelperSettings.getEnableTypeHelper() == typeHelperConfigurableUI.enableTypeHelperCheckBox.isSelected()) {
            return false;
        }
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        typeHelperSettings.setEnableTypeHelper(typeHelperConfigurableUI.enableTypeHelperCheckBox.isSelected());
    }

    // 点击reset按钮和重新打开配置页面时调用
    @Override
    public void reset() {
        typeHelperConfigurableUI.enableTypeHelperCheckBox.setSelected(typeHelperSettings.getEnableTypeHelper());
    }
}
