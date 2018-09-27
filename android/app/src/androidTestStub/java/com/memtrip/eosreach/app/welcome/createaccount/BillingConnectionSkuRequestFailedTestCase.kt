package com.memtrip.eosreach.app.welcome.createaccount

import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.billing.BillingConnectionResponse

class BillingConnectionSkuRequestFailedTestCase : StubTestCase() {

    override fun test() {
        splashRobot.navigateCreateAccount()
        createAccountRobot.verifyBillingUnavailable()
    }

    override fun billingConnectionResponse(): BillingConnectionResponse {
        return BillingConnectionResponse.SkuRequestFailed
    }
}