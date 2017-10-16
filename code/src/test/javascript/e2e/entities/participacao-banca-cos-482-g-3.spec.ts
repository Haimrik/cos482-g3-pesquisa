import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('ParticipacaoBanca e2e test', () => {

    let navBarPage: NavBarPage;
    let participacaoBancaDialogPage: ParticipacaoBancaDialogPage;
    let participacaoBancaComponentsPage: ParticipacaoBancaComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ParticipacaoBancas', () => {
        navBarPage.goToEntity('participacao-banca-cos-482-g-3');
        participacaoBancaComponentsPage = new ParticipacaoBancaComponentsPage();
        expect(participacaoBancaComponentsPage.getTitle()).toMatch(/pesquisaApp.participacaoBanca.home.title/);

    });

    it('should load create ParticipacaoBanca dialog', () => {
        participacaoBancaComponentsPage.clickOnCreateButton();
        participacaoBancaDialogPage = new ParticipacaoBancaDialogPage();
        expect(participacaoBancaDialogPage.getModalTitle()).toMatch(/pesquisaApp.participacaoBanca.home.createOrEditLabel/);
        participacaoBancaDialogPage.close();
    });

    it('should create and save ParticipacaoBancas', () => {
        participacaoBancaComponentsPage.clickOnCreateButton();
        participacaoBancaDialogPage.getConfirmadoInput().isSelected().then(function (selected) {
            if (selected) {
                participacaoBancaDialogPage.getConfirmadoInput().click();
                expect(participacaoBancaDialogPage.getConfirmadoInput().isSelected()).toBeFalsy();
            } else {
                participacaoBancaDialogPage.getConfirmadoInput().click();
                expect(participacaoBancaDialogPage.getConfirmadoInput().isSelected()).toBeTruthy();
            }
        });
        participacaoBancaDialogPage.estadoAprovacaoDefesaSelectLastOption();
        participacaoBancaDialogPage.defesaSelectLastOption();
        participacaoBancaDialogPage.professorSelectLastOption();
        participacaoBancaDialogPage.save();
        expect(participacaoBancaDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ParticipacaoBancaComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-participacao-banca-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ParticipacaoBancaDialogPage {
    modalTitle = element(by.css('h4#myParticipacaoBancaLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    confirmadoInput = element(by.css('input#field_confirmado'));
    estadoAprovacaoDefesaSelect = element(by.css('select#field_estadoAprovacaoDefesa'));
    defesaSelect = element(by.css('select#field_defesa'));
    professorSelect = element(by.css('select#field_professor'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    getConfirmadoInput = function () {
        return this.confirmadoInput;
    }
    setEstadoAprovacaoDefesaSelect = function (estadoAprovacaoDefesa) {
        this.estadoAprovacaoDefesaSelect.sendKeys(estadoAprovacaoDefesa);
    }

    getEstadoAprovacaoDefesaSelect = function () {
        return this.estadoAprovacaoDefesaSelect.element(by.css('option:checked')).getText();
    }

    estadoAprovacaoDefesaSelectLastOption = function () {
        this.estadoAprovacaoDefesaSelect.all(by.tagName('option')).last().click();
    }
    defesaSelectLastOption = function () {
        this.defesaSelect.all(by.tagName('option')).last().click();
    }

    defesaSelectOption = function (option) {
        this.defesaSelect.sendKeys(option);
    }

    getDefesaSelect = function () {
        return this.defesaSelect;
    }

    getDefesaSelectedOption = function () {
        return this.defesaSelect.element(by.css('option:checked')).getText();
    }

    professorSelectLastOption = function () {
        this.professorSelect.all(by.tagName('option')).last().click();
    }

    professorSelectOption = function (option) {
        this.professorSelect.sendKeys(option);
    }

    getProfessorSelect = function () {
        return this.professorSelect;
    }

    getProfessorSelectedOption = function () {
        return this.professorSelect.element(by.css('option:checked')).getText();
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
