package me.yizhou.splitscreenfix

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.window.core.ExperimentalWindowApi
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.injectModuleAppResources
import com.highcapable.yukihookapi.hook.factory.registerModuleAppActivities
import com.highcapable.yukihookapi.hook.type.android.ActivityClass
import com.highcapable.yukihookapi.hook.type.android.BundleClass
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.java.IntType
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers.*
import org.jetbrains.annotations.NotNull

@InjectYukiHookWithXposed(isUsingResourcesHook = false)
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            isEnable = true
        }
        isDebug = true
        isEnableModulePrefsCache = true
        isEnableModuleAppResourcesCache = true
        isEnableHookModuleStatus = true
        isEnableDataChannel = true
        isEnableMemberCache = true
    }

    @OptIn(ExperimentalWindowApi::class)
    override fun onHook() = encase {

        loadZygote {

            "androidx.window.embedding.SplitController".hook {
                injectMember {
                    method {
                        name = "initialize"
                        param(ContextClass, IntType)
                    }
                    beforeHook {
                        (args().first() as Context).injectModuleAppResources()
                        (args().first() as Context).registerModuleAppActivities()
                        XposedBridge.log("\nbeforeHook: ${R.xml.split}\n")
                    }
                    afterHook {
                        XposedBridge.log("\nafterHook\n")
                    }
                }
            }
        }
    }
}