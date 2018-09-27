package com.memtrip.eosreach.app.welcome.createaccount

import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.billing.BillingConnectionResponse
import com.memtrip.eosreach.billing.SkuStub

class BillingFlowItemAlreadyOwnedWithoutLocalPurchaseIdTestCase : StubTestCase() {

    override fun test() {
        splashRobot.navigateCreateAccount()
        createAccountRobot
            .verifyEnterAccountNameScreen()
            .typeAccountName()
            .selectCreateAccountButton()
            .verifyBillingFlowPurchaseIdFatalError()
    }

    override fun billingConnectionResponse(): BillingConnectionResponse {
        return BillingConnectionResponse.Success(
            skuDetails(SkuStub.ITEM_ALREADY_OWNED)
        )
    }
}