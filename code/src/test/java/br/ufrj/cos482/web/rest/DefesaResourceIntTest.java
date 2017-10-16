package br.ufrj.cos482.web.rest;

import br.ufrj.cos482.PesquisaApp;

import br.ufrj.cos482.domain.Defesa;
import br.ufrj.cos482.repository.DefesaRepository;
import br.ufrj.cos482.service.DefesaService;
import br.ufrj.cos482.service.dto.DefesaDTO;
import br.ufrj.cos482.service.mapper.DefesaMapper;
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

import br.ufrj.cos482.domain.enumeration.TipoDefesa;
/**
 * Test class for the DefesaResource REST controller.
 *
 * @see DefesaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PesquisaApp.class)
public class DefesaResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA_E_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_E_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    private static final String DEFAULT_ARQUIVO_TEXTO = "AAAAAAAAAA";
    private static final String UPDATED_ARQUIVO_TEXTO = "BBBBBBBBBB";

    private static final TipoDefesa DEFAULT_TIPO_DEFESA = TipoDefesa.QUALIFICACAO;
    private static final TipoDefesa UPDATED_TIPO_DEFESA = TipoDefesa.DEFESADETESE;

    @Autowired
    private DefesaRepository defesaRepository;

    @Autowired
    private DefesaMapper defesaMapper;

    @Autowired
    private DefesaService defesaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDefesaMockMvc;

    private Defesa defesa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DefesaResource defesaResource = new DefesaResource(defesaService);
        this.restDefesaMockMvc = MockMvcBuilders.standaloneSetup(defesaResource)
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
    public static Defesa createEntity(EntityManager em) {
        Defesa defesa = new Defesa()
            .dataEHora(DEFAULT_DATA_E_HORA)
            .local(DEFAULT_LOCAL)
            .arquivoTexto(DEFAULT_ARQUIVO_TEXTO)
            .tipoDefesa(DEFAULT_TIPO_DEFESA);
        return defesa;
    }

    @Before
    public void initTest() {
        defesa = createEntity(em);
    }

    @Test
    @Transactional
    public void createDefesa() throws Exception {
        int databaseSizeBeforeCreate = defesaRepository.findAll().size();

        // Create the Defesa
        DefesaDTO defesaDTO = defesaMapper.toDto(defesa);
        restDefesaMockMvc.perform(post("/api/defesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(defesaDTO)))
            .andExpect(status().isCreated());

        // Validate the Defesa in the database
        List<Defesa> defesaList = defesaRepository.findAll();
        assertThat(defesaList).hasSize(databaseSizeBeforeCreate + 1);
        Defesa testDefesa = defesaList.get(defesaList.size() - 1);
        assertThat(testDefesa.getDataEHora()).isEqualTo(DEFAULT_DATA_E_HORA);
        assertThat(testDefesa.getLocal()).isEqualTo(DEFAULT_LOCAL);
        assertThat(testDefesa.getArquivoTexto()).isEqualTo(DEFAULT_ARQUIVO_TEXTO);
        assertThat(testDefesa.getTipoDefesa()).isEqualTo(DEFAULT_TIPO_DEFESA);
    }

    @Test
    @Transactional
    public void createDefesaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = defesaRepository.findAll().size();

        // Create the Defesa with an existing ID
        defesa.setId(1L);
        DefesaDTO defesaDTO = defesaMapper.toDto(defesa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDefesaMockMvc.perform(post("/api/defesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(defesaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Defesa in the database
        List<Defesa> defesaList = defesaRepository.findAll();
        assertThat(defesaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDefesas() throws Exception {
        // Initialize the database
        defesaRepository.saveAndFlush(defesa);

        // Get all the defesaList
        restDefesaMockMvc.perform(get("/api/defesas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(defesa.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataEHora").value(hasItem(sameInstant(DEFAULT_DATA_E_HORA))))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.toString())))
            .andExpect(jsonPath("$.[*].arquivoTexto").value(hasItem(DEFAULT_ARQUIVO_TEXTO.toString())))
            .andExpect(jsonPath("$.[*].tipoDefesa").value(hasItem(DEFAULT_TIPO_DEFESA.toString())));
    }

    @Test
    @Transactional
    public void getDefesa() throws Exception {
        // Initialize the database
        defesaRepository.saveAndFlush(defesa);

        // Get the defesa
        restDefesaMockMvc.perform(get("/api/defesas/{id}", defesa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(defesa.getId().intValue()))
            .andExpect(jsonPath("$.dataEHora").value(sameInstant(DEFAULT_DATA_E_HORA)))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.toString()))
            .andExpect(jsonPath("$.arquivoTexto").value(DEFAULT_ARQUIVO_TEXTO.toString()))
            .andExpect(jsonPath("$.tipoDefesa").value(DEFAULT_TIPO_DEFESA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDefesa() throws Exception {
        // Get the defesa
        restDefesaMockMvc.perform(get("/api/defesas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDefesa() throws Exception {
        // Initialize the database
        defesaRepository.saveAndFlush(defesa);
        int databaseSizeBeforeUpdate = defesaRepository.findAll().size();

        // Update the defesa
        Defesa updatedDefesa = defesaRepository.findOne(defesa.getId());
        updatedDefesa
            .dataEHora(UPDATED_DATA_E_HORA)
            .local(UPDATED_LOCAL)
            .arquivoTexto(UPDATED_ARQUIVO_TEXTO)
            .tipoDefesa(UPDATED_TIPO_DEFESA);
        DefesaDTO defesaDTO = defesaMapper.toDto(updatedDefesa);

        restDefesaMockMvc.perform(put("/api/defesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(defesaDTO)))
            .andExpect(status().isOk());

        // Validate the Defesa in the database
        List<Defesa> defesaList = defesaRepository.findAll();
        assertThat(defesaList).hasSize(databaseSizeBeforeUpdate);
        Defesa testDefesa = defesaList.get(defesaList.size() - 1);
        assertThat(testDefesa.getDataEHora()).isEqualTo(UPDATED_DATA_E_HORA);
        assertThat(testDefesa.getLocal()).isEqualTo(UPDATED_LOCAL);
        assertThat(testDefesa.getArquivoTexto()).isEqualTo(UPDATED_ARQUIVO_TEXTO);
        assertThat(testDefesa.getTipoDefesa()).isEqualTo(UPDATED_TIPO_DEFESA);
    }

    @Test
    @Transactional
    public void updateNonExistingDefesa() throws Exception {
        int databaseSizeBeforeUpdate = defesaRepository.findAll().size();

        // Create the Defesa
        DefesaDTO defesaDTO = defesaMapper.toDto(defesa);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDefesaMockMvc.perform(put("/api/defesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(defesaDTO)))
            .andExpect(status().isCreated());

        // Validate the Defesa in the database
        List<Defesa> defesaList = defesaRepository.findAll();
        assertThat(defesaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDefesa() throws Exception {
        // Initialize the database
        defesaRepository.saveAndFlush(defesa);
        int databaseSizeBeforeDelete = defesaRepository.findAll().size();

        // Get the defesa
        restDefesaMockMvc.perform(delete("/api/defesas/{id}", defesa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Defesa> defesaList = defesaRepository.findAll();
        assertThat(defesaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Defesa.class);
        Defesa defesa1 = new Defesa();
        defesa1.setId(1L);
        Defesa defesa2 = new Defesa();
        defesa2.setId(defesa1.getId());
        assertThat(defesa1).isEqualTo(defesa2);
        defesa2.setId(2L);
        assertThat(defesa1).isNotEqualTo(defesa2);
        defesa1.setId(null);
        assertThat(defesa1).isNotEqualTo(defesa2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefesaDTO.class);
        DefesaDTO defesaDTO1 = new DefesaDTO();
        defesaDTO1.setId(1L);
        DefesaDTO defesaDTO2 = new DefesaDTO();
        assertThat(defesaDTO1).isNotEqualTo(defesaDTO2);
        defesaDTO2.setId(defesaDTO1.getId());
        assertThat(defesaDTO1).isEqualTo(defesaDTO2);
        defesaDTO2.setId(2L);
        assertThat(defesaDTO1).isNotEqualTo(defesaDTO2);
        defesaDTO1.setId(null);
        assertThat(defesaDTO1).isNotEqualTo(defesaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(defesaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(defesaMapper.fromId(null)).isNull();
    }
}
