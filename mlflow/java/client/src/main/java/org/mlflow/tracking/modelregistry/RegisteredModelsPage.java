package org.mlflow.tracking.modelregistry;

import jdk.internal.joptsimple.internal.Strings;
import org.mlflow.api.proto.ModelRegistry;
import org.mlflow.tracking.EmptyPage;
import org.mlflow.tracking.MlflowClient;
import org.mlflow.tracking.Page;

import java.util.List;
import java.util.Optional;

public class RegisteredModelsPage implements Page<ModelRegistry.RegisteredModel> {

    private final String token;
    private final List<ModelRegistry.RegisteredModel> registeredModels;

    private final String searchFilter;
    private final List<String> orderBy;
    private final int maxResults;
    private final MlflowClient client;

    public RegisteredModelsPage(String token, List<ModelRegistry.RegisteredModel> registeredModels, String searchFilter, List<String> orderBy, int maxResults, MlflowClient client) {
        this.token = token;
        this.registeredModels = registeredModels;
        this.searchFilter = searchFilter;
        this.orderBy = orderBy;
        this.maxResults = maxResults;
        this.client = client;
    }


    @Override
    public int getPageSize() {
        return registeredModels.size();
    }

    @Override
    public boolean hasNextPage() {
         return Strings.isNullOrEmpty(token);
    }

    @Override
    public Optional<String> getNextPageToken() {
        if(hasNextPage()){
            return Optional.of(token);
        }
        return Optional.empty();
    }

    @Override
    public Page<ModelRegistry.RegisteredModel> getNextPage() {
        if (this.hasNextPage()) {
            return this.client.searchRegisteredModels(
                    this.searchFilter,
                    this.maxResults,
                    this.orderBy,
                    this.token);
        } else {
            return new EmptyPage();
        }
    }

    @Override
    public Iterable<ModelRegistry.RegisteredModel> getItems() {
        return registeredModels;
    }
}
