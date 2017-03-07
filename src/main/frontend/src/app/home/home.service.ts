import {Injectable} from "@angular/core";
import { Http, Response } from "@angular/http";
import { apiUrl } from "../app.config";

@Injectable()
export class HomeService {
    private news:any;

    constructor(private http:Http) {
        this.getNews();
    }

    public async getNews():Promise<any> {
        if (!this.news) {
            let newsResponse:Response = await this.http.get(apiUrl(this.newsRoute)).toPromise();
            this.news = newsResponse.text();
        }

        return this.news;
    }
}