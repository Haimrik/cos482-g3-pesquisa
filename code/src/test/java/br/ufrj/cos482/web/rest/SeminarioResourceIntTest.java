package br.ufrj.cos482.web.rest;

import br.ufrj.cos482.PesquisaApp;

import br.ufrj.cos482.domain.Seminario;
import br.ufrj.cos482.repository.SeminarioRepository;
import br.ufrj.cos482.service.SeminarioService;
import br.ufrj.cos482.service.dto.SeminarioDTO;
import br.ufrj.cos482.service.mapper.SeminarioMapper;
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
 * Test class for the SeminarioResource REST controller.
 *
 * @see SeminarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PesquisaApp.class)
public class SeminarioResourceIntTest {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA_E_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_E_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    @Autowired
    private SeminarioRepository seminarioRepository;

    @Autowired
    private SeminarioMapper seminarioMapper;

    @Autowired
    private SeminarioService seminarioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSeminarioMockMvc;

    private Seminario seminario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SeminarioResource seminarioResource = new SeminarioResource(seminarioService);
        this.restSeminarioMockMvc = MockMvcBuilders.standaloneSetup(seminarioResource)
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
    public static Seminario createEntity(EntityManager em) {
        Seminario seminario = new Seminario()
            .titulo(DEFAULT_TITULO)
            .dataEHora(DEFAULT_DATA_E_HORA)
            .local(DEFAULT_LOCAL);
        return seminario;
    }

    @Before
    public void initTest() {
        seminario = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeminario() throws Exception {
        int databaseSizeBeforeCreate = seminarioRepository.findAll().size();

        // Create the Seminario
        SeminarioDTO seminarioDTO = seminarioMapper.toDto(seminario);
        restSeminarioMockMvc.perform(post("/api/seminarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seminarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Seminario in the database
        List<Seminario> seminarioList = seminarioRepository.findAll();
        assertThat(seminarioList).hasSize(databaseSizeBeforeCreate + 1);
        Seminario testSeminario = seminarioList.get(seminarioList.size() - 1);
        assertThat(testSeminario.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testSeminario.getDataEHora()).isEqualTo(DEFAULT_DATA_E_HORA);
        assertThat(testSeminario.getLocal()).isEqualTo(DEFAULT_LOCAL);
    }

    @Test
    @Transactional
    public void createSeminarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = seminarioRepository.findAll().size();

        // Create the Seminario with an existing ID
        seminario.setId(1L);
        SeminarioDTO seminarioDTO = seminarioMapper.toDto(seminario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeminarioMockMvc.perform(post("/api/seminarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seminarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Seminario in the database
        List<Seminario> seminarioList = seminarioRepository.findAll();
        assertThat(seminarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSeminarios() throws Exception {
        // Initialize the database
        seminarioRepository.saveAndFlush(seminario);

        // Get all the seminarioList
        restSeminarioMockMvc.perform(get("/api/seminarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seminario.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].dataEHora").value(hasItem(sameInstant(DEFAULT_DATA_E_HORA))))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())));
    }

    @Test
    @Transactional
    public void getSeminario() throws Exception {
        // Initialize the database
        seminarioRepository.saveAndFlush(seminario);

        // Get the seminario
        restSeminarioMockMvc.perform(get("/api/seminarios/{id}", seminario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(seminario.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.dataEHora").value(sameInstant(DEFAULT_DATA_E_HORA)))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSeminario() throws Exception {
        // Get the seminario
        restSeminarioMockMvc.perform(get("/api/seminarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeminario() throws Exception {
        // Initialize the database
        seminarioRepository.saveAndFlush(seminario);
        int databaseSizeBeforeUpdate = seminarioRepository.findAll().size();

        // Update the seminario
        Seminario updatedSeminario = seminarioRepository.findOne(seminario.getId());
        updatedSeminario
            .titulo(UPDATED_TITULO)
            .dataEHora(UPDATED_DATA_E_HORA)
            .local(UPDATED_LOCAL);
        SeminarioDTO seminarioDTO = seminarioMapper.toDto(updatedSeminario);

        restSeminarioMockMvc.perform(put("/api/seminarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seminarioDTO)))
            .andExpect(status().isOk());

        // Validate the Seminario in the database
        List<Seminario> seminarioList = seminarioRepository.findAll();
        assertThat(seminarioList).hasSize(databaseSizeBeforeUpdate);
        Seminario testSeminario = seminarioList.get(seminarioList.size() - 1);
        assertThat(testSeminario.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testSeminario.getDataEHora()).isEqualTo(UPDATED_DATA_E_HORA);
        assertThat(testSeminario.getLocal()).isEqualTo(UPDATED_LOCAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSeminario() throws Exception {
        int databaseSizeBeforeUpdate = seminarioRepository.findAll().size();

        // Create the Seminario
        SeminarioDTO seminarioDTO = seminarioMapper.toDto(seminario);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSeminarioMockMvc.perform(put("/api/seminarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seminarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Seminario in the database
        List<Seminario> seminarioList = seminarioRepository.findAll();
        assertThat(seminarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSeminario() throws Exception {
        // Initialize the database
        seminarioRepository.saveAndFlush(seminario);
        int databaseSizeBeforeDelete = seminarioRepository.findAll().size();

        // Get the seminario
        restSeminarioMockMvc.perform(delete("/api/seminarios/{id}", seminario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Seminario> seminarioList = seminarioRepository.findAll();
        assertThat(seminarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Seminario.class);
        Seminario seminario1 = new Seminario();
        seminario1.setId(1L);
        Seminario seminario2 = new Seminario();
        seminario2.setId(seminario1.getId());
        assertThat(seminario1).isEqualTo(seminario2);
        seminario2.setId(2L);
        assertThat(seminario1).isNotEqualTo(seminario2);
        seminario1.setId(null);
        assertThat(seminario1).isNotEqualTo(seminario2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeminarioDTO.class);
        SeminarioDTO seminarioDTO1 = new SeminarioDTO();
        seminarioDTO1.setId(1L);
        SeminarioDTO seminarioDTO2 = new SeminarioDTO();
        assertThat(seminarioDTO1).isNotEqualTo(seminarioDTO2);
        seminarioDTO2.setId(seminarioDTO1.getId());
        assertThat(seminarioDTO1).isEqualTo(seminarioDTO2);
        seminarioDTO2.setId(2L);
        assertThat(seminarioDTO1).isNotEqualTo(seminarioDTO2);
        seminarioDTO1.setId(null);
        assertThat(seminarioDTO1).isNotEqualTo(seminarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(seminarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(seminarioMapper.fromId(null)).isNull();
    }
}
