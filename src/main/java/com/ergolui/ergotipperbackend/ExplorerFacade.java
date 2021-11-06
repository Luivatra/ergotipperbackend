package com.ergolui.ergotipperbackend;

import com.ergolui.ergotipperbackend.explorer.client.DefaultApi;
import com.ergolui.ergotipperbackend.explorer.client.model.ItemsA;
import com.ergolui.ergotipperbackend.explorer.client.model.TransactionInfo;
import org.ergoplatform.appkit.ErgoClientException;
import retrofit2.Retrofit;
import retrofit2.RetrofitUtil;

import java.lang.reflect.Method;
import java.util.List;

public class ExplorerFacade extends ApiFacade {
    /**
     * Get unspent boxes containing given address @GET("transactions/boxes/byAddress/unspent/{id}")
     *
     * @param id  address string (required)
     * @param offset  optional zero based offset of the first box in the list, default = 0
     * @param limit  optional number of boxes to retrive (default = 20)
     * @return list of requested outputs
     */
    static public List<org.ergoplatform.explorer.client.model.OutputInfo> transactionsBoxesByAddressUnspentIdGet(
            Retrofit r, String id, Integer offset, Integer limit) throws ErgoClientException {
        return execute(r, () -> {
            Method method = org.ergoplatform.explorer.client.DefaultApi.class.getMethod(
                    "getApiV1BoxesUnspentByaddressP1", String.class, Integer.class, Integer.class);
            org.ergoplatform.explorer.client.model.ItemsA res =
                    RetrofitUtil.<org.ergoplatform.explorer.client.model.ItemsA>invokeServiceMethod(r, method, new Object[]{id, offset, limit})
                            .execute().body();
            return res.getItems();
        });
    }

    /**
     * Get boxes containing given address @GET("mempool/transactions/byAddress/{id}")
     *
     * @param id  address string (required)
     * @param offset  optional zero based offset of the first box in the list, default = 0
     * @param limit  optional number of boxes to retrive (default = 20)
     * @return list of requested outputs
     */
    static public List<TransactionInfo> mempoolTransactionsByAddressIdGet(
            Retrofit r, String id, Integer offset, Integer limit) throws ErgoClientException {
        return execute(r, () -> {
            Method method = DefaultApi.class.getMethod(
                    "getApiV1MempoolTransactionsByaddressP1", String.class, Integer.class, Integer.class);
             ItemsA res =
                    RetrofitUtil.<ItemsA>invokeServiceMethod(r, method, new Object[]{id, offset, limit})
                            .execute().body();
            return res.getItems();
        });
    }

}
