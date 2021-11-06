package com.ergolui.ergotipperbackend.explorer.client;

import retrofit2.Call;
import retrofit2.http.*;

import com.ergolui.ergotipperbackend.explorer.client.model.Balance;
import com.ergolui.ergotipperbackend.explorer.client.model.BlockSummary;
import com.ergolui.ergotipperbackend.explorer.client.model.BoxAssetsQuery;
import com.ergolui.ergotipperbackend.explorer.client.model.BoxQuery;
import com.ergolui.ergotipperbackend.explorer.client.model.EpochParameters;
import com.ergolui.ergotipperbackend.explorer.client.model.ErgoLikeTransaction;
import com.ergolui.ergotipperbackend.explorer.client.model.ItemsA;
import com.ergolui.ergotipperbackend.explorer.client.model.ListOutputInfo;
import com.ergolui.ergotipperbackend.explorer.client.model.NetworkState;
import com.ergolui.ergotipperbackend.explorer.client.model.NetworkStats;
import com.ergolui.ergotipperbackend.explorer.client.model.OutputInfo;
import com.ergolui.ergotipperbackend.explorer.client.model.TokenInfo;
import com.ergolui.ergotipperbackend.explorer.client.model.TotalBalance;
import com.ergolui.ergotipperbackend.explorer.client.model.TransactionInfo;
import com.ergolui.ergotipperbackend.explorer.client.model.TxIdResponse;

public interface DefaultApi {
  /**
   * 
   * 
   * @param p1  (required)
   * @param minConfirmations  (optional)
   * @return Call&lt;ETBBalance&gt;
   */
  @GET("api/v1/addresses/{p1}/balance/confirmed")
  Call<Balance> getApiV1AddressesP1BalanceConfirmed(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("minConfirmations") Integer minConfirmations                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @return Call&lt;TotalBalance&gt;
   */
  @GET("api/v1/addresses/{p1}/balance/total")
  Call<TotalBalance> getApiV1AddressesP1BalanceTotal(
            @retrofit2.http.Path("p1") String p1            
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/addresses/{p1}/transactions")
  Call<ItemsA> getApiV1AddressesP1Transactions(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * Use &#x27;/tokens&#x27; instead
   * @param offset  (optional)
   * @param limit  (optional)
   * @param sortDirection  (optional)
   * @param hideNfts Exclude NFTs from result set (optional, default to false)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/assets")
  Call<ItemsA> getApiV1Assets(
        @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                ,     @retrofit2.http.Query("sortDirection") String sortDirection                ,     @retrofit2.http.Query("hideNfts") Boolean hideNfts                
  );

  /**
   * 
   * 
   * @param query  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/assets/search/byTokenId")
  Call<ItemsA> getApiV1AssetsSearchBytokenid(
        @retrofit2.http.Query("query") String query                ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param offset  (optional)
   * @param limit  (optional)
   * @param sortBy  (optional)
   * @param sortDirection  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/blocks")
  Call<ItemsA> getApiV1Blocks(
        @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                ,     @retrofit2.http.Query("sortBy") String sortBy                ,     @retrofit2.http.Query("sortDirection") String sortDirection                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @return Call&lt;BlockSummary&gt;
   */
  @GET("api/v1/blocks/{p1}")
  Call<BlockSummary> getApiV1BlocksP1(
            @retrofit2.http.Path("p1") String p1            
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/byAddress/{p1}")
  Call<ItemsA> getApiV1BoxesByaddressP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/byErgoTree/{p1}")
  Call<ItemsA> getApiV1BoxesByergotreeP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/byErgoTreeTemplateHash/{p1}")
  Call<ItemsA> getApiV1BoxesByergotreetemplatehashP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * Get a stream of unspent outputs by a hash of the given ErgoTreeTemplate
   * @param p1  (required)
   * @param minHeight  (required)
   * @param maxHeight  (required)
   * @return Call&lt;ListOutputInfo&gt;
   */
  @GET("api/v1/boxes/byErgoTreeTemplateHash/{p1}/stream")
  Call<ListOutputInfo> getApiV1BoxesByergotreetemplatehashP1Stream(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("minHeight") Integer minHeight                ,     @retrofit2.http.Query("maxHeight") Integer maxHeight                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/byTokenId/{p1}")
  Call<ItemsA> getApiV1BoxesBytokenidP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @return Call&lt;OutputInfo&gt;
   */
  @GET("api/v1/boxes/{p1}")
  Call<OutputInfo> getApiV1BoxesP1(
            @retrofit2.http.Path("p1") String p1            
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/unspent/byAddress/{p1}")
  Call<ItemsA> getApiV1BoxesUnspentByaddressP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/unspent/byErgoTree/{p1}")
  Call<ItemsA> getApiV1BoxesUnspentByergotreeP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/unspent/byErgoTreeTemplateHash/{p1}")
  Call<ItemsA> getApiV1BoxesUnspentByergotreetemplatehashP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param minHeight  (required)
   * @param maxHeight  (required)
   * @return Call&lt;ListOutputInfo&gt;
   */
  @GET("api/v1/boxes/unspent/byErgoTreeTemplateHash/{p1}/stream")
  Call<ListOutputInfo> getApiV1BoxesUnspentByergotreetemplatehashP1Stream(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("minHeight") Integer minHeight                ,     @retrofit2.http.Query("maxHeight") Integer maxHeight                
  );

  /**
   * 
   * Get a stream of unspent outputs ordered by global index
   * @param minGix Min global index (in blockchain) of a box (required)
   * @param limit  (required)
   * @return Call&lt;ListOutputInfo&gt;
   */
  @GET("api/v1/boxes/unspent/byGlobalIndex/stream")
  Call<ListOutputInfo> getApiV1BoxesUnspentByglobalindexStream(
        @retrofit2.http.Query("minGix") Long minGix                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param lastEpochs  (required)
   * @return Call&lt;ListOutputInfo&gt;
   */
  @GET("api/v1/boxes/unspent/byLastEpochs/stream")
  Call<ListOutputInfo> getApiV1BoxesUnspentBylastepochsStream(
        @retrofit2.http.Query("lastEpochs") Integer lastEpochs                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @param sortDirection  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/boxes/unspent/byTokenId/{p1}")
  Call<ItemsA> getApiV1BoxesUnspentBytokenidP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                ,     @retrofit2.http.Query("sortDirection") String sortDirection                
  );

  /**
   * 
   * 
   * @param minHeight  (required)
   * @param maxHeight  (required)
   * @return Call&lt;ListOutputInfo&gt;
   */
  @GET("api/v1/boxes/unspent/stream")
  Call<ListOutputInfo> getApiV1BoxesUnspentStream(
        @retrofit2.http.Query("minHeight") Integer minHeight                ,     @retrofit2.http.Query("maxHeight") Integer maxHeight                
  );

  /**
   * 
   * 
   * @return Call&lt;EpochParameters&gt;
   */
  @GET("api/v1/epochs/params")
  Call<EpochParameters> getApiV1EpochsParams();
    

  /**
   * 
   * 
   * @return Call&lt;NetworkState&gt;
   */
  @GET("api/v1/info")
  Call<NetworkState> getApiV1Info();
    

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/mempool/transactions/byAddress/{p1}")
  Call<ItemsA> getApiV1MempoolTransactionsByaddressP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @return Call&lt;NetworkState&gt;
   */
  @GET("api/v1/networkState")
  Call<NetworkState> getApiV1Networkstate();
    

  /**
   * 
   * 
   * @return Call&lt;NetworkStats&gt;
   */
  @GET("api/v1/networkStats")
  Call<NetworkStats> getApiV1Networkstats();
    

  /**
   * 
   * 
   * @param offset  (optional)
   * @param limit  (optional)
   * @param sortDirection  (optional)
   * @param hideNfts Exclude NFTs from result set (optional, default to false)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/tokens")
  Call<ItemsA> getApiV1Tokens(
        @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                ,     @retrofit2.http.Query("sortDirection") String sortDirection                ,     @retrofit2.http.Query("hideNfts") Boolean hideNfts                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @return Call&lt;TokenInfo&gt;
   */
  @GET("api/v1/tokens/{p1}")
  Call<TokenInfo> getApiV1TokensP1(
            @retrofit2.http.Path("p1") String p1            
  );

  /**
   * 
   * 
   * @param query  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/tokens/search")
  Call<ItemsA> getApiV1TokensSearch(
        @retrofit2.http.Query("query") String query                ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @param sortDirection  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @GET("api/v1/transactions/byInputsScriptTemplateHash/{p1}")
  Call<ItemsA> getApiV1TransactionsByinputsscripttemplatehashP1(
            @retrofit2.http.Path("p1") String p1            ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                ,     @retrofit2.http.Query("sortDirection") String sortDirection                
  );

  /**
   * 
   * 
   * @param p1  (required)
   * @return Call&lt;TransactionInfo&gt;
   */
  @GET("api/v1/transactions/{p1}")
  Call<TransactionInfo> getApiV1TransactionsP1(
            @retrofit2.http.Path("p1") String p1            
  );

  /**
   * 
   * Detailed search among all boxes in the chain
   * @param body  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v1/boxes/search")
  Call<ItemsA> postApiV1BoxesSearch(
                    @retrofit2.http.Body BoxQuery body    ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * Detailed search among UTXO set
   * @param body  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v1/boxes/unspent/search")
  Call<ItemsA> postApiV1BoxesUnspentSearch(
                    @retrofit2.http.Body BoxQuery body    ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * Search among UTXO set by ergoTreeTemplateHash and tokens. The resulted UTXOs will contain at lest one of the given tokens.
   * @param body  (required)
   * @param offset  (optional)
   * @param limit  (optional)
   * @return Call&lt;ItemsA&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v1/boxes/unspent/search/union")
  Call<ItemsA> postApiV1BoxesUnspentSearchUnion(
                    @retrofit2.http.Body BoxAssetsQuery body    ,     @retrofit2.http.Query("offset") Integer offset                ,     @retrofit2.http.Query("limit") Integer limit                
  );

  /**
   * 
   * 
   * @param body  (required)
   * @return Call&lt;TxIdResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v1/mempool/transactions/submit")
  Call<TxIdResponse> postApiV1MempoolTransactionsSubmit(
                    @retrofit2.http.Body ErgoLikeTransaction body    
  );

}
