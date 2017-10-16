import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Defesa e2e test', () => {

    let navBarPage: NavBarPage;
    let defesaDialogPage: DefesaDialogPage;
    let defesaComponentsPage: DefesaComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Defesas', () => {
        navBarPage.goToEntity('defesa-cos-482-g-3');
        defesaComponentsPage = new DefesaComponentsPage();
        expect(defesaComponentsPage.getTitle()).toMatch(/pesquisaApp.defesa.home.title/);

    });

    it('should load create Defesa dialog', () => {
        defesaComponentsPage.clickOnCreateButton();
        defesaDialogPage = new DefesaDialogPage();
        expect(defesaDialogPage.getModalTitle()).toMatch(/pesquisaApp.defesa.home.createOrEditLabel/);
        defesaDialogPage.close();
    });

    it('should create and save Defesas', () => {
        defesaComponentsPage.clickOnCreateButton();
        defesaDialogPage.setDataEHoraInput(12310020012301);
        expect(defesaDialogPage.getDataEHoraInput()).toMatch('2001-12-31T02:30');
        defesaDialogPage.setLocalInput('local');
        expect(defesaDialogPage.getLocalInput()).toMatch('local');
        defesaDialogPage.setArquivoTextoInput('arquivoTexto');
        expect(defesaDialogPage.getArquivoTextoInput()).toMatch('arquivoTexto');
        defesaDialogPage.tipoDefesaSelectLastOption();
        defesaDialogPage.alunoSelectLastOption();
        defesaDialogPage.save();
        expect(defesaDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DefesaComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-defesa-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DefesaDialogPage {
    modalTitle = element(by.css('h4#myDefesaLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    dataEHoraInput = element(by.css('input#field_dataEHora'));
    localInput = element(by.css('input#field_local'));
    arquivoTextoInput = element(by.css('input#field_arquivoTexto'));
    tipoDefesaSelect = element(by.css('select#field_tipoDefesa'));
    alunoSelect = element(by.css('select#field_aluno'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setDataEHoraInput = function (dataEHora) {
        this.dataEHoraInput.sendKeys(dataEHora);
    }

    getDataEHoraInput = function () {
        return this.dataEHoraInput.getAttribute('value');
    }

    setLocalInput = function (local) {
        this.localInput.sendKeys(local);
    }

    getLocalInput = function () {
        return this.localInput.getAttribute('value');
    }

    setArquivoTextoInput = function (arquivoTexto) {
        this.arquivoTextoInput.sendKeys(arquivoTexto);
    }

    getArquivoTextoInput = function () {
        return this.arquivoTextoInput.getAttribute('value');
    }

    setTipoDefesaSelect = function (tipoDefesa) {
        this.tipoDefesaSelect.sendKeys(tipoDefesa);
    }

    getTipoDefesaSelect = function () {
        return this.tipoDefesaSelect.element(by.css('option:checked')).getText();
    }

    tipoDefesaSelectLastOption = function () {
        this.tipoDefesaSelect.all(by.tagName('option')).last().click();
    }
    alunoSelectLastOption = function () {
        this.alunoSelect.all(by.tagName('option')).last().click();
    }

    alunoSelectOption = function (option) {
        this.alunoSelect.sendKeys(option);
    }

    getAlunoSelect = function () {
        return this.alunoSelect;
    }

    getAlunoSelectedOption = function () {
        return this.alunoSelect.element(by.css('option:checked')).getText();
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
