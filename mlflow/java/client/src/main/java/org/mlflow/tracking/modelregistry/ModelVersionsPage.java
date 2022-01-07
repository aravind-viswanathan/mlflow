package org.mlflow.tracking.modelregistry;

import org.mlflow.api.proto.ModelRegistry;
import org.mlflow.tracking.MlflowClient;
import org.mlflow.tracking.Page;

import java.util.List;
import java.util.Optional;

public class ModelVersionsPage implements Page<ModelRegistry.ModelVersion> {

    private final String token;
    private final List<ModelRegistry.ModelVersion> modelVersions;

    private final String searchFilter;
    private final List<String> orderBy;
    private final int maxResults;
    private final MlflowClient client;

    public ModelVersionsPage(String token, List<ModelRegistry.ModelVersion> modelVersions, String searchFilter, List<String> orderBy, int maxResults, MlflowClient client) {
        this.token = token;
        this.modelVersions = modelVersions;
        this.searchFilter = searchFilter;
        this.orderBy = orderBy;
        this.maxResults = maxResults;
        this.client = client;
    }


    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public boolean hasNextPage() {
        return false;
    }

    @Override
    public Optional<String> getNextPageToken() {
        return Optional.empty();
    }

    @Override
    public Page<ModelRegistry.ModelVersion> getNextPage() {
        return null;
    }

    @Override
    public Iterable<ModelRegistry.ModelVersion> getItems() {
        return null;
    }
}
