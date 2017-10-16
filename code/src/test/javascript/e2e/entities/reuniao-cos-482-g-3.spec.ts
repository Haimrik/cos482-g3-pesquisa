import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Reuniao e2e test', () => {

    let navBarPage: NavBarPage;
    let reuniaoDialogPage: ReuniaoDialogPage;
    let reuniaoComponentsPage: ReuniaoComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Reuniaos', () => {
        navBarPage.goToEntity('reuniao-cos-482-g-3');
        reuniaoComponentsPage = new ReuniaoComponentsPage();
        expect(reuniaoComponentsPage.getTitle()).toMatch(/pesquisaApp.reuniao.home.title/);

    });

    it('should load create Reuniao dialog', () => {
        reuniaoComponentsPage.clickOnCreateButton();
        reuniaoDialogPage = new ReuniaoDialogPage();
        expect(reuniaoDialogPage.getModalTitle()).toMatch(/pesquisaApp.reuniao.home.createOrEditLabel/);
        reuniaoDialogPage.close();
    });

    it('should create and save Reuniaos', () => {
        reuniaoComponentsPage.clickOnCreateButton();
        reuniaoDialogPage.setDataEHoraInput(12310020012301);
        expect(reuniaoDialogPage.getDataEHoraInput()).toMatch('2001-12-31T02:30');
        reuniaoDialogPage.setLocalInput('local');
        expect(reuniaoDialogPage.getLocalInput()).toMatch('local');
        reuniaoDialogPage.professorSelectLastOption();
        reuniaoDialogPage.alunoSelectLastOption();
        reuniaoDialogPage.save();
        expect(reuniaoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ReuniaoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-reuniao-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ReuniaoDialogPage {
    modalTitle = element(by.css('h4#myReuniaoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    dataEHoraInput = element(by.css('input#field_dataEHora'));
    localInput = element(by.css('input#field_local'));
    professorSelect = element(by.css('select#field_professor'));
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
