package com.portal.auto;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.portal.auto.dao")
public class ElasticConfig {

	@Value("${elasticsearch.host}")
	private String EsHost;

	@Value("${elasticsearch.port}")
	private int EsPort;

	@SuppressWarnings("resource")
	@Bean
	public Client client() throws Exception {

		//Settings esSettings = Settings.settingsBuilder().put("cluster.name", EsClusterName).build();

		return  new PreBuiltTransportClient(Settings.EMPTY)
				
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}

	
}
