import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';


describe('Usuario e2e test', () => {

    let navBarPage: NavBarPage;
    let usuarioDialogPage: UsuarioDialogPage;
    let usuarioComponentsPage: UsuarioComponentsPage;


    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Usuarios', () => {
        navBarPage.goToEntity('usuario-cos-482-g-3');
        usuarioComponentsPage = new UsuarioComponentsPage();
        expect(usuarioComponentsPage.getTitle()).toMatch(/pesquisaApp.usuario.home.title/);

    });

    it('should load create Usuario dialog', () => {
        usuarioComponentsPage.clickOnCreateButton();
        usuarioDialogPage = new UsuarioDialogPage();
        expect(usuarioDialogPage.getModalTitle()).toMatch(/pesquisaApp.usuario.home.createOrEditLabel/);
        usuarioDialogPage.close();
    });

    it('should create and save Usuarios', () => {
        usuarioComponentsPage.clickOnCreateButton();
        // usuarioDialogPage.publicacaoSelectLastOption();
        usuarioDialogPage.save();
        expect(usuarioDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class UsuarioComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-usuario-cos-482-g-3 div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class UsuarioDialogPage {
    modalTitle = element(by.css('h4#myUsuarioLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    publicacaoSelect = element(by.css('select#field_publicacao'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    publicacaoSelectLastOption = function () {
        this.publicacaoSelect.all(by.tagName('option')).last().click();
    }

    publicacaoSelectOption = function (option) {
        this.publicacaoSelect.sendKeys(option);
    }

    getPublicacaoSelect = function () {
        return this.publicacaoSelect;
    }

    getPublicacaoSelectedOption = function () {
        return this.publicacaoSelect.element(by.css('option:checked')).getText();
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
