import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Seminario e2e test', () => {

    let navBarPage: NavBarPage;
    let seminarioDialogPage: SeminarioDialogPage;
    let seminarioComponentsPage: SeminarioComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Seminarios', () => {
        navBarPage.goToEntity('seminario-cos-482-g-3');
        seminarioComponentsPage = new SeminarioComponentsPage();
        expect(seminarioComponentsPage.getTitle()).toMatch(/pesquisaApp.seminario.home.title/);

    });

    it('should load create Seminario dialog', () => {
        seminarioComponentsPage.clickOnCreateButton();
        seminarioDialogPage = new SeminarioDialogPage();
        expect(seminarioDialogPage.getModalTitle()).toMatch(/pesquisaApp.seminario.home.createOrEditLabel/);
        seminarioDialogPage.close();
    });

    it('should create and save Seminarios', () => {
        seminarioComponentsPage.clickOnCreateButton();
        seminarioDialogPage.setTituloInput('titulo');
        expect(seminarioDialogPage.getTituloInput()).toMatch('titulo');
        seminarioDialogPage.setDataEHoraInput(12310020012301);
        expect(seminarioDialogPage.getDataEHoraInput()).toMatch('2001-12-31T02:30');
        seminarioDialogPage.setLocalInput('local');
        expect(seminarioDialogPage.getLocalInput()).toMatch('local');
        seminarioDialogPage.organizadorSelectLastOption();
        seminarioDialogPage.save();
        expect(seminarioDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SeminarioComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-seminario-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SeminarioDialogPage {
    modalTitle = element(by.css('h4#mySeminarioLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    tituloInput = element(by.css('input#field_titulo'));
    dataEHoraInput = element(by.css('input#field_dataEHora'));
    localInput = element(by.css('input#field_local'));
    organizadorSelect = element(by.css('select#field_organizador'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setTituloInput = function (titulo) {
        this.tituloInput.sendKeys(titulo);
    }

    getTituloInput = function () {
        return this.tituloInput.getAttribute('value');
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

    organizadorSelectLastOption = function () {
        this.organizadorSelect.all(by.tagName('option')).last().click();
    }

    organizadorSelectOption = function (option) {
        this.organizadorSelect.sendKeys(option);
    }

    getOrganizadorSelect = function () {
        return this.organizadorSelect;
    }

    getOrganizadorSelectedOption = function () {
        return this.organizadorSelect.element(by.css('option:checked')).getText();
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
