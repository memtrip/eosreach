package com.memtrip.eosreach.api.transfer

import com.memtrip.eos.core.crypto.EosPrivateKey
import com.memtrip.eosreach.api.ApiError
import com.memtrip.eosreach.api.Result
import com.memtrip.eosreach.api.balance.Balance
import io.reactivex.Single

interface TransferRequest {

    fun transfer(
        fromAccount: String,
        toAccount: String,
        quantity: Balance,
        memo: String,
        authorizingPrivateKey: EosPrivateKey
    ): Single<Result<TransferReceipt, TransferError>>
}

class TransferError(val body: String) : ApiError