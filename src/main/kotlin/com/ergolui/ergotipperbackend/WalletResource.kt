package com.ergolui.ergotipperbackend

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/wallet")
class WalletResource(val service : WalletService) {
    @GetMapping("/")
    fun index(): List<Wallet> = service.findWallets()

    @GetMapping("/{wallet_id}/address")
    fun getAddress(@PathVariable wallet_id: String) : String = service.getAddress(wallet_id)

    @GetMapping("/{wallet_id}/balance")
    fun getBalance(@PathVariable wallet_id: String) : BalanceTotal = service.getBalance(wallet_id)

    @PostMapping
    fun post(@RequestBody wallet: Wallet) {
        service.post(wallet)
    }
}