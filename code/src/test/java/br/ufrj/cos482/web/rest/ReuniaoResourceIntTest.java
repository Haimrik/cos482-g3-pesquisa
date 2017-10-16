package br.ufrj.cos482.web.rest;

import br.ufrj.cos482.PesquisaApp;

import br.ufrj.cos482.domain.Reuniao;
import br.ufrj.cos482.repository.ReuniaoRepository;
import br.ufrj.cos482.service.ReuniaoService;
import br.ufrj.cos482.service.dto.ReuniaoDTO;
import br.ufrj.cos482.service.mapper.ReuniaoMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.ufrj.cos482.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReuniaoResource REST controller.
 *
 * @see ReuniaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PesquisaApp.class)
public class ReuniaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA_E_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_E_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private ReuniaoMapper reuniaoMapper;

    @Autowired
    private ReuniaoService reuniaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReuniaoMockMvc;

    private Reuniao reuniao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReuniaoResource reuniaoResource = new ReuniaoResource(reuniaoService);
        this.restReuniaoMockMvc = MockMvcBuilders.standaloneSetup(reuniaoResource)
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
    public static Reuniao createEntity(EntityManager em) {
        Reuniao reuniao = new Reuniao()
            .dataEHora(DEFAULT_DATA_E_HORA)
            .local(DEFAULT_LOCAL);
        return reuniao;
    }

    @Before
    public void initTest() {
        reuniao = createEntity(em);
    }

    @Test
    @Transactional
    public void createReuniao() throws Exception {
        int databaseSizeBeforeCreate = reuniaoRepository.findAll().size();

        // Create the Reuniao
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(reuniao);
        restReuniaoMockMvc.perform(post("/api/reuniaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reuniaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Reuniao in the database
        List<Reuniao> reuniaoList = reuniaoRepository.findAll();
        assertThat(reuniaoList).hasSize(databaseSizeBeforeCreate + 1);
        Reuniao testReuniao = reuniaoList.get(reuniaoList.size() - 1);
        assertThat(testReuniao.getDataEHora()).isEqualTo(DEFAULT_DATA_E_HORA);
        assertThat(testReuniao.getLocal()).isEqualTo(DEFAULT_LOCAL);
    }

    @Test
    @Transactional
    public void createReuniaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reuniaoRepository.findAll().size();

        // Create the Reuniao with an existing ID
        reuniao.setId(1L);
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(reuniao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReuniaoMockMvc.perform(post("/api/reuniaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reuniaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reuniao in the database
        List<Reuniao> reuniaoList = reuniaoRepository.findAll();
        assertThat(reuniaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReuniaos() throws Exception {
        // Initialize the database
        reuniaoRepository.saveAndFlush(reuniao);

        // Get all the reuniaoList
        restReuniaoMockMvc.perform(get("/api/reuniaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reuniao.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataEHora").value(hasItem(sameInstant(DEFAULT_DATA_E_HORA))))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())));
    }

    @Test
    @Transactional
    public void getReuniao() throws Exception {
        // Initialize the database
        reuniaoRepository.saveAndFlush(reuniao);

        // Get the reuniao
        restReuniaoMockMvc.perform(get("/api/reuniaos/{id}", reuniao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reuniao.getId().intValue()))
            .andExpect(jsonPath("$.dataEHora").value(sameInstant(DEFAULT_DATA_E_HORA)))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReuniao() throws Exception {
        // Get the reuniao
        restReuniaoMockMvc.perform(get("/api/reuniaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReuniao() throws Exception {
        // Initialize the database
        reuniaoRepository.saveAndFlush(reuniao);
        int databaseSizeBeforeUpdate = reuniaoRepository.findAll().size();

        // Update the reuniao
        Reuniao updatedReuniao = reuniaoRepository.findOne(reuniao.getId());
        updatedReuniao
            .dataEHora(UPDATED_DATA_E_HORA)
            .local(UPDATED_LOCAL);
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(updatedReuniao);

        restReuniaoMockMvc.perform(put("/api/reuniaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reuniaoDTO)))
            .andExpect(status().isOk());

        // Validate the Reuniao in the database
        List<Reuniao> reuniaoList = reuniaoRepository.findAll();
        assertThat(reuniaoList).hasSize(databaseSizeBeforeUpdate);
        Reuniao testReuniao = reuniaoList.get(reuniaoList.size() - 1);
        assertThat(testReuniao.getDataEHora()).isEqualTo(UPDATED_DATA_E_HORA);
        assertThat(testReuniao.getLocal()).isEqualTo(UPDATED_LOCAL);
    }

    @Test
    @Transactional
    public void updateNonExistingReuniao() throws Exception {
        int databaseSizeBeforeUpdate = reuniaoRepository.findAll().size();

        // Create the Reuniao
        ReuniaoDTO reuniaoDTO = reuniaoMapper.toDto(reuniao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReuniaoMockMvc.perform(put("/api/reuniaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reuniaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Reuniao in the database
        List<Reuniao> reuniaoList = reuniaoRepository.findAll();
        assertThat(reuniaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReuniao() throws Exception {
        // Initialize the database
        reuniaoRepository.saveAndFlush(reuniao);
        int databaseSizeBeforeDelete = reuniaoRepository.findAll().size();

        // Get the reuniao
        restReuniaoMockMvc.perform(delete("/api/reuniaos/{id}", reuniao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Reuniao> reuniaoList = reuniaoRepository.findAll();
        assertThat(reuniaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reuniao.class);
        Reuniao reuniao1 = new Reuniao();
        reuniao1.setId(1L);
        Reuniao reuniao2 = new Reuniao();
        reuniao2.setId(reuniao1.getId());
        assertThat(reuniao1).isEqualTo(reuniao2);
        reuniao2.setId(2L);
        assertThat(reuniao1).isNotEqualTo(reuniao2);
        reuniao1.setId(null);
        assertThat(reuniao1).isNotEqualTo(reuniao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReuniaoDTO.class);
        ReuniaoDTO reuniaoDTO1 = new ReuniaoDTO();
        reuniaoDTO1.setId(1L);
        ReuniaoDTO reuniaoDTO2 = new ReuniaoDTO();
        assertThat(reuniaoDTO1).isNotEqualTo(reuniaoDTO2);
        reuniaoDTO2.setId(reuniaoDTO1.getId());
        assertThat(reuniaoDTO1).isEqualTo(reuniaoDTO2);
        reuniaoDTO2.setId(2L);
        assertThat(reuniaoDTO1).isNotEqualTo(reuniaoDTO2);
        reuniaoDTO1.setId(null);
        assertThat(reuniaoDTO1).isNotEqualTo(reuniaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(reuniaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(reuniaoMapper.fromId(null)).isNull();
    }
}
