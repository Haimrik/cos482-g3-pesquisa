import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Aluno e2e test', () => {

    let navBarPage: NavBarPage;
    let alunoDialogPage: AlunoDialogPage;
    let alunoComponentsPage: AlunoComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Alunos', () => {
        navBarPage.goToEntity('aluno-cos-482-g-3');
        alunoComponentsPage = new AlunoComponentsPage();
        expect(alunoComponentsPage.getTitle()).toMatch(/pesquisaApp.aluno.home.title/);

    });

    it('should load create Aluno dialog', () => {
        alunoComponentsPage.clickOnCreateButton();
        alunoDialogPage = new AlunoDialogPage();
        expect(alunoDialogPage.getModalTitle()).toMatch(/pesquisaApp.aluno.home.createOrEditLabel/);
        alunoDialogPage.close();
    });

    it('should create and save Alunos', () => {
        alunoComponentsPage.clickOnCreateButton();
        alunoDialogPage.setNomeInput('nome');
        expect(alunoDialogPage.getNomeInput()).toMatch('nome');
        alunoDialogPage.setDreInput('dre');
        expect(alunoDialogPage.getDreInput()).toMatch('dre');
        alunoDialogPage.setDataDeEntradaInput(12310020012301);
        expect(alunoDialogPage.getDataDeEntradaInput()).toMatch('2001-12-31T02:30');
        alunoDialogPage.usuarioSelectLastOption();
        alunoDialogPage.orientadorSelectLastOption();
        alunoDialogPage.save();
        expect(alunoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class AlunoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-aluno-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class AlunoDialogPage {
    modalTitle = element(by.css('h4#myAlunoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nomeInput = element(by.css('input#field_nome'));
    dreInput = element(by.css('input#field_dre'));
    dataDeEntradaInput = element(by.css('input#field_dataDeEntrada'));
    usuarioSelect = element(by.css('select#field_usuario'));
    orientadorSelect = element(by.css('select#field_orientador'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNomeInput = function (nome) {
        this.nomeInput.sendKeys(nome);
    }

    getNomeInput = function () {
        return this.nomeInput.getAttribute('value');
    }

    setDreInput = function (dre) {
        this.dreInput.sendKeys(dre);
    }

    getDreInput = function () {
        return this.dreInput.getAttribute('value');
    }

    setDataDeEntradaInput = function (dataDeEntrada) {
        this.dataDeEntradaInput.sendKeys(dataDeEntrada);
    }

    getDataDeEntradaInput = function () {
        return this.dataDeEntradaInput.getAttribute('value');
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

    orientadorSelectLastOption = function () {
        this.orientadorSelect.all(by.tagName('option')).last().click();
    }

    orientadorSelectOption = function (option) {
        this.orientadorSelect.sendKeys(option);
    }

    getOrientadorSelect = function () {
        return this.orientadorSelect;
    }

    getOrientadorSelectedOption = function () {
        return this.orientadorSelect.element(by.css('option:checked')).getText();
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
