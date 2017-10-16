import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Professor e2e test', () => {

    let navBarPage: NavBarPage;
    let professorDialogPage: ProfessorDialogPage;
    let professorComponentsPage: ProfessorComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Professors', () => {
        navBarPage.goToEntity('professor-cos-482-g-3');
        professorComponentsPage = new ProfessorComponentsPage();
        expect(professorComponentsPage.getTitle()).toMatch(/pesquisaApp.professor.home.title/);

    });

    it('should load create Professor dialog', () => {
        professorComponentsPage.clickOnCreateButton();
        professorDialogPage = new ProfessorDialogPage();
        expect(professorDialogPage.getModalTitle()).toMatch(/pesquisaApp.professor.home.createOrEditLabel/);
        professorDialogPage.close();
    });

    it('should create and save Professors', () => {
        professorComponentsPage.clickOnCreateButton();
        professorDialogPage.setNomeInput('nome');
        expect(professorDialogPage.getNomeInput()).toMatch('nome');
        professorDialogPage.setMatriculaInput('matricula');
        expect(professorDialogPage.getMatriculaInput()).toMatch('matricula');
        professorDialogPage.setLinkLattesInput('linkLattes');
        expect(professorDialogPage.getLinkLattesInput()).toMatch('linkLattes');
        professorDialogPage.setProgramaInput('programa');
        expect(professorDialogPage.getProgramaInput()).toMatch('programa');
        professorDialogPage.setLinhaDePesquisaInput('linhaDePesquisa');
        expect(professorDialogPage.getLinhaDePesquisaInput()).toMatch('linhaDePesquisa');
        professorDialogPage.setAreasDeInteresseInput('areasDeInteresse');
        expect(professorDialogPage.getAreasDeInteresseInput()).toMatch('areasDeInteresse');
        professorDialogPage.usuarioSelectLastOption();
        // professorDialogPage.coorientadorSelectLastOption();
        professorDialogPage.save();
        expect(professorDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ProfessorComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-professor-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ProfessorDialogPage {
    modalTitle = element(by.css('h4#myProfessorLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nomeInput = element(by.css('input#field_nome'));
    matriculaInput = element(by.css('input#field_matricula'));
    linkLattesInput = element(by.css('input#field_linkLattes'));
    programaInput = element(by.css('input#field_programa'));
    linhaDePesquisaInput = element(by.css('input#field_linhaDePesquisa'));
    areasDeInteresseInput = element(by.css('input#field_areasDeInteresse'));
    usuarioSelect = element(by.css('select#field_usuario'));
    coorientadorSelect = element(by.css('select#field_coorientador'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNomeInput = function (nome) {
        this.nomeInput.sendKeys(nome);
    }

    getNomeInput = function () {
        return this.nomeInput.getAttribute('value');
    }

    setMatriculaInput = function (matricula) {
        this.matriculaInput.sendKeys(matricula);
    }

    getMatriculaInput = function () {
        return this.matriculaInput.getAttribute('value');
    }

    setLinkLattesInput = function (linkLattes) {
        this.linkLattesInput.sendKeys(linkLattes);
    }

    getLinkLattesInput = function () {
        return this.linkLattesInput.getAttribute('value');
    }

    setProgramaInput = function (programa) {
        this.programaInput.sendKeys(programa);
    }

    getProgramaInput = function () {
        return this.programaInput.getAttribute('value');
    }

    setLinhaDePesquisaInput = function (linhaDePesquisa) {
        this.linhaDePesquisaInput.sendKeys(linhaDePesquisa);
    }

    getLinhaDePesquisaInput = function () {
        return this.linhaDePesquisaInput.getAttribute('value');
    }

    setAreasDeInteresseInput = function (areasDeInteresse) {
        this.areasDeInteresseInput.sendKeys(areasDeInteresse);
    }

    getAreasDeInteresseInput = function () {
        return this.areasDeInteresseInput.getAttribute('value');
    }

    usuarioSelectLastOption = function () {
        this.usuarioSelect.all(by.tagName('option')).last().click();
    }

    usuarioSelectOption = function (option) {
        this.usuarioSelect.sendKeys(option);
    }

    getUsuarioSelect = function () {
        return this.usuarioSelect;
    }

    getUsuarioSelectedOption = function () {
        return this.usuarioSelect.element(by.css('option:checked')).getText();
    }

    coorientadorSelectLastOption = function () {
        this.coorientadorSelect.all(by.tagName('option')).last().click();
    }

    coorientadorSelectOption = function (option) {
        this.coorientadorSelect.sendKeys(option);
    }

    getCoorientadorSelect = function () {
        return this.coorientadorSelect;
    }

    getCoorientadorSelectedOption = function () {
        return this.coorientadorSelect.element(by.css('option:checked')).getText();
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
