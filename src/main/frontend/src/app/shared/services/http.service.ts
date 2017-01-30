import { Injectable } from "@angular/core";
import { Headers, Http, URLSearchParams } from "@angular/http";
import { API_PATH } from "../../app.config";

@Injectable()
export class HttpService {
    private static headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded'});

    constructor(private http: Http) {}

    public post(url: string, data: any, csrf?: string) {
        return this.makeRequest('post', API_PATH + url, data);
    }

    public get(url: string) {
        return this.makeRequest('get', API_PATH + url);
    }

    private getEncodedData(requestData) {
        let body = new URLSearchParams();

        if (!requestData) {
            return body;
        }

        Object.keys(requestData).forEach(key => {
            body.set(key, requestData[key]);
        });

        return body;
    }

    private makeRequest(method: string, url: string, data?: any) {
        return this.http.request(url, {
            method: method,
            headers: HttpService.headers,
            body: this.getEncodedData(data)
        });
    }
}