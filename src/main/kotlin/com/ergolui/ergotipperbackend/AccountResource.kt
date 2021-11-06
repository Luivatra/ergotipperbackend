package com.ergolui.ergotipperbackend

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountResource(val service : AccountService) {

    @GetMapping("/{platform}/{username}")
    fun getAccount(@PathVariable("platform") platform: String,
                   @PathVariable("username") username: String): ETBAccount? = service.findAccount(platform,username)

    @PostMapping("/{platform}/{username}/changepass/")
    fun changePassword(
        @PathVariable("platform") platform: String,
        @PathVariable("username") username: String,
        @RequestBody passwordChange: PasswordChange)
    {
        service.changePassword(platform,username,passwordChange.password,passwordChange.newPassword)
    }

    @PostMapping("/restore/")
    fun restoreSeed(
        @RequestBody restoreOperation: RestoreOperation): RestoreOperation
    {
        return service.restoreSeed(restoreOperation)
    }

    @PostMapping("/seed/")
    fun getSeed(
        @RequestBody ETBAccount: ETBAccount): String
    {
        return service.getSeed(ETBAccount.platform,ETBAccount.username,ETBAccount.password)
    }

    @PostMapping
    fun post(@RequestBody ETBAccount: ETBAccount) {
        service.post(ETBAccount)
    }
}