package org.mlflow.tracking.modelregistry;

import com.google.common.base.Strings;
import org.mlflow.api.proto.ModelRegistry;
import org.mlflow.tracking.EmptyPage;
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

    public ModelVersionsPage(String token,
                             List<ModelRegistry.ModelVersion> modelVersions,
                             String searchFilter,
                             List<String> orderBy,
                             int maxResults,
                             MlflowClient client) {
        this.token = token;
        this.modelVersions = modelVersions;
        this.searchFilter = searchFilter;
        this.orderBy = orderBy;
        this.maxResults = maxResults;
        this.client = client;
    }


    @Override
    public int getPageSize() {
        return modelVersions.size();
    }

    @Override
    public boolean hasNextPage() {
        return Strings.isNullOrEmpty(token);
    }

    @Override
    public Optional<String> getNextPageToken() {
        if (hasNextPage()){
            return Optional.of(token);
        }
        return Optional.empty();
    }

    @Override
    public Page<ModelRegistry.ModelVersion> getNextPage() {
        if (this.hasNextPage()) {
            return this.client.searchModelVersions(
                    this.searchFilter,
                    this.maxResults,
                    this.orderBy,
                    this.token);
        } else {
            return new EmptyPage();
        }
    }

    @Override
    public Iterable<ModelRegistry.ModelVersion> getItems() {
        return modelVersions;
    }
}
