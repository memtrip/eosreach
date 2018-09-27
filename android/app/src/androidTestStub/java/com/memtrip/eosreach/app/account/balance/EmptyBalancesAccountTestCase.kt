package com.memtrip.eosreach.app.account.balance

import com.memtrip.eosreach.R
import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.api.StubApi
import com.memtrip.eosreach.api.stub.Stub
import com.memtrip.eosreach.api.stub.StubMatcher
import com.memtrip.eosreach.api.stub.request.BasicStubRequest

class EmptyBalancesAccountTestCase : StubTestCase() {

    override fun test() {
        splashRobot.navigateImportKey()
        importKeyRobot
            .typePrivateKey("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")
            .selectImportButton()
        accountListRobot
            .verifyAccountListScreen()
            .selectFirstAccountRow()
        accountRobot
            .verifyAccountSuccess()
        balanceRobot
            .verifyBalanceScreen()
            .hiddenTokenTitle()
            .verifyEmptyBalanceMessage()
    }

    override fun stubApi(): StubApi = object : StubApi(context()) {

        override fun getCurrencyBalance(): Stub = Stub(
            StubMatcher(
                context.getString(R.string.app_default_eos_endpoint_root),
                Regex("v1/chain/get_currency_balance$")
            ),
            BasicStubRequest(200, {
                readJsonFile("stub/happypath/happy_path_get_empty_currency_balance.json")
            })
        )
    }
}