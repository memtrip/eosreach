/*
Copyright (C) 2018-present memtrip

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.memtrip.eosreach.app.welcome.createaccount

import android.os.SystemClock
import com.memtrip.eosreach.R
import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.api.StubApi
import com.memtrip.eosreach.api.stub.Stub
import com.memtrip.eosreach.api.stub.StubMatcher
import com.memtrip.eosreach.api.stub.request.BasicStubRequest
import com.memtrip.eosreach.api.stub.request.ErrorOnFirstStubRequest
import com.memtrip.eosreach.billing.BillingConnectionResponse
import com.memtrip.eosreach.billing.SkuStub

class BillingFlowAccountErrorTestCase : StubTestCase() {

    override fun test() {
        splashRobot
            .selectCreateAccount()
        createAccountRobot
            .verifyEnterAccountNameScreen()
            .typeAccountName()
            .selectCreateAccountButton()

        SystemClock.sleep(3000)
    }

    override fun billingConnectionResponse(): BillingConnectionResponse {
        return BillingConnectionResponse.Success(
            skuDetails(SkuStub.SUCCESS)
        )
    }

    override fun stubApi(): StubApi = object : StubApi(context()) {

        private val request = ErrorOnFirstStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_key_accounts.json")
        })

        override fun getKeyAccounts(): Stub = Stub(
            StubMatcher(
                context.getString(R.string.app_default_eos_endpoint_root),
                Regex("v1/history/get_key_accounts$")
            ),
            request
        )

        override fun createAccount(): Stub = Stub(
            StubMatcher(
                context.getString(R.string.app_default_utility_endpoint_root),
                Regex("createAccount$")
            ),
            BasicStubRequest(400)
        )
    }
}