package br.ufrj.cos482.web.rest;

import br.ufrj.cos482.PesquisaApp;

import br.ufrj.cos482.domain.Publicacao;
import br.ufrj.cos482.repository.PublicacaoRepository;
import br.ufrj.cos482.service.PublicacaoService;
import br.ufrj.cos482.service.dto.PublicacaoDTO;
import br.ufrj.cos482.service.mapper.PublicacaoMapper;
import br.ufrj.cos482.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PublicacaoResource REST controller.
 *
 * @see PublicacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PesquisaApp.class)
public class PublicacaoResourceIntTest {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PERTENCE_AO_PROGRAMA = false;
    private static final Boolean UPDATED_PERTENCE_AO_PROGRAMA = true;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private PublicacaoMapper publicacaoMapper;

    @Autowired
    private PublicacaoService publicacaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPublicacaoMockMvc;

    private Publicacao publicacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PublicacaoResource publicacaoResource = new PublicacaoResource(publicacaoService);
        this.restPublicacaoMockMvc = MockMvcBuilders.standaloneSetup(publicacaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Publicacao createEntity(EntityManager em) {
        Publicacao publicacao = new Publicacao()
            .url(DEFAULT_URL)
            .pertenceAoPrograma(DEFAULT_PERTENCE_AO_PROGRAMA);
        return publicacao;
    }

    @Before
    public void initTest() {
        publicacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createPublicacao() throws Exception {
        int databaseSizeBeforeCreate = publicacaoRepository.findAll().size();

        // Create the Publicacao
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);
        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Publicacao testPublicacao = publicacaoList.get(publicacaoList.size() - 1);
        assertThat(testPublicacao.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testPublicacao.isPertenceAoPrograma()).isEqualTo(DEFAULT_PERTENCE_AO_PROGRAMA);
    }

    @Test
    @Transactional
    public void createPublicacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = publicacaoRepository.findAll().size();

        // Create the Publicacao with an existing ID
        publicacao.setId(1L);
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoRepository.findAll().size();
        // set the field null
        publicacao.setUrl(null);

        // Create the Publicacao, which fails.
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        restPublicacaoMockMvc.perform(post("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPublicacaos() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get all the publicacaoList
        restPublicacaoMockMvc.perform(get("/api/publicacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].pertenceAoPrograma").value(hasItem(DEFAULT_PERTENCE_AO_PROGRAMA.booleanValue())));
    }

    @Test
    @Transactional
    public void getPublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);

        // Get the publicacao
        restPublicacaoMockMvc.perform(get("/api/publicacaos/{id}", publicacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(publicacao.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.pertenceAoPrograma").value(DEFAULT_PERTENCE_AO_PROGRAMA.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPublicacao() throws Exception {
        // Get the publicacao
        restPublicacaoMockMvc.perform(get("/api/publicacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);
        int databaseSizeBeforeUpdate = publicacaoRepository.findAll().size();

        // Update the publicacao
        Publicacao updatedPublicacao = publicacaoRepository.findOne(publicacao.getId());
        updatedPublicacao
            .url(UPDATED_URL)
            .pertenceAoPrograma(UPDATED_PERTENCE_AO_PROGRAMA);
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(updatedPublicacao);

        restPublicacaoMockMvc.perform(put("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeUpdate);
        Publicacao testPublicacao = publicacaoList.get(publicacaoList.size() - 1);
        assertThat(testPublicacao.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testPublicacao.isPertenceAoPrograma()).isEqualTo(UPDATED_PERTENCE_AO_PROGRAMA);
    }

    @Test
    @Transactional
    public void updateNonExistingPublicacao() throws Exception {
        int databaseSizeBeforeUpdate = publicacaoRepository.findAll().size();

        // Create the Publicacao
        PublicacaoDTO publicacaoDTO = publicacaoMapper.toDto(publicacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPublicacaoMockMvc.perform(put("/api/publicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Publicacao in the database
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePublicacao() throws Exception {
        // Initialize the database
        publicacaoRepository.saveAndFlush(publicacao);
        int databaseSizeBeforeDelete = publicacaoRepository.findAll().size();

        // Get the publicacao
        restPublicacaoMockMvc.perform(delete("/api/publicacaos/{id}", publicacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Publicacao> publicacaoList = publicacaoRepository.findAll();
        assertThat(publicacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Publicacao.class);
        Publicacao publicacao1 = new Publicacao();
        publicacao1.setId(1L);
        Publicacao publicacao2 = new Publicacao();
        publicacao2.setId(publicacao1.getId());
        assertThat(publicacao1).isEqualTo(publicacao2);
        publicacao2.setId(2L);
        assertThat(publicacao1).isNotEqualTo(publicacao2);
        publicacao1.setId(null);
        assertThat(publicacao1).isNotEqualTo(publicacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PublicacaoDTO.class);
        PublicacaoDTO publicacaoDTO1 = new PublicacaoDTO();
        publicacaoDTO1.setId(1L);
        PublicacaoDTO publicacaoDTO2 = new PublicacaoDTO();
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
        publicacaoDTO2.setId(publicacaoDTO1.getId());
        assertThat(publicacaoDTO1).isEqualTo(publicacaoDTO2);
        publicacaoDTO2.setId(2L);
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
        publicacaoDTO1.setId(null);
        assertThat(publicacaoDTO1).isNotEqualTo(publicacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(publicacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(publicacaoMapper.fromId(null)).isNull();
    }
}
