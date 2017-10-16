import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { UsuarioCos482G3 } from './usuario-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class UsuarioCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/usuarios';

    constructor(private http: Http) { }

    create(usuario: UsuarioCos482G3): Observable<UsuarioCos482G3> {
        const copy = this.convert(usuario);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(usuario: UsuarioCos482G3): Observable<UsuarioCos482G3> {
        const copy = this.convert(usuario);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<UsuarioCos482G3> {
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
     * Convert a returned JSON object to UsuarioCos482G3.
     */
    private convertItemFromServer(json: any): UsuarioCos482G3 {
        const entity: UsuarioCos482G3 = Object.assign(new UsuarioCos482G3(), json);
        return entity;
    }

    /**
     * Convert a UsuarioCos482G3 to a JSON which can be sent to the server.
     */
    private convert(usuario: UsuarioCos482G3): UsuarioCos482G3 {
        const copy: UsuarioCos482G3 = Object.assign({}, usuario);
        return copy;
    }
}
