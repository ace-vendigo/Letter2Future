import {Injectable} from "@angular/core";
import { Http, Response } from "@angular/http";
import { API_PATH } from "../app.config";

@Injectable()
export class HomeService {
    private newsRoute = API_PATH + "news";
    private news: string;
    
    constructor(private http: Http) {
        this.getNews();
    }

    public async getNews(): Promise<any> {
        if (!this.news) {
            let newsResponse: Response = await this.http.get(this.newsRoute).toPromise();

            this.news = newsResponse.text();
        }

        return this.news;
    }
}