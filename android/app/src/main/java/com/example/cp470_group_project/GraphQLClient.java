package com.example.cp470_group_project;

import android.app.Application;
import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;

public class GraphQLClient extends Application {
    public static ApolloClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        final String BASE_URL = "http://10.0.2.2:5433/graphql";

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        GraphQLClient.client = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    public ApolloClient getClient() {
        return GraphQLClient.client;
    }
}
