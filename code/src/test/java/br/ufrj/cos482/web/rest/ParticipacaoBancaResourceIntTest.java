package br.ufrj.cos482.web.rest;

import br.ufrj.cos482.PesquisaApp;

import br.ufrj.cos482.domain.ParticipacaoBanca;
import br.ufrj.cos482.repository.ParticipacaoBancaRepository;
import br.ufrj.cos482.service.ParticipacaoBancaService;
import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;
import br.ufrj.cos482.service.mapper.ParticipacaoBancaMapper;
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

import br.ufrj.cos482.domain.enumeration.EstadoAprovacaoDefesa;
/**
 * Test class for the ParticipacaoBancaResource REST controller.
 *
 * @see ParticipacaoBancaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PesquisaApp.class)
public class ParticipacaoBancaResourceIntTest {

    private static final Boolean DEFAULT_CONFIRMADO = false;
    private static final Boolean UPDATED_CONFIRMADO = true;

    private static final EstadoAprovacaoDefesa DEFAULT_ESTADO_APROVACAO_DEFESA = EstadoAprovacaoDefesa.PENDENTE;
    private static final EstadoAprovacaoDefesa UPDATED_ESTADO_APROVACAO_DEFESA = EstadoAprovacaoDefesa.APROVADO;

    @Autowired
    private ParticipacaoBancaRepository participacaoBancaRepository;

    @Autowired
    private ParticipacaoBancaMapper participacaoBancaMapper;

    @Autowired
    private ParticipacaoBancaService participacaoBancaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restParticipacaoBancaMockMvc;

    private ParticipacaoBanca participacaoBanca;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParticipacaoBancaResource participacaoBancaResource = new ParticipacaoBancaResource(participacaoBancaService);
        this.restParticipacaoBancaMockMvc = MockMvcBuilders.standaloneSetup(participacaoBancaResource)
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
    public static ParticipacaoBanca createEntity(EntityManager em) {
        ParticipacaoBanca participacaoBanca = new ParticipacaoBanca()
            .confirmado(DEFAULT_CONFIRMADO)
            .estadoAprovacaoDefesa(DEFAULT_ESTADO_APROVACAO_DEFESA);
        return participacaoBanca;
    }

    @Before
    public void initTest() {
        participacaoBanca = createEntity(em);
    }

    @Test
    @Transactional
    public void createParticipacaoBanca() throws Exception {
        int databaseSizeBeforeCreate = participacaoBancaRepository.findAll().size();

        // Create the ParticipacaoBanca
        ParticipacaoBancaDTO participacaoBancaDTO = participacaoBancaMapper.toDto(participacaoBanca);
        restParticipacaoBancaMockMvc.perform(post("/api/participacao-bancas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participacaoBancaDTO)))
            .andExpect(status().isCreated());

        // Validate the ParticipacaoBanca in the database
        List<ParticipacaoBanca> participacaoBancaList = participacaoBancaRepository.findAll();
        assertThat(participacaoBancaList).hasSize(databaseSizeBeforeCreate + 1);
        ParticipacaoBanca testParticipacaoBanca = participacaoBancaList.get(participacaoBancaList.size() - 1);
        assertThat(testParticipacaoBanca.isConfirmado()).isEqualTo(DEFAULT_CONFIRMADO);
        assertThat(testParticipacaoBanca.getEstadoAprovacaoDefesa()).isEqualTo(DEFAULT_ESTADO_APROVACAO_DEFESA);
    }

    @Test
    @Transactional
    public void createParticipacaoBancaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = participacaoBancaRepository.findAll().size();

        // Create the ParticipacaoBanca with an existing ID
        participacaoBanca.setId(1L);
        ParticipacaoBancaDTO participacaoBancaDTO = participacaoBancaMapper.toDto(participacaoBanca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParticipacaoBancaMockMvc.perform(post("/api/participacao-bancas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participacaoBancaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ParticipacaoBanca in the database
        List<ParticipacaoBanca> participacaoBancaList = participacaoBancaRepository.findAll();
        assertThat(participacaoBancaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllParticipacaoBancas() throws Exception {
        // Initialize the database
        participacaoBancaRepository.saveAndFlush(participacaoBanca);

        // Get all the participacaoBancaList
        restParticipacaoBancaMockMvc.perform(get("/api/participacao-bancas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(participacaoBanca.getId().intValue())))
            .andExpect(jsonPath("$.[*].confirmado").value(hasItem(DEFAULT_CONFIRMADO.booleanValue())))
            .andExpect(jsonPath("$.[*].estadoAprovacaoDefesa").value(hasItem(DEFAULT_ESTADO_APROVACAO_DEFESA.toString())));
    }

    @Test
    @Transactional
    public void getParticipacaoBanca() throws Exception {
        // Initialize the database
        participacaoBancaRepository.saveAndFlush(participacaoBanca);

        // Get the participacaoBanca
        restParticipacaoBancaMockMvc.perform(get("/api/participacao-bancas/{id}", participacaoBanca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(participacaoBanca.getId().intValue()))
            .andExpect(jsonPath("$.confirmado").value(DEFAULT_CONFIRMADO.booleanValue()))
            .andExpect(jsonPath("$.estadoAprovacaoDefesa").value(DEFAULT_ESTADO_APROVACAO_DEFESA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParticipacaoBanca() throws Exception {
        // Get the participacaoBanca
        restParticipacaoBancaMockMvc.perform(get("/api/participacao-bancas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParticipacaoBanca() throws Exception {
        // Initialize the database
        participacaoBancaRepository.saveAndFlush(participacaoBanca);
        int databaseSizeBeforeUpdate = participacaoBancaRepository.findAll().size();

        // Update the participacaoBanca
        ParticipacaoBanca updatedParticipacaoBanca = participacaoBancaRepository.findOne(participacaoBanca.getId());
        updatedParticipacaoBanca
            .confirmado(UPDATED_CONFIRMADO)
            .estadoAprovacaoDefesa(UPDATED_ESTADO_APROVACAO_DEFESA);
        ParticipacaoBancaDTO participacaoBancaDTO = participacaoBancaMapper.toDto(updatedParticipacaoBanca);

        restParticipacaoBancaMockMvc.perform(put("/api/participacao-bancas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participacaoBancaDTO)))
            .andExpect(status().isOk());

        // Validate the ParticipacaoBanca in the database
        List<ParticipacaoBanca> participacaoBancaList = participacaoBancaRepository.findAll();
        assertThat(participacaoBancaList).hasSize(databaseSizeBeforeUpdate);
        ParticipacaoBanca testParticipacaoBanca = participacaoBancaList.get(participacaoBancaList.size() - 1);
        assertThat(testParticipacaoBanca.isConfirmado()).isEqualTo(UPDATED_CONFIRMADO);
        assertThat(testParticipacaoBanca.getEstadoAprovacaoDefesa()).isEqualTo(UPDATED_ESTADO_APROVACAO_DEFESA);
    }

    @Test
    @Transactional
    public void updateNonExistingParticipacaoBanca() throws Exception {
        int databaseSizeBeforeUpdate = participacaoBancaRepository.findAll().size();

        // Create the ParticipacaoBanca
        ParticipacaoBancaDTO participacaoBancaDTO = participacaoBancaMapper.toDto(participacaoBanca);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restParticipacaoBancaMockMvc.perform(put("/api/participacao-bancas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participacaoBancaDTO)))
            .andExpect(status().isCreated());

        // Validate the ParticipacaoBanca in the database
        List<ParticipacaoBanca> participacaoBancaList = participacaoBancaRepository.findAll();
        assertThat(participacaoBancaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteParticipacaoBanca() throws Exception {
        // Initialize the database
        participacaoBancaRepository.saveAndFlush(participacaoBanca);
        int databaseSizeBeforeDelete = participacaoBancaRepository.findAll().size();

        // Get the participacaoBanca
        restParticipacaoBancaMockMvc.perform(delete("/api/participacao-bancas/{id}", participacaoBanca.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ParticipacaoBanca> participacaoBancaList = participacaoBancaRepository.findAll();
        assertThat(participacaoBancaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipacaoBanca.class);
        ParticipacaoBanca participacaoBanca1 = new ParticipacaoBanca();
        participacaoBanca1.setId(1L);
        ParticipacaoBanca participacaoBanca2 = new ParticipacaoBanca();
        participacaoBanca2.setId(participacaoBanca1.getId());
        assertThat(participacaoBanca1).isEqualTo(participacaoBanca2);
        participacaoBanca2.setId(2L);
        assertThat(participacaoBanca1).isNotEqualTo(participacaoBanca2);
        participacaoBanca1.setId(null);
        assertThat(participacaoBanca1).isNotEqualTo(participacaoBanca2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipacaoBancaDTO.class);
        ParticipacaoBancaDTO participacaoBancaDTO1 = new ParticipacaoBancaDTO();
        participacaoBancaDTO1.setId(1L);
        ParticipacaoBancaDTO participacaoBancaDTO2 = new ParticipacaoBancaDTO();
        assertThat(participacaoBancaDTO1).isNotEqualTo(participacaoBancaDTO2);
        participacaoBancaDTO2.setId(participacaoBancaDTO1.getId());
        assertThat(participacaoBancaDTO1).isEqualTo(participacaoBancaDTO2);
        participacaoBancaDTO2.setId(2L);
        assertThat(participacaoBancaDTO1).isNotEqualTo(participacaoBancaDTO2);
        participacaoBancaDTO1.setId(null);
        assertThat(participacaoBancaDTO1).isNotEqualTo(participacaoBancaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(participacaoBancaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(participacaoBancaMapper.fromId(null)).isNull();
    }
}
