import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ParticipacaoBancaCos482G3 } from './participacao-banca-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ParticipacaoBancaCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/participacao-bancas';

    constructor(private http: Http) { }

    create(participacaoBanca: ParticipacaoBancaCos482G3): Observable<ParticipacaoBancaCos482G3> {
        const copy = this.convert(participacaoBanca);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(participacaoBanca: ParticipacaoBancaCos482G3): Observable<ParticipacaoBancaCos482G3> {
        const copy = this.convert(participacaoBanca);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ParticipacaoBancaCos482G3> {
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
     * Convert a returned JSON object to ParticipacaoBancaCos482G3.
     */
    private convertItemFromServer(json: any): ParticipacaoBancaCos482G3 {
        const entity: ParticipacaoBancaCos482G3 = Object.assign(new ParticipacaoBancaCos482G3(), json);
        return entity;
    }

    /**
     * Convert a ParticipacaoBancaCos482G3 to a JSON which can be sent to the server.
     */
    private convert(participacaoBanca: ParticipacaoBancaCos482G3): ParticipacaoBancaCos482G3 {
        const copy: ParticipacaoBancaCos482G3 = Object.assign({}, participacaoBanca);
        return copy;
    }
}
