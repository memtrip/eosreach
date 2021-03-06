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
package com.memtrip.eosreach.app.account.vote.cast

import com.memtrip.mxandroid.MxRenderAction
import com.memtrip.mxandroid.MxViewLayout
import com.memtrip.mxandroid.MxViewRenderer
import javax.inject.Inject

sealed class CastVoteRenderAction : MxRenderAction {
    object CastProducerVoteTabIdle : CastVoteRenderAction()
    object CastProxyVoteTabIdle : CastVoteRenderAction()
    data class Populate(val page: CastVoteFragmentPagerFragment.Page) : CastVoteRenderAction()
}

interface CastVoteViewLayout : MxViewLayout {
    fun populate(page: CastVoteFragmentPagerFragment.Page)
}

class CastVoteViewRenderer @Inject internal constructor() : MxViewRenderer<CastVoteViewLayout, CastVoteViewState> {
    override fun layout(layout: CastVoteViewLayout, state: CastVoteViewState): Unit = when (state.view) {
        CastVoteViewState.View.Idle -> {
        }
        CastVoteViewState.View.Populate -> {
            layout.populate(state.page)
        }
    }
}