package com.ergolui.ergotipperbackend;

import com.ergolui.ergotipperbackend.explorer.client.model.*;
import com.google.common.base.Preconditions;
import org.ergoplatform.ErgoBox;
import org.ergoplatform.appkit.*;
import org.ergoplatform.appkit.impl.BlockchainContextImpl;
import org.ergoplatform.appkit.impl.ErgoNodeFacade;
import org.ergoplatform.appkit.impl.InputBoxImpl;
import org.ergoplatform.restapi.client.*;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class BlockchainContextWithMemory extends BlockchainContextImpl {
    private Retrofit _retrofitExplorer;
    private final Retrofit _retrofit;

    public BlockchainContextWithMemory(
            ApiClient client, Retrofit retrofit,
            ExplorerApiClient explorer, Retrofit retrofitExplorer,
            NetworkType networkType,
            NodeInfo nodeInfo, List<BlockHeader> headers) {
        super(client,retrofit,new org.ergoplatform.explorer.client.ExplorerApiClient(RestApiErgoClientWithMemory.defaultMainnetExplorerUrl),retrofitExplorer,networkType,nodeInfo,headers);
        _retrofitExplorer = retrofitExplorer;
        _retrofit = retrofit;
    }

    public List<TransactionInfo> getMempoolTransactionsFor(Address address, int offset, int limit) {
        Preconditions.checkNotNull(_retrofitExplorer, ErgoClient.explorerUrlNotSpecifiedMessage);
        List<TransactionInfo> transactions = ExplorerFacade
                .mempoolTransactionsByAddressIdGet(
                        _retrofitExplorer, address.toString(), offset, limit);
        return transactions;
    }

    public List<InputBox> getSpentInputs(Address address, List<TransactionInfo> txs) {
        ArrayList<InputBox> returnList = new ArrayList<InputBox>();
        for (TransactionInfo tx : txs) {
            for (InputInfo iBox : tx.getInputs()) {
                if(iBox.getAddress().equals(address.toString())) {
                    ErgoTransactionOutput boxInfo = ergoTransactionOutputFromInputInfo(iBox);
                    returnList.add(new InputBoxImpl(this, boxInfo));
                }
            }
        }
        return returnList;
    }

    public ErgoTransactionOutput ergoTransactionOutputFromInputInfo(InputInfo info) {
        ErgoTransactionOutput boxInfo = new ErgoTransactionOutput();
        boxInfo.setBoxId(info.getBoxId());
        boxInfo.setErgoTree(info.getErgoTree());
        boxInfo.setAssets(assetsFromAssetInfo(info.getAssets()));
        boxInfo.setValue(info.getValue());
        boxInfo.setIndex(info.getIndex());
        boxInfo.setAdditionalRegisters(registersFromAdditionalRegisters(info.getAdditionalRegisters()));
        return boxInfo;
    }

    public ErgoTransactionOutput ergoTransactionOutputFromOutputInfo(OutputInfo info) {
        ErgoTransactionOutput boxInfo = new ErgoTransactionOutput();
        boxInfo.setBoxId(info.getBoxId());
        boxInfo.setErgoTree(info.getErgoTree());
        boxInfo.setAssets(assetsFromAssetInfo(info.getAssets()));
        boxInfo.setValue(info.getValue());
        boxInfo.setTransactionId(info.getTransactionId());
        boxInfo.setIndex(info.getIndex());
        boxInfo.setCreationHeight(info.getCreationHeight());
        boxInfo.setAdditionalRegisters(registersFromAdditionalRegisters(info.getAdditionalRegisters()));
        return boxInfo;
    }

    public Registers registersFromAdditionalRegisters(AdditionalRegisters additionalRegisters) {
        Registers result = new Registers();
        additionalRegisters.forEach((key,value) -> {
            result.put(key,value);
        });
        return result;
    }

    public List<Asset> assetsFromAssetInfo(List<AssetInstanceInfo> infoList) {
        ArrayList<Asset> result = new ArrayList<>();
        for (AssetInstanceInfo info: infoList) {
            Asset asset = new Asset();
            asset.setAmount(info.getAmount());
            asset.setTokenId(info.getTokenId());
            result.add(asset);
        }
        return result;
    }

    public List<InputBox> getNewInputs(Address address, List<TransactionInfo> txs) {
        ArrayList<InputBox> returnList = new ArrayList<InputBox>();
        for (TransactionInfo tx : txs) {
            for (OutputInfo oBox : tx.getOutputs()) {
                if(oBox.getAddress().equals(address.toString())) {
                    returnList.add(new InputBoxImpl(this, ergoTransactionOutputFromOutputInfo(oBox)));
                }
            }
        }
        return returnList;
    }

    @Override
    public CoveringBoxes getCoveringBoxesFor(Address address, long amountToSpend, List<ErgoToken> tokensToSpend) {
        SelectTokensHelper tokensRemaining = new SelectTokensHelper(tokensToSpend);
        Preconditions.checkArgument(amountToSpend > 0 ||
                !tokensRemaining.areTokensCovered(), "amountToSpend or tokens to spend should be > 0");
        ArrayList<InputBox> result = new ArrayList<>();
        List<TransactionInfo> mempoolTransactions = getMempoolTransactionsFor(address, 0, 10000);
        List<InputBox> spentInputs = getSpentInputs(address,mempoolTransactions);
        List<InputBox> newInputs = getNewInputs(address,mempoolTransactions);
        long remainToCollect = amountToSpend;
        int offset = 0;
        while (true) {
            List<InputBox> chunk = getUnspentBoxesFor(address, offset, DEFAULT_LIMIT_FOR_API);
            boolean done = false;
            if (chunk.size() < DEFAULT_LIMIT_FOR_API) {
                chunk.addAll(newInputs);
                done = true;
            }
            for (InputBox b : chunk) {
                if(!isSpent(b,spentInputs)) {
                    boolean usefulTokens = tokensRemaining.foundNewTokens(b.getTokens());
                    if (usefulTokens || remainToCollect > 0) {
                        result.add(b);
                        remainToCollect -= b.getValue();
                    }
                    if (remainToCollect <= 0 && tokensRemaining.areTokensCovered())
                        return new CoveringBoxes(amountToSpend, result);
                }
            }
            // this chunk is not enough, go to the next (if any)
            if (done) {
                // this was the last chunk, but still remain to collect
                assert remainToCollect > 0 || !tokensRemaining.areTokensCovered();
                // cannot satisfy the request, but still return cb, with cb.isCovered == false
                return new CoveringBoxes(amountToSpend, result);
            }
            // step to next chunk
            offset += DEFAULT_LIMIT_FOR_API;
        }
    }

    public boolean isSpent(InputBox box, List<InputBox> spentBoxes)
    {
        for(InputBox b : spentBoxes)
        {
            if(b.getId().equals(box.getId())) {
                return true;
            }
        }
        return false;
    }

}
