package com.ergolui.ergotipperbackend;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.ergoplatform.appkit.BlockchainContext;
import org.ergoplatform.appkit.ErgoClient;
import org.ergoplatform.appkit.NetworkType;
import org.ergoplatform.appkit.config.ErgoNodeConfig;
import org.ergoplatform.restapi.client.ApiClient;

import java.util.function.Function;

public class RestApiErgoClientWithMemory implements ErgoClient {
    private final ApiClient _client;
    private final ExplorerApiClient _explorer;
    private final NetworkType _networkType;

    public final static String defaultMainnetExplorerUrl = "https://api.ergoplatform.com";
    public final static String defaultTestnetExplorerUrl = "https://api-testnet.ergoplatform.com";

    RestApiErgoClientWithMemory(String nodeUrl, NetworkType networkType, String apiKey, String explorerUrl) {
        _networkType = networkType;
        _client = new ApiClient(nodeUrl, "ApiKeyAuth", apiKey);
        if (!Strings.isNullOrEmpty(explorerUrl)) {
            _explorer = new ExplorerApiClient(explorerUrl);
        } else {
            _explorer = null;
        }
    }

    @Override
    public <T> T execute(Function<BlockchainContext, T> action) {
        BlockchainContext ctx = new BlockchainContextBuilderWithMemory(_client, _explorer, _networkType).build();
        T res = action.apply(ctx);
        return res;
    }

    public static String getDefaultExplorerUrl(NetworkType networkType) {
        switch (networkType) {
            case MAINNET:
                return defaultMainnetExplorerUrl;
            default:
                return defaultTestnetExplorerUrl;
        }
    }

    /**
     * Creates a new {@link ErgoClient} instance in the `node-only` mode, i.e. connected
     * to a given node of the given network type and not connected to explorer.
     *
     * @param nodeUrl     http url to Ergo node REST API endpoint of the form `https://host:port/`
     * @param networkType type of network (mainnet, testnet) the Ergo node is part of
     * @param apiKey      api key to authenticate this client
     * @return a new instance of {@link ErgoClient} connected to a given node
     */
    public static ErgoClient createWithoutExplorer(String nodeUrl, NetworkType networkType, String apiKey) {
        return new RestApiErgoClientWithMemory(nodeUrl, networkType, apiKey, null);
    }

    /**
     * Creates a new {@link ErgoClient} instance connected to a given node of the given
     *  network type.
     *
     * @param nodeUrl     http url to Ergo node REST API endpoint of the form
     * `https://host:port/`
     * @param networkType type of network (mainnet, testnet) the Ergo node is part of
     * @param apiKey      api key to authenticate this client
     * @param explorerUrl optional http url to Explorer REST API endpoint of the form
     *                    `https://host:port/`. If null or empty, then explorer connection
     *                    is not initialized so that the resulting {@link ErgoClient} can
     *                    work in `node-only` mode.
     * @return a new instance of {@link ErgoClient} connected to a given node
     */
    public static ErgoClient create(String nodeUrl, NetworkType networkType, String apiKey, String explorerUrl) {
        return new RestApiErgoClientWithMemory(nodeUrl, networkType, apiKey, explorerUrl);
    }

    /**
     * Create a new {@link ErgoClient} instance using node configuration parameters and
     * optional explorerUrl.
     *
     * @param nodeConf    parameters of Ergo node used by ErgoClient.
     * @param explorerUrl optional http url to Explorer REST API endpoint of the form
     *                    `https://host:port/`. If null or empty, then explorer connection
     *                    is not initialized so that the resulting {@link ErgoClient} can
     *                    work in `node-only` mode.
     */
    public static ErgoClient create(ErgoNodeConfig nodeConf, String explorerUrl) {
        return RestApiErgoClientWithMemory.create(
                nodeConf.getNodeApi().getApiUrl(),
                nodeConf.getNetworkType(),
                nodeConf.getNodeApi().getApiKey(),
                explorerUrl);
    }

    /**
     * Get underlying Ergo node REST API typed client.
     */
    ApiClient getNodeApiClient() {
        return _client;
    }

    /**
     * Get underlying Ergo Network Explorer REST API typed client.
     */
    ExplorerApiClient getExplorerApiClient() {
        Preconditions.checkNotNull(_explorer, ErgoClient.explorerUrlNotSpecifiedMessage);
        return _explorer;
    }


}
