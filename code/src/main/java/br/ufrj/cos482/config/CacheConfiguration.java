package br.ufrj.cos482.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".publicacaos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".defesas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".reuniaos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".participacaoBancas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".reuniaos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".coorientadors", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Publicacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Publicacao.class.getName() + ".coautors", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Seminario.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.ParticipacaoBanca.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Defesa.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Defesa.class.getName() + ".participacaoBancas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Reuniao.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".seminarios", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".coorientadors", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".seminarios", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".coorientandos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Publicacao.class.getName() + ".coautorAlunos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Publicacao.class.getName() + ".coautorProfessors", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Aluno.class.getName() + ".copublicacaos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos482.domain.Professor.class.getName() + ".copublicacaos", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
