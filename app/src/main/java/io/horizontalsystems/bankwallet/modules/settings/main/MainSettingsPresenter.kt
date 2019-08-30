package io.horizontalsystems.bankwallet.modules.settings.main

import androidx.lifecycle.ViewModel
import io.horizontalsystems.bankwallet.modules.settings.main.MainSettingsModule.IMainSettingsInteractor
import io.horizontalsystems.bankwallet.modules.settings.main.MainSettingsModule.IMainSettingsInteractorDelegate
import io.horizontalsystems.bankwallet.modules.settings.main.MainSettingsModule.IMainSettingsRouter
import io.horizontalsystems.bankwallet.modules.settings.main.MainSettingsModule.IMainSettingsView
import io.horizontalsystems.bankwallet.modules.settings.main.MainSettingsModule.IMainSettingsViewDelegate

class MainSettingsPresenter(
        val view: IMainSettingsView,
        val router: IMainSettingsRouter,
        private val interactor: IMainSettingsInteractor)
    : ViewModel(), IMainSettingsViewDelegate, IMainSettingsInteractorDelegate {

    private val helper = MainSettingsHelper()

    override fun viewDidLoad() {
        view.setBackedUp(helper.isBackedUp(interactor.nonBackedUpCount))
        view.setBaseCurrency(helper.displayName(interactor.baseCurrency))
        view.setLanguage(interactor.currentLanguageDisplayName)
        view.setLightMode(interactor.lightMode)
        view.setAppVersion(interactor.appVersion)
    }

    override fun didTapSecurity() {
        router.showSecuritySettings()
    }

    override fun didManageCoins() {
        router.showManageCoins()
    }

    override fun didTapBaseCurrency() {
        router.showBaseCurrencySettings()
    }

    override fun didTapLanguage() {
        router.showLanguageSettings()
    }

    override fun didSwitchLightMode(lightMode: Boolean) {
        interactor.lightMode = lightMode
        router.reloadAppInterface()
    }

    override fun didTapAbout() {
        router.showAbout()
    }

    override fun didTapCompanyLogo() {
        router.openLink(interactor.companyWebPageLink)
    }

    override fun didTapReportProblem() {
        router.showReportProblem()
    }

    override fun didTapTellFriends() {
        router.showShareApp(interactor.appWebPageLink)
    }

    // IMainSettingsInteractorDelegate

    override fun didUpdateNonBackedUp(count: Int) {
        view.setBackedUp(helper.isBackedUp(count))
    }

    override fun didUpdateBaseCurrency() {
        view.setBaseCurrency(helper.displayName(interactor.baseCurrency))
    }

    // ViewModel

    override fun onCleared() {
        interactor.clear()
    }

}
