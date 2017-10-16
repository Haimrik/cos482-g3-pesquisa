import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { DefesaCos482G3 } from './defesa-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DefesaCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/defesas';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(defesa: DefesaCos482G3): Observable<DefesaCos482G3> {
        const copy = this.convert(defesa);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(defesa: DefesaCos482G3): Observable<DefesaCos482G3> {
        const copy = this.convert(defesa);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<DefesaCos482G3> {
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
     * Convert a returned JSON object to DefesaCos482G3.
     */
    private convertItemFromServer(json: any): DefesaCos482G3 {
        const entity: DefesaCos482G3 = Object.assign(new DefesaCos482G3(), json);
        entity.dataEHora = this.dateUtils
            .convertDateTimeFromServer(json.dataEHora);
        return entity;
    }

    /**
     * Convert a DefesaCos482G3 to a JSON which can be sent to the server.
     */
    private convert(defesa: DefesaCos482G3): DefesaCos482G3 {
        const copy: DefesaCos482G3 = Object.assign({}, defesa);

        copy.dataEHora = this.dateUtils.toDate(defesa.dataEHora);
        return copy;
    }
}
