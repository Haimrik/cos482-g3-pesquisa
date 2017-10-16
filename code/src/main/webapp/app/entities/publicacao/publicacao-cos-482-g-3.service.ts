import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { PublicacaoCos482G3 } from './publicacao-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PublicacaoCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/publicacaos';

    constructor(private http: Http) { }

    create(publicacao: PublicacaoCos482G3): Observable<PublicacaoCos482G3> {
        const copy = this.convert(publicacao);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(publicacao: PublicacaoCos482G3): Observable<PublicacaoCos482G3> {
        const copy = this.convert(publicacao);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<PublicacaoCos482G3> {
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
     * Convert a returned JSON object to PublicacaoCos482G3.
     */
    private convertItemFromServer(json: any): PublicacaoCos482G3 {
        const entity: PublicacaoCos482G3 = Object.assign(new PublicacaoCos482G3(), json);
        return entity;
    }

    /**
     * Convert a PublicacaoCos482G3 to a JSON which can be sent to the server.
     */
    private convert(publicacao: PublicacaoCos482G3): PublicacaoCos482G3 {
        const copy: PublicacaoCos482G3 = Object.assign({}, publicacao);
        return copy;
    }
}
