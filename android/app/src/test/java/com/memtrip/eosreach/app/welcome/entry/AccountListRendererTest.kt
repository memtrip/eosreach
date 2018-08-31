package com.memtrip.eosreach.app.welcome.entry

import com.memtrip.eosreach.app.welcome.AccountListViewLayout
import com.memtrip.eosreach.app.welcome.AccountListViewRenderer
import com.memtrip.eosreach.app.welcome.EntryViewState
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class AccountListRendererTest : Spek({

    val layout by memoized { mock<AccountListViewLayout>() }

    given("a AccountListRenderer") {

        val renderer by memoized { AccountListViewRenderer() }

        on("ViewState is not initialized") {
            renderer.layout(layout, EntryViewState(EntryViewState.View.OnProgress))

            it("shows the Progress") {
                verify(layout).showProgress()
            }
        }

        on("ViewState is initialized") {
            renderer.layout(layout, EntryViewState(EntryViewState.View.OnError))

            it("hides the Progress") {
                verify(layout).showError()
            }
        }
    }
})
