package info.yizhou.SplitFix.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.java.IntType
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import info.yizhou.SplitFix.R


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

    override fun onHook() = encase {

        loadApp(name = "com.tencent.mm"){
            "androidx.window.embedding.SplitController".hook {
                injectMember {
                    method {
                        name = "initialize"
                        param(ContextClass, IntType)
                    }
                    beforeHook {
                        invokeOriginal(args.first(), R.xml.split)
                    }
                }
            }
        }
    }
}