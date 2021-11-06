package com.ergolui.ergotipperbackend;

import org.ergoplatform.P2PKAddress;
import org.ergoplatform.appkit.*;
import shapeless.ops.nat;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static org.ergoplatform.appkit.Parameters.MinFee;
import static org.ergoplatform.appkit.Parameters.MinChangeValue;

public class BoxOperationsWithMemory extends BoxOperations {

    public static List<InputBox> loadTop(
            BlockchainContext ctx,
            List<Address> senderAddresses, long amount,
            List<ErgoToken> tokensToSpend) {
        List<InputBox> unspentBoxes = new ArrayList<>();
        long remainingAmount = amount;
        SelectTokensHelper tokensHelper = new SelectTokensHelper(tokensToSpend);
        List<ErgoToken> remainingTokens = tokensToSpend;

        for (Address sender : senderAddresses) {
            CoveringBoxes unspent = ctx.getCoveringBoxesFor(sender, remainingAmount, remainingTokens);
            for (InputBox b : unspent.getBoxes()) {
                unspentBoxes.add(b);
                tokensHelper.foundNewTokens(b.getTokens());
                remainingAmount -= b.getValue();
                if (remainingAmount <= 0 && tokensHelper.areTokensCovered()) {
                    // collected enough boxes to cover the amount
                    break;
                }
            }
            if (remainingAmount <= 0 && tokensHelper.areTokensCovered()) break;
            remainingTokens = tokensHelper.getRemainingTokenList();
        }
        List<InputBox> selected = selectTop(unspentBoxes, amount, tokensToSpend);
        return selected;
    }

    /**
     * Creates a new {@link SignedTransaction} which sends the given amount of NanoErgs
     * to the given contract. The address of the given senderProver is used to collect
     * boxes for spending.
     */
    public static SignedTransaction putToContractTx(
            BlockchainContext ctx,
            ErgoProver senderProver, boolean useEip3Addresses,
            ErgoContract contract, long amountToSend,
            List<ErgoToken> tokensToSpend) {
        UnsignedTransaction tx = putToContractTxUnsigned(ctx,
                senderProver, useEip3Addresses, contract, amountToSend, tokensToSpend);
        SignedTransaction signed = senderProver.sign(tx);
        return signed;
    }

    /**
     * Creates a new {@link UnsignedTransaction} which sends the given amount of NanoErgs
     * to the given contract. The address of the given senderProver is used to collect
     * boxes for spending.
     */
    public static UnsignedTransaction putToContractTxUnsigned(
            BlockchainContext ctx,
            ErgoProver senderProver, boolean useEip3Addresses,
            ErgoContract contract, long amountToSend,
            List<ErgoToken> tokensToSpend) {
        List<Address> senders = new ArrayList<>();
        if (useEip3Addresses) {
            List<Address> eip3Addresses = senderProver.getEip3Addresses();
            checkState(eip3Addresses.size() > 0,
                    "EIP-3 addresses are not derived in the prover (use ErgoProverBuilder.withEip3Secret)");
            senders.addAll(eip3Addresses);
        } else {
            senders.add(senderProver.getAddress());
        }
        return putToContractTxUnsigned(ctx, senders, contract, amountToSend, tokensToSpend);
    }

    /**
     * Creates a new {@link UnsignedTransaction} which sends the given amount of NanoErgs
     * to the given contract. The addresses of the given senders are used to collect
     * boxes for spending.
     */
    public static UnsignedTransaction putToContractTxUnsigned(
            BlockchainContext ctx,
            List<Address> senders,
            ErgoContract contract, long amountToSend,
            List<ErgoToken> tokensToSpend) {
        List<InputBox> boxesToSpend = loadTop(ctx, senders, amountToSend + MinFee + MinChangeValue, tokensToSpend);

        P2PKAddress changeAddress = senders.get(0).asP2PK();
        UnsignedTransactionBuilder txB = ctx.newTxBuilder();

        OutBoxBuilder outBoxBuilder = txB.outBoxBuilder()
                .value(amountToSend)
                .contract(contract);
        if (!tokensToSpend.isEmpty())
            outBoxBuilder.tokens(tokensToSpend.toArray(new ErgoToken[]{}));
        OutBox newBox = outBoxBuilder.build();

        UnsignedTransaction tx = txB.boxesToSpend(boxesToSpend)
                .outputs(newBox)
                .fee(MinFee)
                .sendChangeTo(changeAddress)
                .build();
        return tx;
    }
}
