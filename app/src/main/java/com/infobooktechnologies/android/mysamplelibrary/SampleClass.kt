package com.infobooktechnologies.android.mysamplelibrary
class UserInfo {
    var username = "Bob"
    var phoneNumber = 16047773333
    val accounts = mutableListOf<Account>(account1, account2)

    val hasMultipleAccounts: Boolean
    get() = if (accounts != null) {
        accounts.size > 1
        true
    } else {
        false
    }

    fun changePhone(newPhone:Long) {
        phoneNumber = newPhone
    }
}

data class Account(var id:Int)


class EnterBiddingStateMachine(
        val userInfo: UserInfo?,
        val auction: Auction,
        val isGuest: Boolean,
        config: (BidStateMachineParameters.() -> Unit)? = null) {

    val isSingleAccount: Boolean
        get() = userInfo!!.hasMultipleAccounts

    val isSingleAccountAndRegistered: Boolean
        get() = isSingleAccount && AccountEx(userInfo!!.accounts[0]).isRegisteredAndAccepted()

    fun clearName() {
        userInfo?.username = null
    }

    fun enterBidding(){
        when {
            isSingleAccountAndRegistered -> enterAuction(auction)
            isSingleAccount -> register(auction, userInfo!!)
            else ->
                val params = Parameters(auction, userInfo)
            // config the account / what line goes here?
            Erro Msg("Network Error", "Invalid user")

        }
    }

    fun enterAuction(val auction:Auction):Boolean {
        ...
        return true
    }

    fun register(val auction:Auction, val userInfo:UserInfo):Boolean {
        ...
        return true
    }
}
