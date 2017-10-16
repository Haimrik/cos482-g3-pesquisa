import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Publicacao e2e test', () => {

    let navBarPage: NavBarPage;
    let publicacaoDialogPage: PublicacaoDialogPage;
    let publicacaoComponentsPage: PublicacaoComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Publicacaos', () => {
        navBarPage.goToEntity('publicacao-cos-482-g-3');
        publicacaoComponentsPage = new PublicacaoComponentsPage();
        expect(publicacaoComponentsPage.getTitle()).toMatch(/pesquisaApp.publicacao.home.title/);

    });

    it('should load create Publicacao dialog', () => {
        publicacaoComponentsPage.clickOnCreateButton();
        publicacaoDialogPage = new PublicacaoDialogPage();
        expect(publicacaoDialogPage.getModalTitle()).toMatch(/pesquisaApp.publicacao.home.createOrEditLabel/);
        publicacaoDialogPage.close();
    });

    it('should create and save Publicacaos', () => {
        publicacaoComponentsPage.clickOnCreateButton();
        publicacaoDialogPage.setUrlInput('url');
        expect(publicacaoDialogPage.getUrlInput()).toMatch('url');
        publicacaoDialogPage.getPertenceAoProgramaInput().isSelected().then(function (selected) {
            if (selected) {
                publicacaoDialogPage.getPertenceAoProgramaInput().click();
                expect(publicacaoDialogPage.getPertenceAoProgramaInput().isSelected()).toBeFalsy();
            } else {
                publicacaoDialogPage.getPertenceAoProgramaInput().click();
                expect(publicacaoDialogPage.getPertenceAoProgramaInput().isSelected()).toBeTruthy();
            }
        });
        publicacaoDialogPage.alunoSelectLastOption();
        publicacaoDialogPage.save();
        expect(publicacaoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class PublicacaoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-publicacao-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PublicacaoDialogPage {
    modalTitle = element(by.css('h4#myPublicacaoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    urlInput = element(by.css('input#field_url'));
    pertenceAoProgramaInput = element(by.css('input#field_pertenceAoPrograma'));
    alunoSelect = element(by.css('select#field_aluno'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setUrlInput = function (url) {
        this.urlInput.sendKeys(url);
    }

    getUrlInput = function () {
        return this.urlInput.getAttribute('value');
    }

    getPertenceAoProgramaInput = function () {
        return this.pertenceAoProgramaInput;
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
