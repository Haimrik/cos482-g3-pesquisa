import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AlunoCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/alunos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(aluno: AlunoCos482G3): Observable<AlunoCos482G3> {
        const copy = this.convert(aluno);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(aluno: AlunoCos482G3): Observable<AlunoCos482G3> {
        const copy = this.convert(aluno);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AlunoCos482G3> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to AlunoCos482G3.
     */
    private convertItemFromServer(json: any): AlunoCos482G3 {
        const entity: AlunoCos482G3 = Object.assign(new AlunoCos482G3(), json);
        entity.dataDeEntrada = this.dateUtils
            .convertDateTimeFromServer(json.dataDeEntrada);
        return entity;
    }

    /**
     * Convert a AlunoCos482G3 to a JSON which can be sent to the server.
     */
    private convert(aluno: AlunoCos482G3): AlunoCos482G3 {
        const copy: AlunoCos482G3 = Object.assign({}, aluno);

        copy.dataDeEntrada = this.dateUtils.toDate(aluno.dataDeEntrada);
        return copy;
    }
}
